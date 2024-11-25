package use_case.blackjack.get_card;

/**
 * The output boundary for the Blackjack Game Use Case.
 */
public interface BlackjackGetCardOutputBoundary {

    /**
     * Prepares the success view for the Blackjack Bet Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(BlackjackGetCardOutputData outputData);

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