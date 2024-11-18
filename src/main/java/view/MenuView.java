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
    private final JButton gamble;
    private final JButton shop;
    private final JButton back;

    public MenuView(MenuViewModel welcomeViewModel) {
        // welcomeViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(MenuViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel tButtons = new JPanel();
        stats = new JButton(MenuViewModel.STATS_BUTTON_LABEL);
        tButtons.add(stats);
        gamble = new JButton(MenuViewModel.GAMBLE_BUTTON_LABEL);
        tButtons.add(gamble);
        shop = new JButton(MenuViewModel.SHOP_BUTTON_LABEL);
        tButtons.add(shop);
        back = new JButton(MenuViewModel.BACK_BUTTON_LABEL);
        tButtons.add(back);

        stats.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        menuController.switchToStatisticsView();
                    }
                }
        );
        gamble.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        menuController.switchToGameMenuView();
                    }
                }
        );
        shop.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        menuController.switchToShopView();
                    }
                }
        );
        back.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        menuController.switchToWelcomeView();
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
