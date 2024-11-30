package use_case.blackjack.stand;


/**
 * Input Boundary for actions relating to the stand use case.
 */
public interface BlackjackStandInputBoundary {

    /**
     * Executes the blackjack stand use case.
     * @param blackjackStandInputData the input data
     */
    void execute(BlackjackStandInputData blackjackStandInputData);

    /**
     * Executes the switch to login use case.
     */
    void switchToGameMenuView();

    void switchToBetView();
}
