package interface_adapter.blackjack.bet;

import use_case.blackjack.bet.BlackjackBetInputBoundary;
import use_case.blackjack.bet.BlackjackBetInputData;

/**
 * Controller for the Gaunlet Bet Use Case.
 */
public class BlackjackBetController {

    private final BlackjackBetInputBoundary blackjackBetInputBoundary;

    public BlackjackBetController(BlackjackBetInputBoundary blackjackBetInputBoundary) {
        this.blackjackBetInputBoundary = blackjackBetInputBoundary;
    }

    /**
     * Executes the Blackjack Bet Use Case.
     * @param bet the amount the user would like to bet in the game
     */
    public void execute(String username, String bet) {
        final BlackjackBetInputData inputData = new BlackjackBetInputData(
                Integer.parseInt(bet), username
        );

        blackjackBetInputBoundary.execute(inputData);
    }

    /**
     * Executes the switch to Blackjack Game Use Case.
     */
    public void switchToBlackjackGameView() {
        blackjackBetInputBoundary.switchToBlackjackGameView();
    }

    /**
     * Executes the switch to menu Use Case.
     */
    public void switchToGameMenuView() {
        blackjackBetInputBoundary.switchToGameMenuView();
    }

}
