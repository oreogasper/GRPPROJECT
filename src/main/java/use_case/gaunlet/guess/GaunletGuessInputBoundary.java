package use_case.gaunlet.guess;

/**
 * Input Boundary for actions which are related to making a guess in Gaunlet Game.
 */
public interface GaunletGuessInputBoundary {

    /**
     * Executes the gaunlet guess use case.
     * @param gaunletGuessInputData the input data
     */
    void execute(GaunletGuessInputData gaunletGuessInputData);

    /**
     * Executes the switch to login use case.
     */
    void switchToLoginView();
    // need to add switch to gaunlet guess view
}
