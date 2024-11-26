package use_case.shop;

/**
 * The output boundary for the Shop main menu Use Case.
 */
public interface ShopOutputBoundary {
    /**
     * Prepares the success view for the Shop Use Case.
     */
    void prepareSuccessView();

    /**
     * Prepares the failure view for the Menu Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Menu View.
     */
    void switchToMenuView();

    /**
     * Switches to the shop wheel View.
     */
    void switchToShopWheelView();

    /**
     * Switches to the shop button View.
     */
    void switchToShopButtonView();

}
