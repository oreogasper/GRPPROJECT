package interface_adapter.menu;

import entity.User;

/**
 * The State information representing the logged-in user.
 */
public class MenuState {
    private User user;
    private String username = "";

    private String password = "";
    private String passwordError;

    public MenuState(MenuState copy) {
        username = copy.username;
        password = copy.password;
        passwordError = copy.passwordError;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public MenuState() {

    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getUsername() {
        return user.getName();
    }

    public void setUsername(String username) {
        this.user.setName(username);
    }

    public void setPassword(String password) {
        this.user.setPassword(password);
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getPassword() {
        return this.user.getPassword();
    }
}
