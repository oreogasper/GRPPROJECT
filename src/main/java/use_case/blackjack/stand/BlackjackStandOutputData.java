package use_case.blackjack.stand;

/**
 * Output Data for the Blackjack Stand Use Case.
 */
public class BlackjackStandOutputData {
    private final String turnState;
    private final boolean useCaseFailed;
    private final boolean isEndPlayerTurn;
    private final int dealerScore;

    public BlackjackStandOutputData(String turnState,
                                    boolean useCaseFailed, boolean isEndPlayerTurn,
                                    int dealerScore) {
        this.turnState = turnState;
        this.useCaseFailed = useCaseFailed;
        this.isEndPlayerTurn = isEndPlayerTurn;
        this.dealerScore = dealerScore;
    }

    public String getTurnState() {
        return turnState;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public int getDealerScore() {return dealerScore;}

    public boolean isEndPlayerTurn() {return isEndPlayerTurn;}

}
