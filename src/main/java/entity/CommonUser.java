package entity;

import org.json.JSONObject;

import java.util.Date;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private static final int INITIAL_BALANCE = 25;
    private final JSONObject info;
    private String username;
    private String password;
    private int balance;
    private int wins;
    private int losses;
    private int games;
    private long lastSpin;
    private int currBet;

    public CommonUser(String name, String password, JSONObject info) {
        this.username = name;
        this.password = password;
        this.balance = INITIAL_BALANCE;
        this.wins = 0;
        this.losses = 0;
        this.games = 0;
        this.lastSpin = null;
        this.currBet = 0;
        this.info = info;
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
    public void setBet(int bet) {
        this.currBet = bet;
    }

    @Override
    public int getBet() {
        return currBet;
    }

    public JSONObject getInfo() {
        return info;
    }

    @Override
    public int getBalance() {
        return info.getInt("balance");
    }

    @Override
    public void updateBalance(int amount) {
        final int bal = info.getInt("balance");
        info.put("balance", bal + amount);
        // balance += amount;
    }

    @Override
    public int getWins() {
        return info.getInt("wins");
    }

    @Override
    public void wonGame() {
        final int win = info.getInt("wins");
        final int game = info.getInt("games");
        info.put("wins", win + 1);
        info.put("games", game + 1);
        // wins++;
        // games++;
    }

    @Override
    public int getLosses() {
        return info.getInt("losses");
    }

    @Override
    public void lostGame() {
        final int loss = info.getInt("losses");
        final int game = info.getInt("games");
        info.put("losses", loss + 1);
        info.put("games", game + 1);
        // losses++;
        // games++;
    }

    @Override
    public int getGames() {
        return info.getInt("games");
    }

    @Override
    public long getLastSpin() {
        return lastSpin;
    }

    @Override
    public void setLastSpin(long lastSpin) {
        this.lastSpin = lastSpin;
    }

    @Override
    public String toString() {
        return "This user {"
                + "username='" + this.getName() + '\''
                + ", password='" + this.getPassword() + '\''
                + ", wins='" + this.getWins() + '\''
                + ", losses='" + this.getLosses() + '\''
                + ", games='" + this.getGames() + '\''
                + ", balance='" + this.getBalance() + '\''
                + ", bet='" + this.getBet() + '\''
                + '}';
    }

}
