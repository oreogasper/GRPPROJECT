package interface_adapter.statistics;

import entity.User;

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

    /**
     * Sets username for the current user in the state.
     * @param username the username for the current user
     */
    public void setUsername(String username) {
        user.setName(username);
    }

    /**
     * Sets password for the current user in the state.
     * @param password the password for the current user
     */
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
