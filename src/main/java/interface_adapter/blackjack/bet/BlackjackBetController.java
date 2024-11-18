package interface_adapter.blackjack.bet;

import use_case.blackjack.bet.BlackjackBetInputBoundary;

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
    public void execute(int bet) {
        // TODO
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
