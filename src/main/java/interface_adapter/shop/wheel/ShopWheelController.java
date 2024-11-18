package interface_adapter.shop.wheel;

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
     * Sends the update request for button clicking.
     */
    public void wheelSpin() {
        userShopWheelUseCaseInteractor.wheelSpin();
    }

}
