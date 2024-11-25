package use_case.blackjack.hit;


/**
 * Input Boundary for actions related to playing the Blackjack Game..
 */
public interface BlackjackHitInputBoundary {

    /**
     * Executes the blackjack game use case.
     * @param blackjackHitInputData the input data
     */
    void execute(BlackjackHitInputData blackjackHitInputData);

    /**
     * Executes the switch to login use case.
     */
    void switchToGameMenuView();

}
