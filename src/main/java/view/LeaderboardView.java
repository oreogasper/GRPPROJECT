package view;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import javax.swing.text.JTextComponent;

import entity.AppColors;
import entity.User;
import interface_adapter.add_friend.AddFriendController;
import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardState;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.remove_friend.RemoveFriendController;
import interface_adapter.statistics.StatisticsState;
import interface_adapter.statistics.StatisticsViewModel;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The View for the Leaderboard Use Case.
 */
public class LeaderboardView extends JPanel implements PropertyChangeListener {
    private static final Integer TABLE_WIDTH = 150;
    private static final Integer TABLE_HEIGHT = 100;
    private final String viewName = "leaderboard";
    private final LeaderboardViewModel leaderboardViewModel;
    private LeaderboardController leaderboardController;
    private AddFriendController addFriendController;
    private RemoveFriendController removeFriendController;

    private final DefaultTableModel tableModel;
    private final JTable table;
    private final JTextField friendInputField = createStyledTextComponent(new JTextField());
    private final JButton remove;
    private final JButton cancel;
    private final JButton addFriendButton;

    public LeaderboardView(LeaderboardViewModel leaderboardViewModel) {
        this.leaderboardViewModel = leaderboardViewModel;
        this.leaderboardViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(LeaderboardViewModel.TITLE_LABEL);
        title.setFont(new Font("Serif", Font.BOLD, 24));
        title.setForeground(AppColors.YELLOW);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel addFriend = new JLabel(LeaderboardViewModel.ADD_FRIEND_LABEL);
        addFriend.setFont(new Font("Serif", Font.BOLD, 15));
        addFriend.setForeground(AppColors.YELLOW);

        final LabelTextPanel friendInfo = new LabelTextPanel(addFriend, friendInputField);
        friendInfo.setBackground(AppColors.DARK_GREEN);

        final JPanel friend = new JPanel();
        friend.setBackground(AppColors.DARK_GREEN);
        addFriendButton = createStyledButton(LeaderboardViewModel.ADD_FRIEND_BUTTON_LABEL, AppColors.DARK_RED);
        addFriendButton.setPreferredSize(new Dimension(150, 30));
        friend.add(friendInfo);
        friend.add(addFriendButton);

        final JPanel buttons = new JPanel();
        buttons.setBackground(AppColors.DARK_GREEN);
        remove = createStyledButton(LeaderboardViewModel.REMOVE_FRIENDS_BUTTON_LABEL, AppColors.BRIGHT_GREEN);
        remove.setPreferredSize(new Dimension(200, 30));
        cancel = createStyledButton(LeaderboardViewModel.RETURN_STATS_BUTTON_LABEL, AppColors.DARK_RED);
        cancel.setPreferredSize(new Dimension(150, 30));
        buttons.add(remove);
        buttons.add(cancel);

        // Initializing table
        final String[] columnNames = {"Username", "Balance", "Wins", "Losses", "Win %", "Games"};
        tableModel = new DefaultTableModel(new String[0][0], columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        styleTable();
        // Inside your constructor, after initializing the table
        table.setAutoCreateRowSorter(true);
        final TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        final JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(AppColors.YELLOW);
        scrollPane.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
        this.add(scrollPane);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(AppColors.DARK_GREEN);
        this.add(Box.createVerticalStrut(20));
        this.add(title);
        this.add(Box.createVerticalStrut(10));
        this.add(friend);
        // this.add(Box.createVerticalStrut(10));
        this.add(scrollPane);
        this.add(Box.createVerticalStrut(10));
        this.add(buttons);

        addActionListeners();
    }
    private void styleTable() {
        // Table Appearance
        table.setBackground(AppColors.YELLOW);
        table.setForeground(AppColors.BLACK);
        table.setFont(new Font("Serif", Font.PLAIN, 15));
        table.setRowHeight(20);
        table.setShowGrid(true);
        table.setShowGrid(true);
        table.setGridColor(AppColors.BRIGHT_GREEN);

        // Header Appearance
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("Serif", Font.BOLD, 16));
        tableHeader.setBackground(AppColors.DARK_GREEN);
        tableHeader.setForeground(AppColors.DARK_GREEN);
        tableHeader.setReorderingAllowed(false);
    }
    private void addActionListeners() {
        addFriendButton.addActionListener(evt -> {
            final LeaderboardState currentState = leaderboardViewModel.getState();
            this.addFriendController.execute(
                    currentState.getFriend(),
                    currentState.getUsername(),
                    currentState.getUser().getInfo());
        });

        remove.addActionListener(evt -> {
            final LeaderboardState currentState = leaderboardViewModel.getState();
            this.removeFriendController.execute(
                    currentState.getUsername(),
                    currentState.getUser().getInfo());
        });

        cancel.addActionListener(evt -> leaderboardController.switchToStatisticsView());

        friendInputField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateFriendState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateFriendState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateFriendState();
            }

            private void updateFriendState() {
                final LeaderboardState currentState = leaderboardViewModel.getState();
                currentState.setFriend(friendInputField.getText());
                leaderboardViewModel.setState(currentState);
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            tableModel.setRowCount(0);
            final LeaderboardState state = (LeaderboardState) evt.getNewValue();
            final User user = state.getUser();

            double winP = 0.00;
            if (user.getGames() > 0) {
                winP = 1.0 * user.getWins() / user.getGames();
            }
            final Object[] newRow = {
                    state.getUsername(),
                    String.valueOf(user.getBalance()),
                    String.valueOf(user.getWins()),
                    String.valueOf(user.getLosses()),
                    String.format("%.2f", winP),
                    String.valueOf(user.getGames()),
            };
            // Check if the username is already in the table
            boolean exists = false;
            for (int row = 0; row < tableModel.getRowCount(); row++) {
                if (tableModel.getValueAt(row, 0).equals(state.getUsername())) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                tableModel.addRow(newRow);
            }

            final JSONObject json = user.getInfo();
            final JSONArray list = json.getJSONArray("friends");

            for (int i = 0; i < list.length(); i++) {
                final String userString = list.get(i).toString();
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

                    if (!exists) {
                        double winPe = 0.00;
                        if (games > 0) {
                            winPe = 1.0 * wins / games;
                        }

                        final Object[] newRowz = {username,
                                String.valueOf(balance),
                                String.valueOf(wins),
                                String.valueOf(losses),
                                String.format("%.2f", winPe),
                                String.valueOf(games),
                        };
                        tableModel.addRow(newRowz);
                    }
                }
            }
        }

        else if (evt.getPropertyName().equals("friend")) {
            final LeaderboardState statex = (LeaderboardState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "added friend " + statex.getFriend());

            final User userx = statex.getUser();
            double winP = 0.00;
            if (userx.getGames() > 0) {
                winP = 1.0 * userx.getWins() / userx.getGames();
            }
            final Object[] newRow = {
                    statex.getUsername(),
                    String.valueOf(userx.getBalance()),
                    String.valueOf(userx.getWins()),
                    String.valueOf(userx.getLosses()),
                    String.format("%.2f", winP),
                    String.valueOf(userx.getGames()),
            };
            tableModel.addRow(newRow);
        }
        else if (evt.getPropertyName().equals("reset")) {
            friendInputField.setText("");
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

    public void setLeaderboardController(LeaderboardController leaderboardController) {
        this.leaderboardController = leaderboardController;
    }

    public void setAddFriendController(AddFriendController addFriendController) {
        this.addFriendController = addFriendController;
    }
    public void setRemoveFriendController(RemoveFriendController removeFriendController) {
        this.removeFriendController = removeFriendController;
    }

}
