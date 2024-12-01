package use_case.Over_Under.bet;

/**
 * The output boundary for the OverUnder Bet Use Case.
 */
public interface OverUnderBetOutputBoundary {

    /**
     * Prepares the success view for the OverUnder Bet Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(OverUnderBetOutputData outputData);

    /**
     * Prepares the failure view for the OverUnder Bet Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the OverUnder Game View.
     */
    void switchToOverUnderPlayView();

    /**
     * Switches to the Game Menu View.
     */
    void switchToGameMenuView();

    void setBet();
}
