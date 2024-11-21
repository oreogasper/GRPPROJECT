package interface_adapter.menu;

import entity.User;

/**
 * The State information representing the logged-in user.
 */
public class MenuState {
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "MenuState{"
                + "username='" + user.getName() + '\''
                + ", password='" + user.getPassword() + '\''
                + ", wins='" + user.getWins() + '\''
                + ", games='" + user.getGames() + '\''
                + ", balance='" + user.getBalance() + '\''
                + '}';
    }

}
