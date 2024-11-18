package interface_adapter.shop.button;

import use_case.shopbutton.ShopButtonInputBoundary;

/**
 * The controller for the shop button screen Use Case.
 */
public class ShopButtonController {

    private final ShopButtonInputBoundary userShopButtonUseCaseInteractor;

    public ShopButtonController(ShopButtonInputBoundary shopButtonUseCaseInteractor) {
        this.userShopButtonUseCaseInteractor = shopButtonUseCaseInteractor;
    }

    /**
     * Switches (back) to the shop's main menu view.
     */
    public void switchToShopView() {
        userShopButtonUseCaseInteractor.switchToShopView();
    }

    /**
     * Sends the update request for button clicking.
     */
    public void buttonClick() {
        userShopButtonUseCaseInteractor.buttonClick();
    }

}
