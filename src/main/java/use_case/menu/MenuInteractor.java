package use_case.menu;

import use_case.menu.MenuInputBoundary;

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
    public void switchToWelcomeView() {
        userPresenter.switchToWelcomeView();
    }

    @Override
    public void switchToGameMenuView() {
        userPresenter.switchToGameMenuView();
    }

    @Override
    public void switchToShopView() {
        userPresenter.switchToShopView();
    }
}