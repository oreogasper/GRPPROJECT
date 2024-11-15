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

    public void switchToLoginView() {
        userMenuUseCaseInteractor.switchToLoginView();
    }
    public void switchToStatisticsView() {
        userMenuUseCaseInteractor.switchToStatisticsView();
    }

    public void switchToGameMenuView() {
        userMenuUseCaseInteractor.switchToGameMenuView();
    }
}
