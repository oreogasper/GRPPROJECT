package use_case.gaunlet.bet;

/**
 * Output Data for the Gaunlet Bet Use Case.
 */
public class GaunletBetOutputData {

    private final String bet;
    private final boolean useCaseFailed;

    public GaunletBetOutputData(String bet, boolean useCaseFailed) {
        this.bet = bet;
        this.useCaseFailed = useCaseFailed;
    }

    public String getBet() {
        return bet;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
