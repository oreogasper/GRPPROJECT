package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.gamemenu.GameMenuController;
import interface_adapter.gamemenu.GameMenuState;
import interface_adapter.gamemenu.GameMenuViewModel;

/**
 * The View for the game menu.
 */
public class GameMenuView extends JPanel implements PropertyChangeListener {

    private transient GameMenuController gameMenuController;
    private final JLabel username;
    private final JLabel balance;

    public GameMenuView(GameMenuViewModel gameMenuViewModel) {

        final JLabel title = new JLabel(GameMenuViewModel.TITLE_LABEL);
        final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(title);

        final JPanel tButtons = new JPanel();
        final JButton blackjack = new JButton(GameMenuViewModel.BLACKJACK_BUTTON_LABEL);
        tButtons.add(blackjack);
        final JButton gauntlet = new JButton(GameMenuViewModel.GAUNTLET_BUTTON_LABEL);
        tButtons.add(gauntlet);
        final JButton overUnder = new JButton(GameMenuViewModel.OVERUNDER_BUTTON_LABEL);
        tButtons.add(overUnder);
        final JButton back = new JButton(GameMenuViewModel.BACK_BUTTON_LABEL);
        tButtons.add(back);

        blackjack.addActionListener(evt -> gameMenuController.switchToBlackjackView());
        gauntlet.addActionListener(evt -> gameMenuController.switchToGaunletView());
        overUnder.addActionListener(evt -> gameMenuController.switchToLoginView());
        back.addActionListener(evt -> gameMenuController.switchToMenuView());

        // Bottom panel for username and balance
        username = new JLabel("unknown username");
        balance = new JLabel("unknown balance");

        // Update labels when state changes
        gameMenuViewModel.addPropertyChangeListener(evt -> {
            if ("state".equals(evt.getPropertyName())) {
                final GameMenuState updatedState = (GameMenuState) evt.getNewValue();
                final String updatedName = updatedState.getUser().getName();
                final String updatedBalance = String.valueOf(updatedState.getUser().getBalance());

                username.setText("Currently logged in: " + updatedName);
                balance.setText("Current balance: " + updatedBalance);
            }
        });

        final JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        bottomPanel.add(username);
        bottomPanel.add(balance);

        // Set layout and add components
        this.setLayout(new BorderLayout());
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(tButtons, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
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
}
