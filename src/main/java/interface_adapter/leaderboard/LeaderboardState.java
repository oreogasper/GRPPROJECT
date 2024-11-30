package interface_adapter.leaderboard;

import entity.User;

/**
 * The state for the Leaderboard View Model.
 */
public class LeaderboardState {
    private User user;
    private String friend;
    private String Error;

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
     * @param friend the password for the current user
     */
    public void setFriend(String friend) {
        this.friend = friend;
    }

    @Override
    public String toString() {
        return "LeaderboardState {"
                + "username='" + user.getName() + '\''
                + ", password='" + user.getPassword() + '\''
                + ", wins='" + user.getWins() + '\''
                + ", games='" + user.getGames() + '\''
                + ", balance='" + user.getBalance() + '\''
                + '}';
    }

    public String getFriend() {
        return friend;
    }

    public void setError(String Error) {
        this.Error = Error;
    }

    public String getError() {
        return Error;
    }
}
