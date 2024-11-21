package use_case.gaunlet.bet;

/**
 * Output Data for the Gaunlet Bet Use Case.
 */
public class GaunletBetOutputData {

    private final int bet;
    private final boolean useCaseFailed;

    public GaunletBetOutputData(int bet, boolean useCaseFailed) {
        this.bet = bet;
        this.useCaseFailed = useCaseFailed;
    }

    public int getBet() {
        return bet;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
