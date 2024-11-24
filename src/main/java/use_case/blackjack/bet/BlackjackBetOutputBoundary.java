package use_case.blackjack.bet;

/**
 * The output boundary for the Blackjack Bet Use Case.
 */
public interface BlackjackBetOutputBoundary {

    /**
     * Prepares the success view for the Blackjack Bet Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(BlackjackBetOutputData outputData);

    /**
     * Prepares the failure view for the Blackjack Bet Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Blackjack Game View.
     */
    void switchToBlackjackGameView();

    /**
     * Switches to the Game Menu View.
     */
    void switchToGameMenuView();
}
