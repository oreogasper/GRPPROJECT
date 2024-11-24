package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.TimeUnit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import interface_adapter.shop.ShopViewModel;
import interface_adapter.shop.wheel.ShopWheelController;
import interface_adapter.shop.wheel.ShopWheelState;
import interface_adapter.shop.wheel.ShopWheelViewModel;

/**
 * The View for the shop wheel screen.
 */
public class ShopWheelView extends JPanel {
    private transient ShopWheelController wheelController;
    private final JLabel username;
    private final JLabel balance;
    private final JLabel countdown;
    private final int timeDivider = 60;
    private Timer countdownTimer;

    public ShopWheelView(ShopWheelViewModel shopWheelViewModel) {
        this.setLayout(new BorderLayout());

        // Title
        final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final JLabel title = new JLabel(ShopWheelViewModel.TITLE_LABEL);
        titlePanel.add(title);

        // Add the wheel animation and wheel button to spin panel
        final ShopWheelAnimationPanel animationPanel = new ShopWheelAnimationPanel();
        shopWheelViewModel.setAnimationPanel(animationPanel);

        final JPanel spinPanel = new JPanel(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        final int padding = 5;
        gbc.insets = new Insets(padding, padding, padding, padding);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        final JButton spinButton = new JButton("Spin the wheel!");
        spinButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                final long lastSpin = shopWheelViewModel.getState().getUser().getLastSpin();
                wheelController.spinWheelRequest(lastSpin, shopWheelViewModel);
                if (shopWheelViewModel.getState().getWasSpun()) {
                    startCountdown(lastSpin, shopWheelViewModel);
                    shopWheelViewModel.getState().setWasSpun(false);
                }
            }
        });

        spinPanel.add(animationPanel, gbc);

        final JPanel rightSpinPanel = new JPanel();
        rightSpinPanel.setLayout(new BoxLayout(rightSpinPanel, BoxLayout.Y_AXIS));

        // Add countdown at the bottom of the spin panel
        countdown = new JLabel("Time until next available spin: 0");
        countdown.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightSpinPanel.add(countdown);
        rightSpinPanel.add(Box.createVerticalStrut(padding));

        spinButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightSpinPanel.add(spinButton);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        spinPanel.add(rightSpinPanel, gbc);

        // Bottom panel for username and balance
        username = new JLabel("unknown username");
        balance = new JLabel("unknown balance");

        // Update labels when state changes
        shopWheelViewModel.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("state".equals(evt.getPropertyName())) {
                    final ShopWheelState updatedState = (ShopWheelState) evt.getNewValue();
                    final String updatedName = updatedState.getUser().getName();
                    final String updatedBalance = String.valueOf(updatedState.getUser().getBalance());
                    username.setText("Currently logged in: " + updatedName);
                    balance.setText("Current balance: " + updatedBalance);
                    if (countdownTimer == null || !countdownTimer.isRunning()) {
                        startCountdown(updatedState.getUser().getLastSpin(), shopWheelViewModel);
                    }
                }
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

    private void startCountdown(long lastSpin, ShopWheelViewModel shopWheelViewModel) {
        final int countdownDelay = 1000;
        if (countdownTimer != null && countdownTimer.isRunning()) {
            countdownTimer.stop();
        }

        countdownTimer = new Timer(countdownDelay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final long currentTime = System.currentTimeMillis();
                final long remainingSeconds = shopWheelViewModel.getState().getWaitRequirement()
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
        final long minutes = totalSeconds / timeDivider;
        final long seconds = totalSeconds % timeDivider;
        return String.format("%02d:%02d", minutes, seconds);
    }

}
