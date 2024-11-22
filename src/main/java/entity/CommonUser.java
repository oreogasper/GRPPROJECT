package entity;

import java.util.Date;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private static final int INITIAL_BALANCE = 25;
    private String username;
    private String password;
    private int balance;
    private int wins;
    private int losses;
    private int games;
    private long lastSpin;

    public CommonUser(String name, String password) {
        this.username = name;
        this.password = password;
        this.balance = INITIAL_BALANCE;
        this.wins = 0;
        this.losses = 0;
        this.games = 0;
        this.lastSpin = 0;
    }

    @Override
    public String getName() {
        return username;
    }

    @Override
    public void setName(String name) {
        this.username = name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
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
    public long getLastSpin() {
        return lastSpin;
    }

    @Override
    public void setLastSpin(long lastSpin) {
        this.lastSpin = lastSpin;
    }

}
