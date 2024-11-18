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
     */
    void wheelSpin();

}
