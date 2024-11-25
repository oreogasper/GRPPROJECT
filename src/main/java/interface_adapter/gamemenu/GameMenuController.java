package interface_adapter.gamemenu;

import use_case.gamemenu.GameMenuInputBoundary;

/**
 * Controller for the Game Menu Use Case.
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

    public void switchToBlackjackView() {
        userGameMenuUseCaseInteractor.switchToBlackjackView();
    }

    public void switchToOverUnderView() {
        userGameMenuUseCaseInteractor.switchToOverUnderView();
    }
}
