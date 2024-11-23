package use_case.change_password;

import org.json.JSONObject;

/**
 * The input data for the Change Password Use Case.
 */
public class ChangePasswordInputData {

    private final String password;
    private final String username;
    private final JSONObject info;

    public ChangePasswordInputData(String password, String username, JSONObject info) {
        this.password = password;
        this.username = username;
        this.info = info;
    }

    String getPassword() {
        return password;
    }

    String getUsername() {
        return username;
    }

    public JSONObject getInfo() {
        return info;
    }
}
