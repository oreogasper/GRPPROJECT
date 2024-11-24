package interface_adapter.shop.wheel;

import javax.swing.JOptionPane;

import interface_adapter.ViewManagerModel;
import interface_adapter.shop.ShopState;
import interface_adapter.shop.ShopViewModel;
import use_case.shopwheel.ShopWheelOutputBoundary;

import java.util.concurrent.TimeUnit;

/**
 * The Presenter for the Shop wheel Use Case.
 */
public class ShopWheelPresenter implements ShopWheelOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final ShopViewModel shopViewModel;
    private final ShopWheelViewModel shopWheelViewModel;
    private boolean isSpinning;

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
    public void spinWheel(int targetAngle, int prize) {
        if (!isSpinning) {
            isSpinning = true;
            shopWheelViewModel.getState().setWasSpun(true);

            // Start the wheel animation
            shopWheelViewModel.getAnimationPanel().startSpinAnimation(targetAngle, () -> {
                isSpinning = false;
                givePrize(prize);
            });

            // Update last spin time after the animation ends
            final long currentTime = System.currentTimeMillis();
            shopWheelViewModel.getState().getUser().setLastSpin(currentTime);
            viewManagerModel.firePropertyChanged();
        }
    }

    @Override
    public void givePrize(int prize) {
        shopWheelViewModel.getState().givePrize(prize);
        shopWheelViewModel.firePropertyChanged();
        JOptionPane.showMessageDialog(null, "You have won " + prize + " tokens!");
    }

    @Override
    public void tooEarly() {
        final String waitRequirementMinutes =
                String.valueOf(TimeUnit.SECONDS.toMinutes(shopWheelViewModel.getState().getWaitRequirement()));
        JOptionPane.showMessageDialog(null,
                "You must wait " + waitRequirementMinutes
                        + " minutes between spins!", "Too Early", JOptionPane.WARNING_MESSAGE);
    }
}
