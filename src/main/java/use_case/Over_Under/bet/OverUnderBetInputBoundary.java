package use_case.Over_Under.bet;

/**
 * Input Boundary for actions on the betting view for betting in OverUnder game.
 */
public interface OverUnderBetInputBoundary {

    /**
     * Executes the bet use case.
     * @param overUnderBetInputData the input data
     */
    void execute(OverUnderBetInputData overUnderBetInputData);

    /**
     * Executes the switch to overUnder game use case.
     */
    void switchToOverUnderGameView();

    /**
     * Executes the switch to gameMenu use case.
     */
    void switchToGameMenuView();
}
