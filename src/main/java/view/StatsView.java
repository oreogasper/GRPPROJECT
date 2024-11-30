package view;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import entity.AppColors;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.JTextComponent;

import entity.User;
import interface_adapter.logout.LogoutController;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.statistics.StatisticsController;
import interface_adapter.statistics.StatisticsState;
import interface_adapter.statistics.StatisticsViewModel;

/**
 * The View for the Statistics Use Case.
 */
public class StatsView extends JPanel implements PropertyChangeListener {
    final JTextField passwordInputField = createStyledTextComponent(new JPasswordField());
    private static final String INITIAL_VALUE = "0";
    public static final String STATE_CHANGED = "state";
    public static final String PASSWORD_CHANGED = "password";
    private final String viewName = "stats";
    private final StatisticsViewModel statisticsViewModel;
    private ChangePasswordController changePasswordController;
    private StatisticsController statisticsController;

    private final DefaultTableModel tableModel;
    private final JTable table;
    private final JButton logOut;
    private final JButton changePassword;
    private final JButton cancel;
    private final JButton leaderboard;
    private LogoutController logoutController;

    public StatsView(StatisticsViewModel statisticsViewModel) {
        this.statisticsViewModel = statisticsViewModel;
        this.statisticsViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(StatisticsViewModel.TITLE_LABEL);
        title.setFont(new Font("Serif", Font.BOLD, 24));
        title.setForeground(AppColors.YELLOW);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        leaderboard = createStyledButton(StatisticsViewModel.LEADERBOARD_BUTTON_LABEL, AppColors.DARK_RED);
        leaderboard.setPreferredSize(new Dimension(500, 30));
        leaderboard.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        buttons.setBackground(AppColors.DARK_GREEN);

        logOut = createStyledButton(StatisticsViewModel.LOGOUT_BUTTON_LABEL, AppColors.DARK_RED);
        logOut.setPreferredSize(new Dimension(150, 30));
        buttons.add(logOut);

        cancel = createStyledButton(StatisticsViewModel.RETURN_MENU_BUTTON_LABEL, AppColors.DARK_RED);
        cancel.setPreferredSize(new Dimension(200, 30));
        buttons.add(cancel);

        final JLabel password = new JLabel(StatisticsViewModel.CHANGE_PASS_LABEL);
        password.setFont(new Font("Serif", Font.BOLD, 15));
        password.setForeground(AppColors.YELLOW);

        final LabelTextPanel passwordInfo = new LabelTextPanel(password, passwordInputField);
        passwordInfo.setBackground(AppColors.DARK_GREEN);

        final JPanel changePass = new JPanel();
        changePass.setBackground(AppColors.DARK_GREEN);
        changePassword = createStyledButton(StatisticsViewModel.CHANGE_PASS_BUTTON_LABEL, AppColors.DARK_RED);
        changePassword.setPreferredSize(new Dimension(150, 30));
        changePass.add(passwordInfo);
        changePass.add(changePassword);

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
        table.setBackground(AppColors.YELLOW);
        table.setForeground(AppColors.BLACK);
        table.setFont(new Font("Serif", Font.PLAIN, 15));
        table.setRowHeight(18);
        table.setShowGrid(true);
        table.setGridColor(AppColors.BRIGHT_GREEN);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("Serif", Font.BOLD, 16));
        tableHeader.setBackground(AppColors.DARK_GREEN);
        tableHeader.setForeground(AppColors.DARK_GREEN);
        tableHeader.setReorderingAllowed(false);

        final JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(AppColors.YELLOW);
        scrollPane.setPreferredSize(new Dimension(100, 140));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(AppColors.DARK_GREEN);
        this.add(Box.createVerticalStrut(20));
        this.add(title);
        this.add(Box.createVerticalStrut(10));
        this.add(leaderboard);
        this.add(Box.createVerticalStrut(10));
        this.add(scrollPane);
        this.add(Box.createVerticalStrut(10));
        this.add(buttons);
        this.add(Box.createVerticalStrut(10));
        this.add(changePass);

        addActionListeners();
    }

    private void addActionListeners() {
        changePassword.addActionListener(evt -> {
            final StatisticsState currentState = statisticsViewModel.getState();

            // Check if the new password is different from the current one
            if (!currentState.getPassword().equals(passwordInputField.getText())) {
                currentState.setPassword(passwordInputField.getText());
                statisticsViewModel.setState(currentState);

                this.changePasswordController.execute(
                        currentState.getUsername(),
                        currentState.getPassword(),
                        currentState.getUser().getInfo()
                );
            } else {
                JOptionPane.showMessageDialog(this,
                        "The new password must be different from the old password!");
            }
        });
        logOut.addActionListener(
                evt -> {
                    if (evt.getSource().equals(logOut)) {
                        final StatisticsState currentState = statisticsViewModel.getState();
                        this.logoutController.execute(currentState.getUsername());
                    }
                }
        );
        cancel.addActionListener(evt -> statisticsController.switchToMenuView());
        leaderboard.addActionListener(evt -> statisticsController.switchToLeaderboardView());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (STATE_CHANGED.equals(evt.getPropertyName())) {
            updateTable((StatisticsState) evt.getNewValue());
        } else if (PASSWORD_CHANGED.equals(evt.getPropertyName())) {
            handlePasswordChange((StatisticsState) evt.getNewValue());
            passwordInputField.setText("");
        }
    }

    private void updateTable(StatisticsState state) {
        final User user = state.getUser();
        double winP = user.getGames() > 0 ? 1.0 * user.getWins() / user.getGames() : 0.00;

        tableModel.setValueAt(state.getUsername(), 0, 1);
        tableModel.setValueAt(String.valueOf(user.getBalance()), 1, 1);
        tableModel.setValueAt(String.valueOf(user.getWins()), 2, 1);
        tableModel.setValueAt(String.valueOf(user.getLosses()), 3, 1);
        tableModel.setValueAt(String.format("%.2f", winP), 4, 1);
        tableModel.setValueAt(String.valueOf(user.getGames()), 5, 1);
    }

    private void handlePasswordChange(StatisticsState state) {
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError());
        } else {
            JOptionPane.showMessageDialog(null, "Password updated for " + state.getUsername());
        }
    }

    private <T extends JTextComponent> T createStyledTextComponent(T textComponent) {
        textComponent.setBackground(AppColors.BRIGHT_GREEN);
        textComponent.setForeground(AppColors.YELLOW);
        textComponent.setFont(new Font("Serif", Font.PLAIN, 18));
        textComponent.setCaretColor(AppColors.YELLOW);
        textComponent.setPreferredSize(new Dimension(150, 30));
        return textComponent;
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(AppColors.YELLOW);
        button.setFont(new Font("Serif", Font.BOLD, 15));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(AppColors.YELLOW, 2));
        return button;
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
