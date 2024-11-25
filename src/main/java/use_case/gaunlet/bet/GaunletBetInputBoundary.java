package use_case.gaunlet.bet;

/**
 * Input Boundary for actions which are related to betting in Gaunlet Game.
 */
public interface GaunletBetInputBoundary {
    /**
     * Executes the bet use case
     *
     * @param gaunletBetInputData the input data
     * @param bet the bet to initialize
     */
    void execute(GaunletBetInputData gaunletBetInputData, int bet);

    /**
     * Executes the switch to gaunlet guess use case.
     */
    void switchToGaunletGuessView();

    /**
     * Executes the switch to game menu use case.
     */
    void switchToGameMenuView();
}

