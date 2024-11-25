package view;

import java.awt.Component;
import java.awt.Dimension;
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
import javax.swing.table.TableRowSorter;

import entity.User;
import interface_adapter.add_friend.AddFriendController;
import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardState;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.statistics.StatisticsState;

/**
 * The View for the Leaderboard Use Case.
 */
public class LeaderboardView extends JPanel implements PropertyChangeListener {
    private static final Integer TABLE_WIDTH = 200;
    private static final Integer TABLE_HEIGHT = 125;
    private static final Integer LOSS_COL = 3;
    private static final Integer WINP_COL = 4;
    private static final Integer GAMES_COL = 5;

    private final String viewName = "leaderboard";
    private final LeaderboardViewModel leaderboardViewModel;
    private LeaderboardController leaderboardController;
    private AddFriendController addFriendController;
    private final DefaultTableModel tableModel;
    private final JTable table;
    private final JTextField friendInputField = new JTextField(15);
    private final JButton remove;
    private final JButton cancel;
    private final JButton addFriend;

    public LeaderboardView(LeaderboardViewModel leaderboardViewModel) {
        this.leaderboardViewModel = leaderboardViewModel;
        this.leaderboardViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(LeaderboardViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel friend = new JPanel();
        final LabelTextPanel friendInput = new LabelTextPanel(
                new JLabel("Type a Friend's username:"), friendInputField);
        friend.add(friendInput);

        addFriend = new JButton("Add Friend");
        friend.add(addFriend);
        addFriend.addActionListener(
                evt -> {
                    if (evt.getSource().equals(addFriend)) {
                        final LeaderboardState currentState = leaderboardViewModel.getState();
                        this.addFriendController.execute(
                                currentState.getUsername(),
                                currentState.getPassword(),
                                currentState.getUser().getInfo());
                    }
                }
        );

        final JPanel buttons = new JPanel();
        remove = new JButton(LeaderboardViewModel.REMOVE_FRIENDS);
        buttons.add(remove);
        cancel = new JButton(LeaderboardViewModel.RETURN_STATS_BUTTON_LABEL);
        buttons.add(cancel);

        // Initializing table
        final String[] columnNames = {"Username", "Balance", "Wins", "Losses", "Win %", "Games"};
        final String[][] initialData = {
                {},
                {"Friend 1"},
                {"Friend 2"},
                {"Friend 3"},
                {"Friend 4"},
                {"Friend 5"},
        };
        tableModel = new DefaultTableModel(initialData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        // Inside your constructor, after initializing the table
        table.setAutoCreateRowSorter(true);

        // Create a TableRowSorter for your table's model
        final TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

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

        // TODO: IMPLEMENT LATER, IGNORE FOR NOW
        remove.addActionListener(
                evt -> {
                    if (evt.getSource().equals(remove)) {
                        final LeaderboardState currentState = leaderboardViewModel.getState();
                        this.addFriendController.execute(
                                currentState.getUsername(),
                                currentState.getPassword(),
                                currentState.getUser().getInfo());
                    }
                }
        );

        cancel.addActionListener(evt -> leaderboardController.switchToStatisticsView());

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(friend);
        final JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
        this.add(scrollPane);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final LeaderboardState state = (LeaderboardState) evt.getNewValue();
            final User user = state.getUser();

            tableModel.setValueAt(state.getUsername(), 0, 0);
            tableModel.setValueAt(String.valueOf(user.getBalance()), 0, 1);
            tableModel.setValueAt(String.valueOf(user.getWins()), 0, 2);
            tableModel.setValueAt(String.valueOf(user.getLosses()), 0, LOSS_COL);
            tableModel.setValueAt(String.valueOf(user.getWins() / (user.getGames() + 1)), 0, WINP_COL);
            tableModel.setValueAt(String.valueOf(user.getGames()), 0, GAMES_COL);

        }

        else if (evt.getPropertyName().equals("friend")) {
            final LeaderboardState state = (LeaderboardState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "added friend " + state.getUsername());

            final User user = state.getUser();
            tableModel.setValueAt(state.getUsername(), 1, 0);
            tableModel.setValueAt(String.valueOf(user.getBalance()), 1, 1);
            tableModel.setValueAt(String.valueOf(user.getWins()), 1, 2);
            tableModel.setValueAt(String.valueOf(user.getLosses()), 1, LOSS_COL);
            tableModel.setValueAt(String.valueOf(user.getWins() / (user.getGames() + 1)), 1, WINP_COL);
            tableModel.setValueAt(String.valueOf(user.getGames()), 1, GAMES_COL);
        }

    }

    public String getViewName() {
        return viewName;
    }

    public void setLeaderboardController(LeaderboardController leaderboardController) {
        this.leaderboardController = leaderboardController;
    }

    public void setAddFriendController(AddFriendController addFriendController) {
        this.addFriendController = addFriendController;
    }

}
