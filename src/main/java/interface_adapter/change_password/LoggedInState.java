package interface_adapter.change_password;

import org.json.JSONObject;

/**
 * The State information representing the logged-in user.
 */
public class LoggedInState {
    private String username = "";

    private String password = "";
    private String passwordError;
    private JSONObject info;

    public LoggedInState(LoggedInState copy, JSONObject info) {
        username = copy.username;
        password = copy.password;
        passwordError = copy.passwordError;
        this.info = info;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoggedInState() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getPassword() {
        return password;
    }

    public JSONObject getInfo() {
        return info;
    }
}
