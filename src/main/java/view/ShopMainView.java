package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.shop.ShopController;
import interface_adapter.shop.ShopViewModel;

/**
 * The View for the shop main menu.
 */
public class ShopMainView extends JPanel {
    private final String viewName = "shop menu";

    private ShopController shopController;
    private final JButton wheel;
    private final JButton button;
    private final JButton back;

    public ShopMainView(ShopViewModel shopViewModel) {

        final JLabel title = new JLabel(ShopViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

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

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(tButtons);
    }

    public String getViewName() {
        return viewName;
    }

    public void setShopController(ShopController controller) {
        this.shopController = controller;
    }
}
