package use_case.shopwheel;

/**
 * Input Boundary for actions which are related to the Shop button screen.
 */
public interface ShopWheelInputBoundary {

    /**
     * Executes the switch to menu view use case (exit).
     */
    void switchToShopView();

    /**
     * Executes the spin of the wheel.
     * @param lastSpin is the last time the user spun the wheel.
     */
    void spinWheel(long lastSpin);

    /**
     * Rejects the user's attempt to spin the wheel.
     */
    void tooEarly();

}
