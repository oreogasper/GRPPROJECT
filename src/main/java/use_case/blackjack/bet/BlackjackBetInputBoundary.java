package use_case.blackjack.bet;

/**
 * Input Boundary for actions on the betting view for betting in Blackjack.
 */
public interface BlackjackBetInputBoundary {
    /**
     * Executes the bet use case.
     * @param blackjackBetInputData the input data
     */
    void execute(BlackjackBetInputData blackjackBetInputData);

    /**
     * Executes the switch to blackjack game use case.
     */
    void switchToBlackjackGameView();

    /**
     * Executes the switch to game menu use case.
     */
    void switchToGameMenuView();
}
