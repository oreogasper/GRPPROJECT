package use_case.shop;

import use_case.shop.ShopInputData;

/**
 * Input Boundary for actions which are related to the Shop main menu.
 */
public interface ShopInputBoundary {
    /**
     * Executes the exit shop use case.
     * @param shopInputData the input data
     * @param changedAmount the amount to be changed after user is done using the shop
     */
    void execute(ShopInputData shopInputData, Integer changedAmount);

    /**
     * Executes the switch to menu view use case (exit).
     */
    void switchToMenuView();

    /**
     * Executes the switch to shop wheel use case.
     */
    void switchToShopWheelView();

    /**
     * Executes the switch to shop button view use case.
     */
    void switchToShopButtonView();

}
