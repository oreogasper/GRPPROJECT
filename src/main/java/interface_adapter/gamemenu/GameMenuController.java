package interface_adapter.gamemenu;

import use_case.menu.MenuInputBoundary;

/**
 * The controller for the Menu Use Case.
 */
public class GameMenuController {

    private final GameMenuInputBoundary userGameMenuUseCaseInteractor;

    public GameMenuController(GameMenuInputBoundary gameMenuUseCaseInteractor) {
        this.userGameMenuUseCaseInteractor = gameMenuUseCaseInteractor;
    }

    public void switchToLoginView() {
        userGameMenuUseCaseInteractor.switchToLoginView();
    }
    public void switchToMenuView() {
        userGameMenuUseCaseInteractor.switchToMenuView();
    }
    public void switchToGaunletView() {
        userGameMenuUseCaseInteractor.switchToGaunletView();
    }

}
