package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.shop.wheel.ShopWheelController;
import interface_adapter.shop.wheel.ShopWheelViewModel;

/**
 * The View for the shop wheel screen.
 */
public class ShopWheelView extends JPanel {
    private final String viewName = "shop wheel";

    private ShopWheelController shopWheelController;
    private final JButton spinner;
    private final JButton back;

    public ShopWheelView(ShopWheelViewModel shopWheelViewModel) {

        final JLabel title = new JLabel(ShopWheelViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel tButtons = new JPanel();
        spinner = new JButton(ShopWheelViewModel.SPIN_BUTTON_LABEL);
        tButtons.add(spinner);
        back = new JButton(ShopWheelViewModel.SHOP_BUTTON_LABEL);
        tButtons.add(back);

        spinner.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        shopWheelController.wheelSpin();
                    }
                }
        );
        back.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        shopWheelController.switchToShopView();
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

    public void setShopWheelController(ShopWheelController controller) {
        this.shopWheelController = controller;
    }
}
