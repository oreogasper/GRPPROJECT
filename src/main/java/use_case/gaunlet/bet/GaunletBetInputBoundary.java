package use_case.gaunlet.bet;

/**
 * Input Boundary for actions which are related to betting in Gaunlet Game.
 */
public interface GaunletBetInputBoundary {
    /**
     * Executes the signup use case.
     * @param gaunletBetInputData the input data
     */
    void execute(GaunletBetInputData gaunletBetInputData);

    /**
     * Executes the switch to game menu use case.
     */
    void switchToGaunletGuessView();
    void switchToGameMenuView();
}

