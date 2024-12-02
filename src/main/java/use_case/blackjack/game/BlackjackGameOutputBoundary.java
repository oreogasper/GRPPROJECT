package use_case.blackjack.game;

import use_case.blackjack.bet.BlackjackBetOutputData;

/**
 * The output boundary for the Blackjack Game Use Case.
 */
public interface BlackjackGameOutputBoundary {

    /**
     * Prepares the success view for the Blackjack Bet Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(BlackjackGameOutputData outputData);

    /**
     * Prepares the failure view for the Blackjack Bet Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Game Menu View.
     */
    void switchToGameMenuView();

}
