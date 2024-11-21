package view;

import java.awt.Component;
import java.beans.PropertyChangeEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.statistics.ChangePasswordController;
import interface_adapter.logout.LogoutController;

import interface_adapter.statistics.StatisticsController;
import interface_adapter.statistics.StatisticsState;
import interface_adapter.statistics.StatisticsViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

/**
 * The View for the Statistics Use Case.
 */
public class StatsView extends JPanel implements PropertyChangeListener {
    private final String viewName = "stats";
    private final StatisticsViewModel statisticsViewModel;
    private ChangePasswordController changePasswordController;

    private StatisticsController statisticsController;

    private final JLabel username;
    private final JButton logOut;
    private final JTextField passwordInputField = new JTextField(15);
    private final JButton changePassword;

    private final JButton cancel;
    private final JButton leaderboard;
    JTable j;
    private LogoutController logoutController;

    public StatsView(StatisticsViewModel statisticsViewModel) {
        this.statisticsViewModel = statisticsViewModel;
        this.statisticsViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(StatisticsViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel button = new JPanel();
        leaderboard = new JButton(StatisticsViewModel.TO_LEADERBOARD_BUTTON_LABEL);
        button.add(leaderboard);
        // TODO:
        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Change Password To:"), passwordInputField);

        username = new JLabel();
        final StatsTextPanel userInfo = new StatsTextPanel(
                new JLabel("Currently logged in: "), username);

        final JPanel buttons = new JPanel();
        logOut = new JButton("Log Out");
        buttons.add(logOut);

        changePassword = new JButton("Change Password");
        buttons.add(changePassword);

        //TODO:
        cancel = new JButton(StatisticsViewModel.RETURN_MENU_BUTTON_LABEL);
        buttons.add(cancel);

        // TEMPORARY BUTTONS
        final JTable info = new JTable();
        String[][] data = {
                {"Tokens", "0"},
                {"Wins", "10"},
                {"Losses", "20"},
                {"Win percentage", "30"},
                {"Games", "40"},
        };

        // Column Names
        final String[] columnNames = {"Name", "Roll Number"};

        // Initializing the JTable
        j = new JTable(data, columnNames);
        j.setBounds(0, 10, getWidth(), getHeight());
        // TODO:
        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final StatisticsState currentState = statisticsViewModel.getState();
                currentState.setPassword(passwordInputField.getText());
                statisticsViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        changePassword.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(changePassword)) {
                        final StatisticsState currentState = statisticsViewModel.getState();

                        this.changePasswordController.execute(
                                currentState.getUsername(),
                                currentState.getPassword()
                        );
                    }
                }
        );

        logOut.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(logOut)) {
                        StatisticsState currentState = statisticsViewModel.getState();
                        // TODO:
                        System.out.println(currentState);
                        this.logoutController.execute(currentState.getUsername());
                    }
                }
        );

        cancel.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        System.out.println(statisticsViewModel.getState());
                        statisticsController.switchToMenuView();
                    }
                }
        );

        leaderboard.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        statisticsController.switchToWelcomeView();
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(userInfo);

        this.add(button);
        this.add(buttons);
        this.add(passwordInfo);
        this.add(j);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final StatisticsState state = (StatisticsState) evt.getNewValue();
            username.setText(state.getUsername());
        }

        else if (evt.getPropertyName().equals("password")) {
            final StatisticsState state = (StatisticsState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "password updated for " + state.getUsername());
        }

    }

    public String getViewName() {
        return viewName;
    }

    public void setChangePasswordController(ChangePasswordController changePasswordController) {
        this.changePasswordController = changePasswordController;
    }

    public void setStatisticsController(StatisticsController controller) {
        this.statisticsController = controller;
    }
    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }
}
