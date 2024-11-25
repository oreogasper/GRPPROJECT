package use_case.blackjack.hit;

/**
 * The output boundary for the Blackjack Hit Use Case.
 */
public interface BlackjackHitOutputBoundary {

    /**
     * Prepares the success view for the Blackjack Bet Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(BlackjackHitOutputData outputData);

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
