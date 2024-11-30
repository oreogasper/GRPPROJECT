package use_case.shop;

/**
 * Input Boundary for actions which are related to the Shop main menu.
 */
public interface ShopInputBoundary {
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
