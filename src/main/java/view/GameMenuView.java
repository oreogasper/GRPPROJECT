package view;

import interface_adapter.menu.MenuController;
import interface_adapter.menu.MenuViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The View for the game menu.
 */
public class GameMenuView extends JPanel {
    private final String viewName = "game menu";

    private GameMenuController gameMenuController;
    private final JButton blackjack;
    private final JButton gauntlet;
    private final JButton overUnder;

    public GameMenuView(GameMenuViewModel gameMenuViewModel) {
        // welcomeViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(MenuViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel tButtons = new JPanel();
        blackjack = new JButton(MenuViewModel.BLACKJACK_BUTTON_LABEL);
        tButtons.add(blackjack);
        gauntlet = new JButton(MenuViewModel.GAUNTLET_BUTTON_LABEL);
        tButtons.add(gauntlet);
        overUnder = new JButton(MenuViewModel.OVERUNDER_BUTTON_LABEL);
        tButtons.add(overUnder);

        blackjack.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        GameMenuController.switchToLoginView();
                    }
                }
        );
        gauntlet.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        GameMenuController.switchToLoginView();
                    }
                }
        );
        overUnder.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        GameMenuController.switchToLoginView();
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(tButtons);
    }

    public String getViewName() {
        return viewName;
    }

    public void setGameMenuController(GameMenuController controller) {
        this.GameMenuController = controller;
    }
}
