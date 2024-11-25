package use_case.menu;

/**
 * Input Boundary for actions which are related to menu.
 */
public interface MenuInputBoundary {

    /**
     * Executes the switch to statistics view use case.
     */
    void switchToStatisticsView();

    /**
     * Executes the switch to gambling menu view use case.
     */
    void switchToGameMenuView();

    /**
     * Executes the switch to shop view use case.
     */
    void switchToShopView();
}
