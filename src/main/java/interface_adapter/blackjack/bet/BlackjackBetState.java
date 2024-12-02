package interface_adapter.blackjack.bet;

import entity.User;

/**
 * The state for the Blackjack Bet View Model.
 */
public class BlackjackBetState {

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

    public void setBet(String bet) {
        this.bet = bet;
    }

    public String getBetError() {
        return betError;
    }

    public void setBetError(String betError) {
        this.betError = betError;
    }
}
