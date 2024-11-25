package interface_adapter.gaunlet.bet;

import entity.User;

/**
 * The state for the gauntlet bet View Model.
 */
public class GaunletBetState {
    private User user;
    private int bet;
    private String betError;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public int getBet() {
        return bet;
    }

    public String getBetError() {
        return betError;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public void setBetError(String betError) {
        this.betError = betError;
    }

    @Override
    public String toString() {
        return "GaunletBetState {"
                + "bet='" + bet + '\''
                + "user='" + user + '\''
                + '}';
    }
}
