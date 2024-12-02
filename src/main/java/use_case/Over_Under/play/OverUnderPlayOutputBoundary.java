package use_case.Over_Under.play;

/**
 * Output Boundary for actions related to the Over/Under game.
 */
public interface OverUnderPlayOutputBoundary {

    /**
     * Prepares the success view with the results of the game.
     * @param outputData the output data containing game results
     */
    void prepareSuccessView(OverUnderPlayOutputData outputData);

    /**
     * Prepares the failure view with an error message.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Game Menu View.
     */
    void switchToGameMenuView();
}
