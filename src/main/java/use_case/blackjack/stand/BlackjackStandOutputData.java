package use_case.blackjack.stand;

/**
 * Output Data for the Blackjack Stand Use Case.
 */
public class BlackjackStandOutputData {
    private final int playerScore;
    private final boolean useCaseFailed;

    public BlackjackStandOutputData(int playerScore, boolean useCaseFailed) {
        this.playerScore = playerScore;
        this.useCaseFailed = useCaseFailed;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

}
