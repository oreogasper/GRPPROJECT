package use_case.blackjack.stand;


/**
 * The output boundary for the Blackjack Stand Use Case.
 */
public interface BlackjackStandOutputBoundary {

    /**
     * Prepares the success view for the Blackjack Bet Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(BlackjackStandOutputData outputData);

    /**
     * Prepares the failure view for the Blackjack Bet Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Game Menu View.
     */
    void switchToGameMenuView();

    void switchToBetView();
}
