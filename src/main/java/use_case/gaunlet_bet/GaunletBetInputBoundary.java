package use_case.gaunlet_bet;

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
    void switchToGameMenuView();
    // need to add switch to gaunlet guess view
}

