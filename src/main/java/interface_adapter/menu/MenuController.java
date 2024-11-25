package interface_adapter.menu;

import use_case.menu.MenuInputBoundary;

/**
 * The controller for the Menu Use Case.
 */
public class MenuController {

    private final MenuInputBoundary userMenuUseCaseInteractor;

    public MenuController(MenuInputBoundary menuUseCaseInteractor) {
        this.userMenuUseCaseInteractor = menuUseCaseInteractor;
    }

    /**
     * Switches to the stats screen.
     */
    public void switchToStatisticsView() {
        userMenuUseCaseInteractor.switchToStatisticsView();
    }

    /**
     * Switches to the gambling game menu screen.
     */
    public void switchToGameMenuView() {
        userMenuUseCaseInteractor.switchToGameMenuView();
    }

    /**
     * Switches to the shop main menu screen.
     */
    public void switchToShopView() {
        userMenuUseCaseInteractor.switchToShopView();
    }
}
