package use_case.shopwheel;

import java.util.concurrent.TimeUnit;

/**
 * The Shop wheel Interactor.
 */
public class ShopWheelInteractor implements ShopWheelInputBoundary {
    private final ShopWheelOutputBoundary userPresenter;

    public ShopWheelInteractor(ShopWheelOutputBoundary shopWheelOutputBoundary) {
        this.userPresenter = shopWheelOutputBoundary;
    }

    @Override
    public void switchToShopView() {
        userPresenter.switchToShopView();
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

    @Override
    public void spinWheel(long lastSpin) {
        final long waitTimeSeconds = 10;
        if (lastSpin == 0 || waitedEnough(System.currentTimeMillis(), lastSpin, waitTimeSeconds)) {
            userPresenter.spinWheel();
        }
        else {
            userPresenter.tooEarly();
        }
    }

}
