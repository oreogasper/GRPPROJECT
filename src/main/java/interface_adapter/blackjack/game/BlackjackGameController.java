package interface_adapter.blackjack.game;


import entity.User;
import use_case.blackjack.game.BlackjackGameInputBoundary;
import use_case.blackjack.game.BlackjackGameInputData;

/**
 * Controller for the Blackjack Game Use Case.
 */
public class BlackjackGameController {
    private final BlackjackGameInputBoundary blackjackGameInputBoundary;

    public BlackjackGameController(BlackjackGameInputBoundary blackjackGameInputBoundary) {
        this.blackjackGameInputBoundary = blackjackGameInputBoundary;
    }

    public void execute(String useCaseName, int bet, User user, String turnSate) {

        BlackjackGameInputData inputData = new BlackjackGameInputData(useCaseName, bet, user, turnSate);
        blackjackGameInputBoundary.execute(inputData);

    }

    /**
     * Executes the "switch to LoginView" Use Case.
     */
    public void switchToLoginView() {
        blackjackGameInputBoundary.switchToLoginView();
    }

}
