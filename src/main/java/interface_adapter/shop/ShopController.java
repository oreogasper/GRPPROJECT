package interface_adapter.shop;

import use_case.shop.ShopInputBoundary;
import use_case.shop.ShopInputData;

/**
 * The controller for the shop main menu Use Case.
 */
public class ShopController {

    private final ShopInputBoundary userShopUseCaseInteractor;

    public ShopController(ShopInputBoundary shopUseCaseInteractor) {
        this.userShopUseCaseInteractor = shopUseCaseInteractor;
    }

    /**
     * Executes the Shop Use Case.
     * @param username the username to sign up
     * @param password the password
     * @param changedAmount the password repeated
     */
    public void execute(String username, String password, Integer changedAmount) {
        final ShopInputData shopInputData = new ShopInputData(
                username, password, changedAmount);

        userShopUseCaseInteractor.execute(shopInputData, changedAmount);
    }

    /**
     * Switches (back) to menu view if the player wishes to exit.
     */
    public void switchToMenuView() {
        userShopUseCaseInteractor.switchToMenuView();
    }

    /**
     * Switches to the shop's wheel view.
     */
    public void switchToShopWheelView() {
        userShopUseCaseInteractor.switchToShopWheelView();
    }

    /**
     * Switches to the shop's button view.
     */
    public void switchToShopButtonView() {
        userShopUseCaseInteractor.switchToShopButtonView();
    }

}
