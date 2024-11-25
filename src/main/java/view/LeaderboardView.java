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
import org.json.JSONArray;
import org.json.JSONObject;

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
                                currentState.getFriend(),
                                currentState.getUsername(),
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
                currentState.setFriend(friendInputField.getText());
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

            final JSONObject json = user.getInfo();
            final JSONArray list = json.getJSONArray("friends");

            for (int i = 0; i < list.length(); i++) {
                final String userString = list.get(i).toString();
                System.out.println("USERS: " + userString);

                // Extract data from the string
                if (userString.startsWith("This user {") && userString.endsWith("}")) {
                    // Remove the prefix and suffix
                    final String content = userString.substring("This user {".length(), userString.length() - 1);

                    // Split the key-value pairs
                    final String[] keyValuePairs = content.split(", ");
                    String username = "";
                    int balance = 0;
                    int wins = 0, losses = 0, games = 0;

                    // Extract values
                    for (String pair : keyValuePairs) {
                        final String[] keyValue = pair.split("=");
                        final String key = keyValue[0].trim();
                        final String value = keyValue[1].replace("'", "").trim();

                        switch (key) {
                            case "username":
                                username = value;
                                break;
                            case "balance":
                                balance = Integer.parseInt(value);
                                break;
                            case "wins":
                                wins = Integer.parseInt(value);
                                break;
                            case "losses":
                                losses = Integer.parseInt(value);
                                break;
                            case "games":
                                games = Integer.parseInt(value);
                                break;
                        }
                    }

                    // Calculate win percentage
                    final double winPercentage = (games > 0) ? (double) wins / games : 0;

                    // Add a row to the table
                    final Object[] newRow = {username,
                            String.valueOf(balance),
                            String.valueOf(wins),
                            String.valueOf(losses),
                            String.format("%.2f", winPercentage),
                            String.valueOf(games),
                    };
                    tableModel.addRow(newRow);
                }
                else {
                    System.err.println("Invalid user string format: " + userString);
                }
            }

        }

        else if (evt.getPropertyName().equals("friend")) {
            final LeaderboardState state = (LeaderboardState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "added friend " + state.getFriend());
            System.out.println("SWITCHED TO FRIEND: " + state.getUsername());

            final User user = state.getUser();
            final Object[] newRow = {
                    state.getUsername(),
                    String.valueOf(user.getBalance()),
                    String.valueOf(user.getWins()),
                    String.valueOf(user.getLosses()),
                    String.valueOf(user.getWins() / (user.getGames() + 1)),
                    String.valueOf(user.getGames()),
            };

            // Dynamically add the new row to the table
            tableModel.addRow(newRow);
        }
        else if (evt.getPropertyName().equals("return")) {
            final LeaderboardState state = (LeaderboardState) evt.getNewValue();
            System.out.println("SWITCHED TO RETURN: " + state.getUsername());
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
