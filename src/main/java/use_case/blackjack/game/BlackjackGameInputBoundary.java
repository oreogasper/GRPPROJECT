package use_case.blackjack.game;


/**
 * Input Boundary for actions related to playing the Blackjack Game..
 */
public interface BlackjackGameInputBoundary {


    /**
     * Executes the blackjack game use case.
     * @param blackjackGameInputData the input data
     */
    void execute(BlackjackGameInputData blackjackGameInputData);

    /**
     * Executes the switch to login use case.
     */
    void switchToLoginView();

}
