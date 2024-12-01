package interface_adapter.und_ovr.bet;

import entity.User;

/**
 * The state for the overUnder bet View model.
 */
public class OverUnderBetState {
    private String betError;
    private User user;
    private int betAmt;


    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBetError(String betError) {
        this.betError = betError;
    }

    public String getBetError() {
        return betError;
    }

    public int getBet() {
        return this.betAmt;
    }

    public void setBet(int bet) {
        this.betAmt = bet;
    }

    // Not necessary.
    public String toString() {
        return "OverUnderBetState{"
                + "bet='" + betAmt + '\''
                + "user='" + user + '\''
                + '}';
    }
}
