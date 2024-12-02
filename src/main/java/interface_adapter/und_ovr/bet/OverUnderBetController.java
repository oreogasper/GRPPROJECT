package interface_adapter.und_ovr.bet;

import use_case.Over_Under.bet.OverUnderBetInputBoundary;
import use_case.Over_Under.bet.OverUnderBetInputData;

/**
 * Controller for the OverUnder Bet Use Case.
 */
public class OverUnderBetController {

    private final OverUnderBetInputBoundary overUnderBetInputBoundary;

    public OverUnderBetController(OverUnderBetInputBoundary overUnderBetInputBoundary) {
        this.overUnderBetInputBoundary = overUnderBetInputBoundary;
    }

    /**
     * Executes the over under bet use case.
     * @param betAmt User's bet amount.
     * @param username Username of user.
     */
    public void execute(String username, int betAmt) {
        final OverUnderBetInputData overUnderBetInputData = new OverUnderBetInputData(username, betAmt);
        this.overUnderBetInputBoundary.execute(overUnderBetInputData);
    }

    /**
     * Executes the game menu use case.
     */
    public void switchToGameMenuView() {
        overUnderBetInputBoundary.switchToGameMenuView();
    }

    /**
     * Executes the over under bet view use case.
     */
    public void switchToOverUnderGameView() {
        overUnderBetInputBoundary.switchToOverUnderGameView();
    }

}
