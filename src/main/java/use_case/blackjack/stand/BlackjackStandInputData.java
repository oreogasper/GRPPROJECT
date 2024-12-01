package use_case.blackjack.stand;

/**
 * Input Data for the Blackjack Stand Use Case.
 */
public class BlackjackStandInputData {
    private final boolean isEndPlayerTurn;

    public BlackjackStandInputData(boolean isEndPlayerTurn) {
        this.isEndPlayerTurn = isEndPlayerTurn;

    }

    public boolean isEndPlayerTurn() {
        return isEndPlayerTurn;
    }
}
