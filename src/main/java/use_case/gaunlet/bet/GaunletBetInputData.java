package use_case.gaunlet.bet;

/**
 * The Input Data for the Gaunlet Bet Use Case.
 */
public class GaunletBetInputData {
    private final String username;

    private final int bet;

    public GaunletBetInputData(String username, int bet) {
        this.username = username;
        this.bet = bet;
    }

    String getUsername() {
        return username;
    }

    int getBet() {
        return bet;
    }
}
