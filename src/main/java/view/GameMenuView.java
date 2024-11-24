package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interface_adapter.gamemenu.GameMenuController;
import interface_adapter.gamemenu.GameMenuState;
import interface_adapter.gamemenu.GameMenuViewModel;

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

    private void setupUi(GameMenuViewModel gameMenuViewModel) {
        final JLabel title = new JLabel(GameMenuViewModel.TITLE_LABEL);
        final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(title);

        final JPanel tButtons = new JPanel(new GridLayout(3, 3));
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
        addButton(tButtons, GameMenuViewModel.BACK_BUTTON_LABEL, evt -> gameMenuController.switchToMenuView());

        final JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        bottomPanel.add(username);
        bottomPanel.add(balance);

        this.setLayout(new BorderLayout());
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(tButtons, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void addButton(JPanel panel, String label, ActionListener actionListener) {
        final JButton button = new JButton(label);
        button.addActionListener(actionListener);
        panel.add(button);
    }

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
        // No action needed
    }
}
