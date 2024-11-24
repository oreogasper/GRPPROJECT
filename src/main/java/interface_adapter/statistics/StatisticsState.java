package interface_adapter.statistics;

import entity.User;
import interface_adapter.change_password.LoggedInState;

/**
 * The state for the Signup View Model.
 */
public class StatisticsState {
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getUsername() {
        return user.getName();
    }

    public String getPassword() {
        return user.getPassword();
    }

    public Integer getWins() {
        return user.getWins();
    }

    public Integer getLosses() {
        return user.getLosses();
    }

    public Integer getGames() {
        return user.getGames();
    }

    public Integer getWinPercentage() {
        return user.getWins() / user.getGames();
    }

    public void setUsername(String username) {
        user.setName(username);
    }

    public void setPassword(String password) {
        user.setPassword(password);
    }

    @Override
    public String toString() {
        return "StatisticsState{"
                + "username='" + user.getName() + '\''
                + ", password='" + user.getPassword() + '\''
                + ", wins='" + user.getWins() + '\''
                + ", games='" + user.getGames() + '\''
                + ", balance='" + user.getBalance() + '\''
                + '}';
    }
}
