package interface_adapter.gaunlet.bet;

import entity.User;

/**
 * The state for the gaunlet bet View Model.
 */
public class GaunletBetState {
    private User user;
    private String bet = "0";
    private String betError;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getBet() {
        return bet;
    }

    public String getBetError() {
        return betError;
    }

    public void setBet(String bet) {this.bet = bet;}

    public void setBetError(String betError) {
        this.betError = betError;
    }

    @Override
    public String toString() {
        return "GaunletBetState{"
                + "bet='" + bet + '\''
                + "user='" + user + '\''
                + '}';
    }
}
