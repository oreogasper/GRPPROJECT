package use_case.gaunlet.bet;

/**
 * The output boundary for the Gaunlet Bet Use Case.
 */
public interface GaunletBetOutputBoundary {

    /**
     * Prepares the success view for the Signup Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(GaunletBetOutputData outputData);

    /**
     * Prepares the failure view for the Signup Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Gaunlet Guess View.
     */
    void switchToGaunletGuessView();

    /**
     * Switches to the Game menu View.
     */
    void switchToGameMenuView();

    /**
     * Saves current bet for user.
     */
    void setUserBet();

}
