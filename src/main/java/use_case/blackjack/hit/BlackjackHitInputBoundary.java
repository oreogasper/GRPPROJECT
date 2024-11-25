package use_case.blackjack.hit;


/**
 * Input Boundary for actions relating to the hit use case.
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
