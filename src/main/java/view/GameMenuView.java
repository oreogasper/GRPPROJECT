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
        final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(title);
        title.setFont(new Font("Serif", Font.BOLD, gameMenuViewModel.TITLE_SIZE));
        title.setForeground(AppColors.YELLOW);
        titlePanel.setBackground(AppColors.DARK_RED);

        final JPanel tButtons = new JPanel(new GridLayout(2, 3));
        tButtons.setBackground(AppColors.DARK_GREEN);
        addButton(tButtons, GameMenuViewModel.BLACKJACK_BUTTON_LABEL,
                evt -> gameMenuController.switchToBlackjackView());
        addButton(tButtons, GameMenuViewModel.GAUNTLET_BUTTON_LABEL,
                evt -> gameMenuController.switchToGaunletView());
        addButton(tButtons, GameMenuViewModel.OVERUNDER_BUTTON_LABEL,
                evt -> gameMenuController.switchToLoginView());
        addButton(tButtons, GameMenuViewModel.BLACKJACK_RULES_BUTTON_LABEL,
                evt -> openRulesFile("game rules/blackjackRules"));
        addButton(tButtons, GameMenuViewModel.GAUNTLET_RULES_BUTTON_LABEL,
                evt -> openRulesFile("game rules/gaunletRules"));
        addButton(tButtons, GameMenuViewModel.OVERUNDER_RULES_BUTTON_LABEL,
                evt -> openRulesFile("game rules/overunderRules"));

        final JPanel backButtons = new JPanel(new GridLayout(1, 1));
        addButton(backButtons, GameMenuViewModel.BACK_BUTTON_LABEL, evt -> gameMenuController.switchToMenuView());

        final JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        bottomPanel.add(username);
        username.setFont(new Font("Serif", Font.PLAIN, gameMenuViewModel.SUBTITLE_SIZE));
        username.setForeground(AppColors.YELLOW);
        bottomPanel.add(balance);
        balance.setFont(new Font("Serif", Font.PLAIN, gameMenuViewModel.SUBTITLE_SIZE));
        balance.setForeground(AppColors.YELLOW);
        bottomPanel.setBackground(AppColors.DARK_RED);

        this.setLayout(new BorderLayout());
        this.setBackground(AppColors.DARK_GREEN);
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(tButtons, BorderLayout.CENTER);
        backButtons.add(bottomPanel, BorderLayout.NORTH);
        this.add(backButtons, BorderLayout.SOUTH);
    }

    // adding button helper function
    private void addButton(JPanel panel, String label, ActionListener actionListener) {
        JButton button = new JButton(label);
        button = createStyledButton(label, AppColors.BRIGHT_GREEN);
        button.addActionListener(actionListener);
        panel.add(button);
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

    private JButton createStyledButton(String text, Color bgColor) {
        final JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(AppColors.YELLOW);
        button.setFont(new Font("Serif", Font.BOLD, 15));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(180, 50));
        button.setBorder(BorderFactory.createLineBorder(AppColors.YELLOW, 2));
        return button;
    }
}
