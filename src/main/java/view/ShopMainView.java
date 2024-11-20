package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import interface_adapter.shop.ShopController;
import interface_adapter.shop.ShopState;
import interface_adapter.shop.ShopViewModel;

/**
 * The View for the shop main menu.
 */
public class ShopMainView extends JPanel implements PropertyChangeListener {
    private final String viewName = "shop menu";

    private ShopController shopController;
    private final ShopViewModel shopViewModel;
    private final JButton wheel;
    private final JButton button;
    private final JButton back;
    private final JLabel username;
    private final JLabel balance;

    public ShopMainView(ShopViewModel shopViewModel) {

        this.shopViewModel = shopViewModel;

        final JLabel title = new JLabel(ShopViewModel.TITLE_LABEL);

        // Center title
        final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(title);

        // Add buttons
        final JPanel tButtons = new JPanel();
        wheel = new JButton(ShopViewModel.WHEEL_BUTTON_LABEL);
        tButtons.add(wheel);
        button = new JButton(ShopViewModel.BUTTON_BUTTON_LABEL);
        tButtons.add(button);
        back = new JButton(ShopViewModel.BACK_BUTTON_LABEL);
        tButtons.add(back);

        wheel.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        shopController.switchToShopWheelView();
                    }
                }
        );
        button.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        shopController.switchToShopButtonView();
                    }
                }
        );
        back.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        shopController.switchToMenuView();
                    }
                }
        );

        // Bottom panel for username and balance
        username = new JLabel("unknown username");
        balance = new JLabel("unknown balance");

        // Update labels when state changes
        shopViewModel.addPropertyChangeListener(evt -> {
            if ("state".equals(evt.getPropertyName())) {
                final ShopState updatedState = (ShopState) evt.getNewValue();
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
            final ShopState state = (ShopState) evt.getNewValue();
            username.setText(state.getUsername());
            balance.setText(String.valueOf(state.getBalance()));
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setShopController(ShopController controller) {
        this.shopController = controller;
    }
}
