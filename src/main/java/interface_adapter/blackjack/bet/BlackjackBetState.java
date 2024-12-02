package interface_adapter.blackjack.bet;

import entity.User;

/**
 * The state for the Blackjack Bet View Model.
 */
public class BlackjackBetState {

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
