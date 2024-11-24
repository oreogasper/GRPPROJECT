package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import entity.User;
import interface_adapter.logout.LogoutController;
import interface_adapter.statistics.ChangePasswordController;
import interface_adapter.statistics.StatisticsController;
import interface_adapter.statistics.StatisticsState;
import interface_adapter.statistics.StatisticsViewModel;

/**
 * The View for the Statistics Use Case.
 */
public class StatsView extends JPanel implements PropertyChangeListener {
    private static final String INITIAL_VALUE = "0";
    private static final Integer TABLE_WIDTH = 200;
    private static final Integer TABLE_HEIGHT = 125;
    private static final Integer ROW_THREE = 3;
    private static final Integer ROW_FOUR = 4;
    private static final Integer ROW_FIVE = 5;

    private final String viewName = "stats";
    private final StatisticsViewModel statisticsViewModel;
    private ChangePasswordController changePasswordController;
    private StatisticsController statisticsController;

    private final DefaultTableModel tableModel;
    private final JTable table;
    private final JButton logOut;
    private final JTextField passwordInputField = new JTextField(15);
    private final JButton changePassword;
    private final JButton cancel;
    private final JButton leaderboard;
    private LogoutController logoutController;

    public StatsView(StatisticsViewModel statisticsViewModel) {
        this.statisticsViewModel = statisticsViewModel;
        this.statisticsViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(StatisticsViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel button = new JPanel();
        leaderboard = new JButton(StatisticsViewModel.TO_LEADERBOARD_BUTTON_LABEL);
        button.add(leaderboard);

        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Change Password To:"), passwordInputField);

        final JPanel buttons = new JPanel();
        logOut = new JButton("Log Out");
        buttons.add(logOut);

        changePassword = new JButton("Change Password");
        buttons.add(changePassword);

        cancel = new JButton(StatisticsViewModel.RETURN_MENU_BUTTON_LABEL);
        buttons.add(cancel);

        // Initializing table
        final String[] columnNames = {"Statistic", "Value"};
        final String[][] initialData = {
                {"Currently logged in", "username"},
                {"Balance", INITIAL_VALUE},
                {"Wins", INITIAL_VALUE},
                {"Losses", INITIAL_VALUE},
                {"Win percentage", INITIAL_VALUE},
                {"Games", INITIAL_VALUE},
        };
        tableModel = new DefaultTableModel(initialData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);

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
                evt -> {
                    if (evt.getSource().equals(changePassword)) {
                        final StatisticsState currentState = statisticsViewModel.getState();
                        this.changePasswordController.execute(
                                currentState.getUsername(),
                                currentState.getPassword(),
                                currentState.getUser().getInfo());
                    }
                }
        );

        logOut.addActionListener(
                evt -> {
                    if (evt.getSource().equals(logOut)) {
                        final StatisticsState currentState = statisticsViewModel.getState();
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

        this.add(button);
        this.add(buttons);
        this.add(passwordInfo);
        final JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
        this.add(scrollPane);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final StatisticsState state = (StatisticsState) evt.getNewValue();
            final User user = state.getUser();

            tableModel.setValueAt(state.getUsername(), 0, 1);
            tableModel.setValueAt(String.valueOf(user.getBalance()), 1, 1);
            tableModel.setValueAt(String.valueOf(user.getWins()), 2, 1);
            tableModel.setValueAt(String.valueOf(user.getLosses()), ROW_THREE, 1);
            // TODO: when games is 0, add 1
            tableModel.setValueAt(String.valueOf(user.getWins() / (user.getGames() + 1)), ROW_FOUR, 1);
            tableModel.setValueAt(String.valueOf(user.getGames()), ROW_FIVE, 1);

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
