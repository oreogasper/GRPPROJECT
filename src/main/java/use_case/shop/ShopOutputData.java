package use_case.shop;

import org.json.JSONObject;

/**
 * Output Data for the Signup Use Case.
 */
public class ShopOutputData {
    private final String username;
    private final String password;
    private final JSONObject info;

    public ShopOutputData(String username, String password, JSONObject info) {
        this.username = username;
        this.password = password;
        this.info = info;
    }

    public String getUsername() {
        return username;
    }

    public JSONObject getInfo() {
        return info;
    }

    public String getPassword() {
        return password;
    }
}
