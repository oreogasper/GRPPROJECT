package view;

import java.awt.*;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import entity.AppColors;
import interface_adapter.shop.wheel.ShopWheelController;
import interface_adapter.shop.wheel.ShopWheelState;
import interface_adapter.shop.wheel.ShopWheelViewModel;
import org.jetbrains.annotations.NotNull;

/**
 * The View for the shop wheel screen.
 */
public class ShopWheelView extends JPanel {
    private static final String FONT_NAME = "Serif";
    private transient ShopWheelController wheelController;
    private final JLabel username;
    private final JLabel balance;
    private final JLabel countdown;
    private Timer countdownTimer;

    public ShopWheelView(ShopWheelViewModel shopWheelViewModel) {
        this.setLayout(new BorderLayout());
        this.setBackground(AppColors.DARK_GREEN);

        // Title
        final JLabel title = new JLabel(ShopWheelViewModel.TITLE_LABEL);
        title.setFont(new Font (FONT_NAME, Font.BOLD, 30));
        title.setForeground(AppColors.YELLOW);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        final JPanel titlePanel = new JPanel();
        titlePanel.setBackground(AppColors.DARK_GREEN);
        titlePanel.add(title);

        // Add the wheel animation and wheel button to spin panel
        final ShopWheelAnimationPanel animationPanel = new ShopWheelAnimationPanel();
        shopWheelViewModel.setAnimationPanel(animationPanel);
        animationPanel.setBackground(AppColors.DARK_GREEN);

        final JPanel spinPanel = new JPanel(new GridBagLayout());
        spinPanel.setBackground(AppColors.DARK_GREEN);
        final GridBagConstraints gbc = new GridBagConstraints();
        final int padding = 5;
        gbc.insets = new Insets(padding, padding, padding, padding);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        final JButton spinButton = createStyledButton(ShopWheelViewModel.SPIN_LABEL);
        spinButton.setFont(new Font (FONT_NAME, Font.PLAIN, 15));
        spinButton.setPreferredSize(new Dimension(120, 25));
        spinButton.setForeground(AppColors.YELLOW);

        spinButton.addActionListener(evt -> {
            final long lastSpin = shopWheelViewModel.getState().getUser().getLastSpin();
            wheelController.spinWheelRequest(lastSpin, shopWheelViewModel);
            if (shopWheelViewModel.getState().getWasSpun()) {
                startCountdown(shopWheelViewModel);
                shopWheelViewModel.getState().setWasSpun(false);
            }
        });

        spinPanel.add(animationPanel, gbc);

        final JPanel rightSpinPanel = new JPanel();
        rightSpinPanel.setBackground(AppColors.DARK_GREEN);
        rightSpinPanel.setLayout(new BoxLayout(rightSpinPanel, BoxLayout.Y_AXIS));

        // Add countdown at the bottom of the spin panel
        countdown = new JLabel("Time until next available spin: 0");
        countdown.setForeground(AppColors.YELLOW);
        countdown.setFont(new Font (FONT_NAME, Font.PLAIN, 12));
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
        username.setForeground(AppColors.YELLOW);
        username.setFont(new Font(FONT_NAME, Font.PLAIN, 15));
        balance = new JLabel("unknown balance");
        balance.setForeground(AppColors.YELLOW);
        balance.setFont(new Font(FONT_NAME, Font.PLAIN, 15));

        // Update labels when state changes
        shopWheelViewModel.addPropertyChangeListener(evt -> {
            if ("state".equals(evt.getPropertyName())) {
                final ShopWheelState updatedState = (ShopWheelState) evt.getNewValue();
                final String updatedName = updatedState.getUser().getName();
                final String updatedBalance = String.valueOf(updatedState.getUser().getBalance());
                username.setText("Currently logged in: " + updatedName);
                balance.setText("Current balance: " + updatedBalance);
                if (countdownTimer == null || !countdownTimer.isRunning()) {
                    startCountdown(shopWheelViewModel);
                }
            }
        });

        // Bottom info and buttons
        final JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(AppColors.DARK_GREEN);

        final JPanel leftBottomPanel = new JPanel();
        leftBottomPanel.setBackground(AppColors.DARK_GREEN);
        leftBottomPanel.setLayout(new BoxLayout(leftBottomPanel, BoxLayout.Y_AXIS));
        leftBottomPanel.add(username);
        leftBottomPanel.add(balance);

        final JPanel rightBottomPanel = getRightBottomPanel(shopWheelViewModel);
        rightBottomPanel.setBackground(AppColors.DARK_GREEN);
        bottomPanel.add(leftBottomPanel, BorderLayout.WEST);
        bottomPanel.add(rightBottomPanel, BorderLayout.EAST);

        // Set layout and add components
        this.setLayout(new BorderLayout());
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(spinPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

    }

    @NotNull
    private JPanel getRightBottomPanel(ShopWheelViewModel shopWheelViewModel) {
        final JButton back = createStyledButton(ShopWheelViewModel.SHOP_BUTTON_LABEL);
        back.addActionListener(evt -> {
            wheelController.switchToShopView();
            wheelController.saveData(shopWheelViewModel.getState().getUser().getName(),
                    shopWheelViewModel.getState().getUser().getPassword(),
                    shopWheelViewModel.getState().getUser().getBalance(),
                    shopWheelViewModel.getState().getUser().getLastSpin());
        });
        back.setFont(new Font(FONT_NAME, Font.BOLD, 15));
        back.setPreferredSize(new Dimension(120, 25));
        final JPanel rightBottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightBottomPanel.add(back);
        return rightBottomPanel;
    }

    public String getViewName() {
        return "shop wheel";
    }

    public void setShopWheelController(ShopWheelController shopWheelController) {
        this.wheelController = shopWheelController;
    }

    private void startCountdown(ShopWheelViewModel shopWheelViewModel) {
        final int countdownDelay = 1000;
        if (countdownTimer != null && countdownTimer.isRunning()) {
            countdownTimer.stop();
        }

        countdownTimer = new Timer(countdownDelay, e -> {
            final long currentTime = System.currentTimeMillis();
            final long remainingSeconds = shopWheelViewModel.getState().getWaitRequirement()
                    - TimeUnit.MILLISECONDS.toSeconds(currentTime)
                    + TimeUnit.MILLISECONDS.toSeconds(shopWheelViewModel.getState().getUser().getLastSpin());

            if (remainingSeconds <= 0) {
                countdown.setText("Spin is ready!");
                countdownTimer.stop();
            }
            else {
                countdown.setText("Time until next available spin: " + formatTime(remainingSeconds));
            }
        });
        countdownTimer.start();
    }

    private String formatTime(long totalSeconds) {
        final int timeDivider = 60;
        final long minutes = totalSeconds / timeDivider;
        final long seconds = totalSeconds % timeDivider;
        return String.format("%02d:%02d", minutes, seconds);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(AppColors.DARK_GREEN);
        return getjButton(button);
    }

    @NotNull
    static JButton getjButton(JButton button) {
        return ShopButtonView.getjButton(button, FONT_NAME);
    }

}
