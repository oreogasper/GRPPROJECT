package use_case.gaunlet.bet;

/**
 * The Input Data for the Gaunlet Bet Use Case.
 */
public class GaunletBetInputData {

    private final int bet;

    public GaunletBetInputData(int bet) {
        this.bet = bet;
    }

    int getBet() {
        return bet;
    }
}
