package entity;

import org.json.JSONObject;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {
    private static final String BALANCE = "balance";
    private static final String WINS = "wins";
    private static final String LOSSES = "losses";
    private static final String GAMES = "games";
    private final JSONObject info;
    private String username;
    private String password;
    private long lastSpin;
    private int currBet;

    public CommonUser(String name, String password, JSONObject info) {
        this.username = name;
        this.password = password;
        this.lastSpin = 0L;
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
        return info.getInt(BALANCE);
    }

    @Override
    public void updateBalance(int amount) {
        final int bal = info.getInt(BALANCE);
        info.put(BALANCE, bal + amount);
        // balance += amount;
    }

    @Override
    public int getWins() {
        return info.getInt(WINS);
    }

    @Override
    public void wonGame() {
        final int win = info.getInt(WINS);
        final int game = info.getInt(GAMES);
        info.put(WINS, win + 1);
        info.put(GAMES, game + 1);
    }

    @Override
    public int getLosses() {
        return info.getInt(LOSSES);
    }

    @Override
    public void lostGame() {
        final int loss = info.getInt(LOSSES);
        final int game = info.getInt(GAMES);
        info.put(LOSSES, loss + 1);
        info.put(GAMES, game + 1);
    }

    @Override
    public int getGames() {
        return info.getInt(GAMES);
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
