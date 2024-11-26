package use_case.shopwheel;

import org.json.JSONObject;

/**
 * Output Data for the ShopWheel Use Case.
 */
public class ShopWheelOutputData {
    private final String username;
    private final String password;
    private final JSONObject info;

    public ShopWheelOutputData(String username, String password, JSONObject info) {
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
