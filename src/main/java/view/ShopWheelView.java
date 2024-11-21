package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.shop.wheel.ShopWheelController;
import interface_adapter.shop.wheel.ShopWheelState;
import interface_adapter.shop.wheel.ShopWheelViewModel;

/**
 * The View for the shop wheel screen.
 */
public class ShopWheelView extends JPanel implements PropertyChangeListener {

    private transient ShopWheelController shopWheelController;
    private final JLabel username;
    private final JLabel balance;

    public ShopWheelView(ShopWheelViewModel shopWheelViewModel) {

        final JLabel title = new JLabel(ShopWheelViewModel.TITLE_LABEL);
        final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(title);

        final JPanel tButtons = new JPanel();
        final JButton spinner = new JButton(ShopWheelViewModel.SPIN_BUTTON_LABEL);
        tButtons.add(spinner);
        final JButton back = new JButton(ShopWheelViewModel.SHOP_BUTTON_LABEL);
        tButtons.add(back);

        spinner.addActionListener(evt -> shopWheelController.wheelSpin());
        back.addActionListener(evt -> shopWheelController.switchToShopView());

        // Bottom panel for username and balance
        username = new JLabel("unknown username");
        balance = new JLabel("unknown balance");

        // Update labels when state changes
        shopWheelViewModel.addPropertyChangeListener(evt -> {
            if ("state".equals(evt.getPropertyName())) {
                final ShopWheelState updatedState = (ShopWheelState) evt.getNewValue();
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
            final ShopWheelState state = (ShopWheelState) evt.getNewValue();
            username.setText(state.getUser().getName());
            balance.setText(String.valueOf(state.getUser().getBalance()));
        }
    }

    public String getViewName() {
        return "shop wheel";
    }

    public void setShopWheelController(ShopWheelController controller) {
        this.shopWheelController = controller;
    }
}
