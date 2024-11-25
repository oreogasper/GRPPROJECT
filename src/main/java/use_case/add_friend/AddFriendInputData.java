package use_case.add_friend;

import org.json.JSONObject;

/**
 * The input data for the Add Friend Use Case.
 */
public class AddFriendInputData {

    private final String friend;
    private final String username;
    private final JSONObject info;

    public AddFriendInputData(String friend, String username, JSONObject info) {
        this.friend = friend;
        this.username = username;
        this.info = info;
    }

    String getUsername() {
        return username;
    }

    public JSONObject getInfo() {
        return info;
    }

    public String getFriend() {
        return friend;
    }
}
