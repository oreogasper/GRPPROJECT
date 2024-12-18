package interface_adapter.shop.button;

import use_case.shopbutton.ShopButtonInputBoundary;
import use_case.shopbutton.ShopButtonInputData;

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
     * Passes along the current clicks for interactor analysis.
     * @param clicksMade is the current number of clicks / 10.
     */
    public void buttonClick(int clicksMade) {
        userShopButtonUseCaseInteractor.buttonClick(clicksMade);
    }

    public void saveData(String username, int newBalance) {
        final ShopButtonInputData shopButtonInputData = new ShopButtonInputData(
                username, newBalance);

        userShopButtonUseCaseInteractor.saveData(shopButtonInputData, newBalance);

    }

}
