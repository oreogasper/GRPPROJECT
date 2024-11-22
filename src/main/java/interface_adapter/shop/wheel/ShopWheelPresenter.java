package interface_adapter.shop.wheel;

import java.util.Random;

import javax.swing.JOptionPane;

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
    public void spinWheel() {
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

    private void updatePrize(int prize) {
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
