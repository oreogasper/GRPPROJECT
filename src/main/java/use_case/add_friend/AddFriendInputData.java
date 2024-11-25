package use_case.add_friend;

import org.json.JSONObject;

/**
 * The input data for the Add Friend Use Case.
 */
public class AddFriendInputData {

    private final String password;
    private final String username;
    private final JSONObject info;

    public AddFriendInputData(String password, String username, JSONObject info) {
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
