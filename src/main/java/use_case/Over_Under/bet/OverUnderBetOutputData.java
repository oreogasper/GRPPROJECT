package use_case.Over_Under.bet;

/**
 * Output Data for the Blackjack Bet Use Case.
 */
public class OverUnderBetOutputData {

    private final int betAmt;
    private final boolean useCaseFailed;

    public OverUnderBetOutputData(int bet, boolean useCaseFailed) {
        this.betAmt = bet;
        this.useCaseFailed = useCaseFailed;
    }

    public int getBet() {
        return betAmt;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
