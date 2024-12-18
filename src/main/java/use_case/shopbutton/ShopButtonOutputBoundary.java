package use_case.shopbutton;

/**
 * The output boundary for the Shop button Use Case.
 */
public interface ShopButtonOutputBoundary {
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
    void addClick();

    /**
     * Adds a token to the user's balance.
     */
    void addToken();

}
