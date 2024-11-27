package use_case.blackjack.stand;

/**
 * Output Data for the Blackjack Stand Use Case.
 */
public class BlackjackStandOutputData {
    private final String turnState;
    private final boolean useCaseFailed;
    private final boolean isEndPlayerTurn;

    public BlackjackStandOutputData(String turnState,
                                    boolean useCaseFailed, boolean isEndPlayerTurn) {
        this.turnState = turnState;
        this.useCaseFailed = useCaseFailed;
        this.isEndPlayerTurn = isEndPlayerTurn;
    }

    public String getTurnState() {
        return turnState;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public boolean isEndPlayerTurn() {return isEndPlayerTurn;}

}
