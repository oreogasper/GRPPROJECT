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
 * The view class for the Shop button class.
 */
public class ShopButtonView extends JPanel implements PropertyChangeListener {

    private transient ShopButtonController shopButtonController;
    private final JLabel username;
    private final JLabel balance;
    private final JLabel clicksMade;

    public ShopButtonView(ShopButtonViewModel shopButtonViewModel) {
        final JLabel title = new JLabel(ShopButtonViewModel.TITLE_LABEL);
        final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(title);

        // Buttons
        final JPanel tButtons = new JPanel();
        final JButton clicker = new JButton(ShopButtonViewModel.CLICK_BUTTON_LABEL);
        final JButton back = new JButton(ShopButtonViewModel.SHOP_BUTTON_LABEL);
        tButtons.add(clicker);
        tButtons.add(back);

        clicker.addActionListener(
                evt -> shopButtonController.buttonClick(shopButtonViewModel.getState().getClicksMade()));
        back.addActionListener(evt -> shopButtonController.switchToShopView());

        // Labels
        username = new JLabel("Currently logged in: unknown");
        balance = new JLabel("Current balance: 0");
        clicksMade = new JLabel("Tokens earned this session: 0");

        final JPanel clickPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        clickPanel.add(new JLabel(ShopButtonViewModel.CLICK_LABEL));
        clickPanel.add(clicksMade);

        final JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        bottomPanel.add(username);
        bottomPanel.add(balance);

        // Combine center components
        final JPanel centerPanel = new JPanel(new GridLayout(2, 1));
        centerPanel.add(tButtons);
        centerPanel.add(clickPanel);

        // Set layout
        this.setLayout(new BorderLayout());
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

        // Register property change listener
        shopButtonViewModel.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            final ShopButtonState updatedState = (ShopButtonState) evt.getNewValue();
            username.setText("Currently logged in: " + updatedState.getUser().getName());
            balance.setText("Current balance: " + updatedState.getUser().getBalance());
            clicksMade.setText(String.valueOf(updatedState.getClicksMade() / ShopButtonViewModel.DIVIDER));
        }
    }

    public String getViewName() {
        return "shop button";
    }

    public void setShopButtonController(ShopButtonController controller) {
        this.shopButtonController = controller;
    }
}
