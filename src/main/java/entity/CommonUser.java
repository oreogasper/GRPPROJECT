package entity;

import java.util.Date;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String username;
    private final String password;
    private int balance;
    private int wins;
    private int losses;
    private int games;
    private Date lastSpin;

    public CommonUser(String name, String password) {
        this.username = name;
        this.password = password;
        this.balance = 0;
        this.wins = 0;
        this.losses = 0;
        this.games = 0;
        this.lastSpin = null;
    }

    @Override
    public String getName() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public void updateBalance(int amount) {
        balance += amount;
    }

    @Override
    public int getWins() {
        return wins;
    }

    @Override
    public void wonGame() {
        wins++;
    }

    @Override
    public int getLosses() {
        return losses;
    }

    @Override
    public void lostGame() {
        losses++;
    }

    @Override
    public int getGames() {
        return games;
    }

    @Override
    public void playedGame() {
        games++;
    }

    @Override
    public Date getLastSpin() {
        return lastSpin;
    }

    @Override
    public void setLastSpin(Date lastSpin) {
        this.lastSpin = lastSpin;
    }

}
