package interface_adapter.statistics;

import entity.User;
import interface_adapter.change_password.LoggedInState;

/**
 * The state for the Signup View Model.
 */
public class StatisticsState {
    private User user;
    private String username = "";
    private String password = "";
    private Integer wins = 0;
    private Integer losses = 0;
    private Integer games = 0;

    public StatisticsState(StatisticsState copy) {
        username = copy.username;
        password = copy.password;
        wins = copy.wins;
        losses = copy.losses;
        games = copy.games;
    }

    public StatisticsState() {

    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Integer getWins() {
        return wins;
    }

    public Integer getLosses() {
        return losses;
    }

    public Integer getGames() {
        return games;
    }

    public Integer getWinPercentage() {
        return wins / games;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "StatisticsState{"
                + "username='" + username + '\''
                + ", password='" + password + '\''
                + ", wins='" + wins + '\''
                + '}';
    }
}
