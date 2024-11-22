package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import interface_adapter.shop.ShopViewModel;
import interface_adapter.shop.wheel.ShopWheelController;
import interface_adapter.shop.wheel.ShopWheelState;
import interface_adapter.shop.wheel.ShopWheelViewModel;
import interface_adapter.shop.wheel.SpinningWheelButton;

/**
 * The View for the shop wheel screen.
 */
public class ShopWheelView extends JPanel {
    private transient ShopWheelController wheelController;
    private final JLabel username;
    private final JLabel balance;
    private final JLabel countdown;
    private final JButton spinButton;
    private final ShopWheelAnimationPanel wheelPanel;

    public ShopWheelView(ShopWheelViewModel shopWheelViewModel) {
        this.setLayout(new BorderLayout());

        // Title
        final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final JLabel title = new JLabel(ShopWheelViewModel.TITLE_LABEL);
        titlePanel.add(title);

        // Add the wheel button and countdown to spin panel
        final JPanel spinPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final SpinningWheelButton wheelButton = new SpinningWheelButton();
        spinPanel.add(wheelButton);
        countdown = new JLabel("Time until next available spin: 0");
        spinPanel.add(countdown);

        // Implement wheelButton functionality
        wheelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                wheelController.spinWheelRequest(shopWheelViewModel.getState().getUser().getLastSpin());
                startCountdown(shopWheelViewModel.getState().getUser().getLastSpin());
            }
        });

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
                startCountdown(updatedState.getUser().getLastSpin());
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

        bottomPanel.add(leftBottomPanel, BorderLayout.WEST);
        bottomPanel.add(rightBottomPanel, BorderLayout.EAST);

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

    private void startCountdown(long lastSpin) {
        countdownTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final long currentTime = System.currentTimeMillis();
                final long remainingSeconds = 600
                        - TimeUnit.MILLISECONDS.toSeconds(currentTime)
                        + TimeUnit.MILLISECONDS.toSeconds(lastSpin);

                if (remainingSeconds <= 0) {
                    countdown.setText("Spin is ready!");
                    countdownTimer.stop();
                }
                else {
                    countdown.setText("Time until next available spin: " + formatTime(remainingSeconds));
                }
            }
        });
        countdownTimer.start();
    }

    private String formatTime(long totalSeconds) {
        final long minutes = totalSeconds / 60;
        final long seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

}
