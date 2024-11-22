package interface_adapter.shop.wheel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

import interface_adapter.ViewManagerModel;
import interface_adapter.shop.ShopState;
import interface_adapter.shop.ShopViewModel;
import use_case.shopwheel.ShopWheelOutputBoundary;

/**
 * The Presenter for the Shop wheel Use Case.
 */
public class ShopWheelPresenter implements ShopWheelOutputBoundary {
    private static final int FULL_CIRCLE = 360;
    private static final int WAIT_REQUIREMENT = 600000;
    private final ViewManagerModel viewManagerModel;
    private final ShopViewModel shopViewModel;
    private final ShopWheelViewModel shopWheelViewModel;
    private int currentAngle;
    private final int[] wheelSegments = {1, 2, 3, 4, 5, 6, 7, 8};
    private final Color[] segmentColors = {Color.RED.darker(), Color.GREEN.darker(),
            Color.YELLOW.darker(), Color.CYAN.darker(),
            Color.BLUE.darker(), Color.ORANGE.darker().darker().darker(),
            Color.PINK.darker(), Color.MAGENTA.darker()};
    private boolean isSpinning;
    private Timer timer;

    public ShopWheelPresenter(ViewManagerModel viewManagerModel,
                              ShopViewModel shopViewModel,
                              ShopWheelViewModel shopWheelViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.shopViewModel = shopViewModel;
        this.shopWheelViewModel = shopWheelViewModel;
    }

    @Override
    public void prepareSuccessView() {
        // No return statement here, as the method does not need to return anything.
    }

    @Override
    public void prepareFailView(String error) {
        // No return statement here either.
    }

    @Override
    public void switchToShopView() {
        final ShopState shopState = shopViewModel.getState();
        shopState.setUser(shopWheelViewModel.getState().getUser());
        this.shopViewModel.setState(shopState);
        this.shopViewModel.firePropertyChanged();

        viewManagerModel.setState(shopViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void spinWheel(int targetAngle) {
        if (isSpinning) {
            return;
        }
        isSpinning = true;

        final int fullDuration = 400;
        final int accelerationDuration = fullDuration / 2;
        final int maxSpeed = 20;
        final int timerDelay = 10;

        timer = new Timer(timerDelay, new ActionListener() {
            private int tick;

            @Override
            public void actionPerformed(ActionEvent e) {
                final double speed = getSpeed(tick);
                currentAngle = (int) ((currentAngle + speed) % 360);
                tick++;
                repaint();
                if (tick >= fullDuration) {
                    timer.stop();
                    isSpinning = false;
                }
            }

            private double getSpeed(int tick) {
                final double speed;
                if (tick < accelerationDuration) {
                    // Acceleration phase: use sinusoidal easing
                    speed = maxSpeed * Math.sin((Math.PI / 2) * tick / accelerationDuration);
                }
                else {
                    // Deceleration phase: symmetric easing
                    final int decelerationTick = tick - accelerationDuration;
                    speed = maxSpeed * Math.cos((Math.PI / 2) * decelerationTick / accelerationDuration);
                }
                return speed;
            }
        });
        timer.start();



        SpinningWheelButton.main();

        final long currentTime = System.currentTimeMillis();
        final long lastSpin = shopWheelViewModel.getState().getUser().getLastSpin();

        // Check if it's been less than 10 minutes since the last spin
        if (currentTime - lastSpin < WAIT_REQUIREMENT) {
            tooEarly();
        }

        this.spinningWheel();

        // Update last spin time after the spin starts
        shopWheelViewModel.getState().getUser().setLastSpin(currentTime);
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void spinningWheel() {
        new Thread(() -> {
            animateWheelSpin();
            final int prize = getRandomPrize();
            updatePrize(prize);
            showPrizeNotification(prize);
        }).start();
    }

    private void animateWheelSpin() {
        for (int i = 0; i < FULL_CIRCLE; i++) {
            try {
                shopWheelViewModel.setWheelAngle(i);
                Thread.sleep(10);
            }
            catch (InterruptedException evt) {
                evt.printStackTrace();
            }
        }
    }

    private int getRandomPrize() {
        final int maxTokens = 50;
        final Random random = new Random();
        return random.nextInt(maxTokens) + 1;
    }

    @Override
    public void updatePrize(int prize) {
        shopWheelViewModel.getState().givePrize(prize);
        shopWheelViewModel.firePropertyChanged();
    }

    private void showPrizeNotification(int prize) {
        JOptionPane.showMessageDialog(null, "You have won " + prize + " tokens!");
    }

    @Override
    public void tooEarly() {
        JOptionPane.showMessageDialog(null,
                "You must wait 10 minutes between spins!", "Too Early", JOptionPane.WARNING_MESSAGE);
    }
}
