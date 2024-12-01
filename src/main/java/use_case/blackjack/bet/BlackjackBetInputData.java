package use_case.blackjack.bet;

/**
 * Input Data for the Blackjack bet Use Case.
 */
public class BlackjackBetInputData {

    private final int bet;
    private final String username;

    public BlackjackBetInputData(int bet, String username) {
        this.bet = bet;
        this.username = username;
    }

    int getBet() {
        return bet;
    }

    String getUsername() {
        return username;
    }
}
