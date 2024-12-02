package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

import entity.AppColors;
import interface_adapter.gamemenu.GameMenuController;
import interface_adapter.gamemenu.GameMenuState;
import interface_adapter.gamemenu.GameMenuViewModel;
import interface_adapter.signup.SignupViewModel;

/**
 * The View for the game menu.
 */
public class GameMenuView extends JPanel implements ActionListener, PropertyChangeListener {
    private static final Color GREEN = AppColors.BRIGHT_GREEN;
    private static final Color RED = AppColors.DARK_RED;

    private static final String NEW_LINE = "\n";
    private static final String INSTRUCTIONS_TITLE = "Instructions";

    private transient GameMenuController gameMenuController;
    private final JLabel username;
    private final JLabel balance;

    public GameMenuView(GameMenuViewModel gameMenuViewModel) {
        this.username = new JLabel("unknown username");
        this.balance = new JLabel("unknown balance");

        setupUi(gameMenuViewModel);
        setupListeners(gameMenuViewModel);
    }

    // Set up the layout of the panel
    private void setupUi(GameMenuViewModel gameMenuViewModel) {
        final JLabel title = new JLabel(GameMenuViewModel.TITLE_LABEL);
        title.setFont(new Font("Serif", Font.BOLD, 30));
        title.setForeground(AppColors.YELLOW);

        final JPanel titlePanel = new JPanel();
        titlePanel.add(title);

        // Main panel for the buttons
        final JPanel tButtons = new JPanel();
        tButtons.setLayout(new BoxLayout(tButtons, BoxLayout.Y_AXIS));

        // First row with three square buttons
        final JPanel firstRow = new JPanel(new GridLayout(1, 3, 10, 0));
        addButton(firstRow, GameMenuViewModel.BLACKJACK_BUTTON_LABEL, evt -> gameMenuController.switchToBlackjackView(), RED);
        addButton(firstRow, GameMenuViewModel.GAUNTLET_BUTTON_LABEL, evt -> gameMenuController.switchToGaunletView(), RED);
        addButton(firstRow, GameMenuViewModel.OVERUNDER_BUTTON_LABEL, evt -> gameMenuController.switchToOverUnderBetView(), RED);
        tButtons.add(firstRow);
        tButtons.add(Box.createVerticalStrut(10)); // Add spacing between rows

        // Second row with three shorter but same-width buttons
        final JPanel secondRow = new JPanel(new GridLayout(1, 3, 8, 0));
        addButton(secondRow, GameMenuViewModel.BLACKJACK_RULES_BUTTON_LABEL, evt -> openRulesFile("game rules/blackjackRules"), GREEN);
        addButton(secondRow, GameMenuViewModel.GAUNTLET_RULES_BUTTON_LABEL, evt -> openRulesFile("game rules/gaunletRules"), GREEN);
        addButton(secondRow, GameMenuViewModel.OVERUNDER_RULES_BUTTON_LABEL, evt -> openRulesFile("game rules/overunderRules"), GREEN);
        tButtons.add(secondRow);
        tButtons.add(Box.createVerticalStrut(20)); // Add spacing for the last row

        // Bottom row with the centered Return button
        final JPanel bottomRow = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Center alignment
        addButton(bottomRow, GameMenuViewModel.BACK_BUTTON_LABEL, evt -> gameMenuController.switchToMenuView(), GREEN);
        tButtons.add(bottomRow);

        final JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        bottomPanel.add(username);
        username.setForeground(AppColors.YELLOW);
        username.setFont(new Font("Serif", Font.PLAIN, 15));
        bottomPanel.add(balance);
        balance.setForeground(AppColors.YELLOW);
        balance.setFont(new Font("Serif", Font.PLAIN, 15));

        // Styling for uniformity
        this.setBackground(AppColors.DARK_GREEN);
        titlePanel.setBackground(AppColors.DARK_GREEN);
        tButtons.setBackground(AppColors.DARK_GREEN);
        firstRow.setBackground(AppColors.DARK_GREEN);
        secondRow.setBackground(AppColors.DARK_GREEN);
        bottomRow.setBackground(AppColors.DARK_GREEN);
        bottomPanel.setBackground(AppColors.DARK_GREEN);

        this.setLayout(new BorderLayout(0, 25));
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(tButtons, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void addButton(JPanel panel, String label, ActionListener actionListener, Color bgColor) {
        // Create a styled button
        JButton button = createStyledButton(label, bgColor);
        button.addActionListener(actionListener);
        panel.add(button);
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(AppColors.YELLOW);
        button.setFont(new Font("Serif", Font.BOLD, 15));
        button.setFocusPainted(false);
        if (bgColor.equals(RED)) {
            button.setPreferredSize(new Dimension(150, 100));
        }
        else {
            button.setPreferredSize(new Dimension(150, 50));
        }

        button.setBorder(BorderFactory.createLineBorder(AppColors.YELLOW, 2));
        return button;
    }

    // for game rules button, reads the instruction file and put it in message box
    private void openRulesFile(String filePath) {
        final File file = new File(filePath);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            final StringBuilder content = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                content.append(line).append(NEW_LINE);
            }
            JOptionPane.showMessageDialog(null, content.toString(), INSTRUCTIONS_TITLE,
                    JOptionPane.INFORMATION_MESSAGE);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void setupListeners(GameMenuViewModel gameMenuViewModel) {
        gameMenuViewModel.addPropertyChangeListener(evt -> {
            if ("state".equals(evt.getPropertyName())) {
                final GameMenuState updatedState = (GameMenuState) evt.getNewValue();
                final String updatedName = updatedState.getUser().getName();
                final String updatedBalance = String.valueOf(updatedState.getUser().getBalance());

                username.setText("Currently logged in: " + updatedName);
                balance.setText("Current balance: " + updatedBalance);
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            final GameMenuState state = (GameMenuState) evt.getNewValue();
            username.setText(state.getUser().getName());
            balance.setText(String.valueOf(state.getUser().getBalance()));
        }
    }

    public String getViewName() {
        return "game menu";
    }

    public void setGameMenuController(GameMenuController controller) {
        this.gameMenuController = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

