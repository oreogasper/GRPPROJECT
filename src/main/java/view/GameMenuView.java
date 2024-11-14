package view;

import interface_adapter.gamemenu.GameMenuController;
import interface_adapter.gamemenu.GameMenuViewModel;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * The View for the game menu.
 */
public class GameMenuView extends JPanel {
    private final String viewName = "game menu";

    private GameMenuController gameMenuController;
    private final JButton blackjack;
    private final JButton gauntlet;
    private final JButton overUnder;
    private final JButton back;

    public GameMenuView(GameMenuViewModel gameMenuViewModel) {
        // welcomeViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(GameMenuViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel tButtons = new JPanel();
        blackjack = new JButton(GameMenuViewModel.BLACKJACK_BUTTON_LABEL);
        tButtons.add(blackjack);
        gauntlet = new JButton(GameMenuViewModel.GAUNTLET_BUTTON_LABEL);
        tButtons.add(gauntlet);
        overUnder = new JButton(GameMenuViewModel.OVERUNDER_BUTTON_LABEL);
        tButtons.add(overUnder);
        back = new JButton(GameMenuViewModel.BACK_BUTTON_LABEL);

        blackjack.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        gameMenuController.switchToLoginView();
                    }
                }
        );
        gauntlet.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        gameMenuController.switchToLoginView();
                    }
                }
        );
        overUnder.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        gameMenuController.switchToLoginView();
                    }
                }
        );
        back.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        gameMenuController.switchToMenuView();
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
        this.gameMenuController = controller;
    }
}
