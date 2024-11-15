package use_case.menu;

/**
 * Input Boundary for actions which are related to menu.
 */
public interface MenuInputBoundary {

    /**
     * Executes the switch to statistics view use case.
     */
    void switchToStatisticsView();
    void switchToLoginView();
    void switchToGameMenuView();

}
