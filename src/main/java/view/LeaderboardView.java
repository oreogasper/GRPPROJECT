package view;

import entity.User;
import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardState;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.statistics.ChangePasswordController;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for the Leaderboard Use Case.
 */
public class LeaderboardView extends JPanel implements PropertyChangeListener {
    private static final String INITIAL_VALUE = "0";
    private static final Integer TABLE_WIDTH = 200;
    private static final Integer TABLE_HEIGHT = 125;
    private static final Integer ROW_THREE = 3;
    private static final Integer ROW_FOUR = 4;
    private static final Integer ROW_FIVE = 5;

    private final String viewName = "leaderboard";
    private final LeaderboardViewModel leaderboardViewModel;
    private ChangePasswordController changePasswordController;
    private LeaderboardController leaderboardController;
    private final DefaultTableModel tableModel;
    private final JTable table;
    private final JTextField friendInputField = new JTextField(15);
    private final JButton remove;
    private final JButton cancel;

    public LeaderboardView(LeaderboardViewModel leaderboardViewModel) {
        this.leaderboardViewModel = leaderboardViewModel;
        this.leaderboardViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(LeaderboardViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel friendInput = new LabelTextPanel(
                new JLabel("Add friend:"), friendInputField);

        final JPanel buttons = new JPanel();
        remove = new JButton(LeaderboardViewModel.REMOVE_FRIENDS);
        buttons.add(remove);
        cancel = new JButton(LeaderboardViewModel.RETURN_STATS_BUTTON_LABEL);
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

        friendInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LeaderboardState currentState = leaderboardViewModel.getState();
                currentState.setPassword(friendInputField.getText());
                leaderboardViewModel.setState(currentState);
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

        remove.addActionListener(
                evt -> {
                    if (evt.getSource().equals(remove)) {
                        final LeaderboardState currentState = leaderboardViewModel.getState();
                        this.changePasswordController.execute(
                                currentState.getUsername(),
                                currentState.getPassword(),
                                currentState.getUser().getInfo());
                    }
                }
        );

        cancel.addActionListener(evt -> leaderboardController.switchToStatisticsView());

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(friendInput);
        final JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
        this.add(buttons);
        this.add(scrollPane);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final LeaderboardState state = (LeaderboardState) evt.getNewValue();
            final User user = state.getUser();

            tableModel.setValueAt(state.getUsername(), 0, 1);
            tableModel.setValueAt(String.valueOf(user.getBalance()), 1, 1);
            tableModel.setValueAt(String.valueOf(user.getWins()), 2, 1);
            tableModel.setValueAt(String.valueOf(user.getLosses()), ROW_THREE, 1);
            // TODO: when games is 0, add 1
            tableModel.setValueAt(String.valueOf(user.getWins() / (user.getGames() + 1)), ROW_FOUR, 1);
            tableModel.setValueAt(String.valueOf(user.getGames()), ROW_FIVE, 1);

        }

        else if (evt.getPropertyName().equals("friend")) {
            final LeaderboardState state = (LeaderboardState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "added friend " + state.getUsername());
        }

    }

    public String getViewName() {
        return viewName;
    }

    public void setLeaderboardController(LeaderboardController leaderboardController) {
        this.leaderboardController = leaderboardController;
    }

}
