package interface_adapter.shop.wheel;

import java.util.concurrent.TimeUnit;

import use_case.shopwheel.ShopWheelInputBoundary;

/**
 * The controller for the shop wheel Use Case.
 */
public class ShopWheelController {

    private final ShopWheelInputBoundary userShopWheelUseCaseInteractor;

    public ShopWheelController(ShopWheelInputBoundary shopWheelUseCaseInteractor) {
        this.userShopWheelUseCaseInteractor = shopWheelUseCaseInteractor;
    }

    /**
     * Switches (back) to the shop's main menu view.
     */
    public void switchToShopView() {
        userShopWheelUseCaseInteractor.switchToShopView();
    }

    /**
     * Returns whether the user has waited long enough for the next spin.
     * @param currentTime is user's current system time.
     * @param lastSpin is the last time the user spun the wheel,
     * @param waitTimeSeconds is the required time to wait, in seconds.
     * @return if the user has waited enough.
     */
    public static boolean waitedEnough(long currentTime, long lastSpin, long waitTimeSeconds) {
        return TimeUnit.MILLISECONDS.toSeconds(currentTime)
                - TimeUnit.MILLISECONDS.toSeconds(lastSpin) > waitTimeSeconds;
    }

    /**
     * Sends the update request for button clicking.
     * @param lastSpin is the time the user last spun the wheel.
     * @param shopWheelViewModel is the accessor to the current state.
     */
    public void spinWheelRequest(long lastSpin, ShopWheelViewModel shopWheelViewModel) {
        if (lastSpin == 0 || waitedEnough(System.currentTimeMillis(),
                lastSpin, shopWheelViewModel.getState().getWaitRequirement())) {
            userShopWheelUseCaseInteractor.spinWheel(lastSpin);
        }
        else {
            userShopWheelUseCaseInteractor.tooEarly();
        }

    }

}
