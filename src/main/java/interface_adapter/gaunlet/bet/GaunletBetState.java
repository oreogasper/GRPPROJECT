package interface_adapter.gaunlet.bet;

/**
 * The state for the gaunlet bet View Model.
 */
public class GaunletBetState {
    private String bet;
    private String betError;

    public String getBet() {
        return bet;
    }

    public String getBetError() {
        return betError;
    }

    public void setBet(String bet) {
        this.bet = bet;
    }

    public void setBetError(String betError) {
        this.betError = betError;
    }

    @Override
    public String toString() {
        return "GaunletBetState{"
                + "bet='" + bet + '\''
                + '}';
    }
}
