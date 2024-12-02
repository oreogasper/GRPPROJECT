package interface_adapter.blackjack.game.stand;

import use_case.blackjack.stand.BlackjackStandInputBoundary;
import use_case.blackjack.stand.BlackjackStandInputData;

public class BlackjackStandController {
    private final BlackjackStandInputBoundary inputBoundary;

    public BlackjackStandController(BlackjackStandInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Executes the Blackjack Stand Use Case.
     */
    public void execute(String currentTurnState, String username, int bet) {
        boolean isEndPlayerTurn = false;
        if (currentTurnState.equals("Player")) {
            isEndPlayerTurn = true;
        }

        final BlackjackStandInputData inputData = new BlackjackStandInputData(isEndPlayerTurn, username, bet);

        inputBoundary.execute(inputData);
    }

    /**
     * Executes the "switch to GameMenuView" Use Case.
     */
    public void switchToGameMenuView() {
        inputBoundary.switchToGameMenuView();
    }

    public void switchToBetView() {
        inputBoundary.switchToBetView();
    }
}
