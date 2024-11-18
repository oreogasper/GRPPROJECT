package interface_adapter.blackjack.bet;

/**
 * The state for the Blackjack Bet View Model.
 */
public class BlackjackBetState {

    private int bet;
    private String betError;

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public String getBetError() {
        return betError;
    }

    public void setBetError(String betError) {
        this.betError = betError;
    }

}
