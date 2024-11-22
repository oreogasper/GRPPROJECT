package use_case.shopwheel;

/**
 * The output boundary for the Shop button Use Case.
 */
public interface ShopWheelOutputBoundary {
    /**
     * Prepares the success view for the Shop button Use Case.
     */
    void prepareSuccessView();

    /**
     * Prepares the failure view for the shop view Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the shop main menu View.
     */
    void switchToShopView();

    /**
     * Performs the action associated with clicking the button.
     * @param targetAngle is the angle that the wheel stops at.
     */
    void spinWheel(int targetAngle);

    /**
     * Displays the wheel which spins.
     */
    void spinningWheel();

    /**
     * Adds the prize amount to the user's balance and updates the main screen.
     * @param prize is the prize amount to be awarded.
     */
    void updatePrize(int prize);

    /**
     * Displays a message if the user tries to spin too early.
     */
    void tooEarly();

}
