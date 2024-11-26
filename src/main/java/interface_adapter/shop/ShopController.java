package interface_adapter.shop;

import use_case.shop.ShopInputBoundary;

/**
 * The controller for the shop main menu Use Case.
 */
public class ShopController {

    private final ShopInputBoundary userShopUseCaseInteractor;

    public ShopController(ShopInputBoundary shopUseCaseInteractor) {
        this.userShopUseCaseInteractor = shopUseCaseInteractor;
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
