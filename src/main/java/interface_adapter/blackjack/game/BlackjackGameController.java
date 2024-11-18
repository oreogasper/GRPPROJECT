package interface_adapter.blackjack.game;


import use_case.blackjack.game.BlackjackGameInputBoundary;

/**
 * Controller for the Blackjack Game Use Case.
 */
public class BlackjackGameController {
    private final BlackjackGameInputBoundary blackjackGameUseCaseInteractor;

    public BlackjackGameController(BlackjackGameInputBoundary blackjackGameInputBoundary) {
        this.blackjackGameUseCaseInteractor = blackjackGameInputBoundary;
    }

    /**
     * Executes the "switch to LoginView" Use Case.
     */
    public void switchToLoginView() {
        blackjackGameUseCaseInteractor.switchToLoginView();
    }

}
