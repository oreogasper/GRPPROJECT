package interface_adapter.gaunlet.guess;

import entity.User;

public class GaunletGuessState {
    private User user;
    private String coinGuess = "";
    private String coinGuessError;
    private String diceGuess = "";
    private String diceGuessError;
    private String rpsGuess = "";
    private String rpsGuessError;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getCoinGuess() {
        return coinGuess;
    }

    public String getCoinGuessError() {
        return coinGuessError;
    }

    public String getDiceGuess() {
        return diceGuess;
    }

    public String getDiceGuessError() {
        return diceGuessError;
    }

    public String getRpsGuess() {
        return rpsGuess;
    }

    public String getRpsGuessError() {
        return rpsGuessError;
    }

    public void setCoinGuess(String coinGuess) {
        this.coinGuess = coinGuess;
    }

    public void setCoinGuessError(String coinGuessError) {
        this.coinGuessError = coinGuessError;
    }

    public void setDiceGuess(String diceGuess) {
        this.diceGuess = diceGuess;
    }

    public void setDiceGuessError(String diceGuessError) {
        this.diceGuessError = diceGuessError;
    }

    public void setRpsGuess(String rpsGuess) {
        this.rpsGuess = rpsGuess;
    }

    public void setRpsGuessError(String rpsGuessError) {
        this.rpsGuessError = rpsGuessError;
    }

    @Override
    public String toString() {
        return "GaunletGuessState{"
                + "coin guess='" + coinGuess + '\''
                + ", dice roll guess='" + diceGuess + '\''
                + ", rock, paper, scissors guess='" + rpsGuess + '\''
                + "user='" + user + '\''
                + '}';
    }
}
