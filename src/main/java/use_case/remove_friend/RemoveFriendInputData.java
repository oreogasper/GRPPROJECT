package use_case.remove_friend;

import org.json.JSONObject;

/**
 * The input data for the Remove Friend Use Case.
 */
public class RemoveFriendInputData {
    private final String username;
    private final JSONObject info;

    public RemoveFriendInputData(String username, JSONObject info) {
        this.username = username;
        this.info = info;
    }

    String getUsername() {
        return username;
    }

    public JSONObject getInfo() {
        return info;
    }
}
