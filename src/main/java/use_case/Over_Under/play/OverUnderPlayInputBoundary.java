package use_case.Over_Under.play;

/**
 * Input Boundary for actions related to playing the OverUnder Game.
 */
public interface OverUnderPlayInputBoundary {

    /**
     * Executes the OverUnder game use case.
     * @param overUnderPlayInputData the input data
     */
    void execute(OverUnderPlayInputData overUnderPlayInputData);

    /**
     * Executes the switch to game menu use case.
     */
    void switchToGameMenuView();
}
