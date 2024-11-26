package use_case.shopbutton;

import org.json.JSONObject;

/**
 * Output Data for the ShopButton Use Case.
 */
public class ShopButtonOutputData {
    private final String username;
    private final String password;
    private final JSONObject info;

    public ShopButtonOutputData(String username, String password, JSONObject info) {
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
