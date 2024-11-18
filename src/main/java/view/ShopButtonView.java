package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.shop.button.ShopButtonController;
import interface_adapter.shop.button.ShopButtonViewModel;

/**
 * The View for the shop button screen.
 */
public class ShopButtonView extends JPanel {
    private final String viewName = "shop button";

    private ShopButtonController shopButtonController;
    private final JButton clicker;
    private final JButton back;

    public ShopButtonView(ShopButtonViewModel shopButtonViewModel) {

        final JLabel title = new JLabel(ShopButtonViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel tButtons = new JPanel();
        clicker = new JButton(ShopButtonViewModel.CLICK_BUTTON_LABEL);
        tButtons.add(clicker);
        back = new JButton(ShopButtonViewModel.SHOP_BUTTON_LABEL);
        tButtons.add(back);

        clicker.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        shopButtonController.buttonClick();
                    }
                }
        );
        back.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        shopButtonController.switchToShopView();
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

    public void setShopButtonController(ShopButtonController controller) {
        this.shopButtonController = controller;
    }
}
