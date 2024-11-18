package use_case.blackjack.bet;

/**
 * Input Data for the Blackjack bet Use Case.
 */
public class BlackjackBetInputData {

    private final int bet;

    public BlackjackBetInputData(int bet) {
        this.bet = bet;
    }

    int getBet() {
        return bet;
    }
}
