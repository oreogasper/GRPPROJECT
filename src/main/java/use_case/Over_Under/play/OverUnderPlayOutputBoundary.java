package use_case.Over_Under.play;

import use_case.Over_Under.OverUnderPlayOutputData;

/**
 * Output Boundary for the Over/Under game.
 */
public interface OverUnderPlayOutputBoundary {
    void prepareSuccessView(OverUnderPlayOutputData outputData);
    void prepareFailView(String errorMessage);
    void switchToGameMenuView();
}
