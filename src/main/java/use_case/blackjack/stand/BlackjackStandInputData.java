package use_case.blackjack.stand;

/**
 * Input Data for the Blackjack Stand Use Case.
 */
public class BlackjackStandInputData {
    private final boolean isEndPlayerTurn;
    private final String username;
    private final int bet;

    public BlackjackStandInputData(boolean isEndPlayerTurn, String username, int bet) {
        this.isEndPlayerTurn = isEndPlayerTurn;
        this.username = username;
        this.bet = bet;

    }

    public boolean isEndPlayerTurn() {
        return isEndPlayerTurn;
    }

    public int getBet() {
        return bet;
    }

    public String getUsername() {
        return username;
    }
}
