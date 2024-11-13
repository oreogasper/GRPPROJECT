package view;

import interface_adapter.menu.MenuController;
import interface_adapter.menu.MenuViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The View for the Welcome Use Case.
 */
public class MenuView extends JPanel {
    private final String viewName = "menu";

    private MenuController menuController;
    private final JButton stats;
    private final JButton blackjack;
    private final JButton gauntlet;
    private final JButton overUnder;
    private final JButton shop;

    public MenuView(MenuViewModel welcomeViewModel) {
        // welcomeViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(MenuViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel tButtons = new JPanel();
        stats = new JButton(MenuViewModel.STATS_BUTTON_LABEL);
        tButtons.add(stats);
        blackjack = new JButton(MenuViewModel.BLACKJACK_BUTTON_LABEL);
        tButtons.add(blackjack);
        gauntlet = new JButton(MenuViewModel.GAUNTLET_BUTTON_LABEL);
        tButtons.add(gauntlet);
        overUnder = new JButton(MenuViewModel.OVERUNDER_BUTTON_LABEL);
        tButtons.add(overUnder);
        shop = new JButton(MenuViewModel.SHOP_BUTTON_LABEL);
        tButtons.add(shop);

        stats.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        menuController.switchToStatisticsView();
                    }
                }
        );
        blackjack.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        menuController.switchToLoginView();
                    }
                }
        );
        gauntlet.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        menuController.switchToLoginView();
                    }
                }
        );
        overUnder.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        menuController.switchToLoginView();
                    }
                }
        );
        shop.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        menuController.switchToLoginView();
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

    public void setMenuController(MenuController controller) {
        this.menuController = controller;
    }
}
