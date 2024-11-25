package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.shop.ShopController;
import interface_adapter.shop.ShopState;
import interface_adapter.shop.ShopViewModel;

/**
 * The View for the shop main menu.
 */
public class ShopMainView extends JPanel implements PropertyChangeListener {

    private transient ShopController shopController;
    private final JLabel username;
    private final JLabel balance;

    public ShopMainView(ShopViewModel shopViewModel) {

        final JLabel title = new JLabel(ShopViewModel.TITLE_LABEL);
        final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(title);

        // Add buttons
        final JPanel tButtons = new JPanel();
        final JButton wheel = new JButton(ShopViewModel.WHEEL_BUTTON_LABEL);
        tButtons.add(wheel);
        final JButton button = new JButton(ShopViewModel.BUTTON_BUTTON_LABEL);
        tButtons.add(button);

        wheel.addActionListener(evt -> shopController.switchToShopWheelView());
        button.addActionListener(evt -> shopController.switchToShopButtonView());

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

        final JPanel bottomPanel = new JPanel(new BorderLayout());

        final JPanel leftBottomPanel = new JPanel();
        leftBottomPanel.setLayout(new BoxLayout(leftBottomPanel, BoxLayout.Y_AXIS));
        leftBottomPanel.add(username);
        leftBottomPanel.add(balance);

        final JButton back = new JButton(ShopViewModel.BACK_BUTTON_LABEL);
        back.addActionListener(evt -> shopController.switchToMenuView());

        final JPanel rightBottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightBottomPanel.add(back);

        bottomPanel.add(leftBottomPanel, BorderLayout.WEST);
        bottomPanel.add(rightBottomPanel, BorderLayout.EAST);

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
            username.setText(state.getUser().getName());
            balance.setText(String.valueOf(state.getUser().getBalance()));
        }
    }

    public String getViewName() {
        return "shop menu";
    }

    public void setShopController(ShopController controller) {
        this.shopController = controller;
    }
}
