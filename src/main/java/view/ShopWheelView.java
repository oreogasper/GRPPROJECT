package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import interface_adapter.shop.ShopViewModel;
import interface_adapter.shop.wheel.ShopWheelController;
import interface_adapter.shop.wheel.ShopWheelState;
import interface_adapter.shop.wheel.ShopWheelViewModel;
import view.SpinningWheelButton;

/**
 * The View for the shop wheel screen.
 */
public class ShopWheelView extends JPanel {
    private transient ShopWheelController wheelController;
    private final JLabel username;
    private final JLabel balance;

    public ShopWheelView(ShopWheelViewModel viewModel) {
        this.setLayout(new BorderLayout());

        // Title
        final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final JLabel title = new JLabel(ShopWheelViewModel.TITLE_LABEL);
        titlePanel.add(title);

        // Add the wheel button and countdown to spin panel
        final JPanel spinPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final SpinningWheelButton wheelButton = new SpinningWheelButton();
        spinPanel.add(wheelButton);
        final JLabel countdownLabel = new JLabel("Time until next available spin: 0");
        spinPanel.add(countdownLabel);

        // Implement wheelButton functionality
        wheelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                wheelController.spinWheel(viewModel.getState().getUser().getLastSpin());
            }
        });

        // Bottom panel for username and balance
        username = new JLabel("unknown username");
        balance = new JLabel("unknown balance");

        // Update labels when state changes
        viewModel.addPropertyChangeListener(evt -> {
            if ("state".equals(evt.getPropertyName())) {
                final ShopWheelState updatedState = (ShopWheelState) evt.getNewValue();
                final String updatedName = updatedState.getUser().getName();
                final String updatedBalance = String.valueOf(updatedState.getUser().getBalance());

                username.setText("Currently logged in: " + updatedName);
                balance.setText("Current balance: " + updatedBalance);
            }
        });

        // Bottom info and buttons
        final JPanel bottomPanel = new JPanel(new BorderLayout());

        final JPanel leftBottomPanel = new JPanel();
        leftBottomPanel.setLayout(new BoxLayout(leftBottomPanel, BoxLayout.Y_AXIS));
        leftBottomPanel.add(username);
        leftBottomPanel.add(balance);

        final JButton back = new JButton(ShopViewModel.BACK_BUTTON_LABEL);
        back.addActionListener(evt -> wheelController.switchToShopView());
        final JPanel rightBottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightBottomPanel.add(back);

        bottomPanel.add(leftBottomPanel);
        bottomPanel.add(rightBottomPanel);

        // Set layout and add components
        this.setLayout(new BorderLayout());
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(spinPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

    }

    public String getViewName() {
        return "shop wheel";
    }

    public void setShopWheelController(ShopWheelController shopWheelController) {
        this.wheelController = shopWheelController;
    }
}
