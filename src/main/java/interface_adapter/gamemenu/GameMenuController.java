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

    /**
     * Executes the "switch to login view" Use Case.
     */
    public void switchToLoginView() {
        userGameMenuUseCaseInteractor.switchToLoginView();
    }

    /**
     * Executes the "switch to menu" Use Case.
     */
    public void switchToMenuView() {
        userGameMenuUseCaseInteractor.switchToMenuView();
    }

    /**
     * Executes the "switch to gaunlet view" Use Case.
     */
    public void switchToGaunletView() {
        userGameMenuUseCaseInteractor.switchToGaunletView();
    }

    /**
     * Executes the "switch to blackjack view" Use Case.
     */
    public void switchToBlackjackView() {
        userGameMenuUseCaseInteractor.switchToBlackjackView();
    }

    /**
     * Executes the "switch to overunder view" Use Case.
     */
    public void switchToOverUnderBetView() {
        userGameMenuUseCaseInteractor.switchToOverUnderBetView();
        System.out.println("GameMenuController");
    }
}
