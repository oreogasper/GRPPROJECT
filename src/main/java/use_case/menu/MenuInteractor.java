package use_case.menu;

import use_case.menu.MenuInputBoundary;
import use_case.menu.MenuOutputBoundary;

/**
 * The Menu Interactor.
 */
public class MenuInteractor implements MenuInputBoundary {
    private final MenuOutputBoundary userPresenter;

    public MenuInteractor(MenuOutputBoundary menuOutputBoundary) {
        this.userPresenter = menuOutputBoundary;
    }

    @Override
    public void switchToStatisticsView() {
        userPresenter.switchToStatisticsView();
    }
    @Override
    public void switchToLoginView() {
        userPresenter.switchToLoginView();
    }
}

