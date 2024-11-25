package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.menu.MenuController;
import interface_adapter.menu.MenuState;
import interface_adapter.menu.MenuViewModel;

/**
 * The View for the Welcome Use Case.
 */
public class MenuView extends JPanel implements PropertyChangeListener {

    private final MenuViewModel menuViewModel;
    private transient MenuController menuController;
    private final JLabel username;
    private final JLabel balance;

    public MenuView(MenuViewModel menuViewModel) {
        this.menuViewModel = menuViewModel;
        this.menuViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(MenuViewModel.TITLE_LABEL);
        final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(title);

        final JPanel tButtons = new JPanel();
        final JButton stats = new JButton(MenuViewModel.STATS_BUTTON_LABEL);
        tButtons.add(stats);
        final JButton gamble = new JButton(MenuViewModel.GAMBLE_BUTTON_LABEL);
        tButtons.add(gamble);
        final JButton shop = new JButton(MenuViewModel.SHOP_BUTTON_LABEL);
        tButtons.add(shop);

        stats.addActionListener(evt -> menuController.switchToStatisticsView());
        gamble.addActionListener(evt -> menuController.switchToGameMenuView());
        shop.addActionListener(evt -> menuController.switchToShopView());

        // Bottom panel for username and balance
        username = new JLabel("unknown username");
        balance = new JLabel("unknown balance");

        // Update labels when state changes
        menuViewModel.addPropertyChangeListener(evt -> {
            if ("state".equals(evt.getPropertyName())) {
                final MenuState updatedState = (MenuState) evt.getNewValue();
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
            final MenuState state = (MenuState) evt.getNewValue();
            username.setText(state.getUser().getName());
            balance.setText(String.valueOf(state.getUser().getBalance()));
        }
    }

    public String getViewName() {
        return "menu";
    }

    public void setMenuController(MenuController controller) {
        this.menuController = controller;
    }
}
