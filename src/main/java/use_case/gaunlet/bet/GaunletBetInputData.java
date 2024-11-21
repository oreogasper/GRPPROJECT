package use_case.gaunlet.bet;

/**
 * The Input Data for the Gaunlet Bet Use Case.
 */
public class GaunletBetInputData {

    private final String bet;

    public GaunletBetInputData(String bet) {
        this.bet = bet;
    }

    String getBet() {
        return bet;
    }
}
