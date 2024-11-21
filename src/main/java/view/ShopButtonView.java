package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.shop.ShopViewModel;
import interface_adapter.shop.button.ShopButtonController;
import interface_adapter.shop.button.ShopButtonState;
import interface_adapter.shop.button.ShopButtonViewModel;

/**
 * The View for the shop button screen.
 */
public class ShopButtonView extends JPanel implements PropertyChangeListener {

    private transient ShopButtonController shopButtonController;
    private final JLabel username;
    private final JLabel balance;

    public ShopButtonView(ShopButtonViewModel shopButtonViewModel) {

        final JLabel title = new JLabel(ShopViewModel.TITLE_LABEL);
        final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(title);

        final JPanel tButtons = new JPanel();
        final JButton clicker = new JButton(ShopButtonViewModel.CLICK_BUTTON_LABEL);
        tButtons.add(clicker);
        final JButton back = new JButton(ShopButtonViewModel.SHOP_BUTTON_LABEL);
        tButtons.add(back);

        clicker.addActionListener(evt -> shopButtonController.buttonClick());
        back.addActionListener(evt -> shopButtonController.switchToShopView());

        // Bottom panel for username and balance
        username = new JLabel("unknown username");
        balance = new JLabel("unknown balance");

        // Update labels when state changes
        shopButtonViewModel.addPropertyChangeListener(evt -> {
            if ("state".equals(evt.getPropertyName())) {
                final ShopButtonState updatedState = (ShopButtonState) evt.getNewValue();
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
            final ShopButtonState state = (ShopButtonState) evt.getNewValue();
            username.setText(state.getUser().getName());
            balance.setText(String.valueOf(state.getUser().getBalance()));
        }
    }

    public String getViewName() {
        return "shop button";
    }

    public void setShopButtonController(ShopButtonController controller) {
        this.shopButtonController = controller;
    }
}
