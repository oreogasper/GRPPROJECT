package use_case.shop;

import org.json.JSONObject;

/**
 * The Input Data for the Shop Use Case.
 */

public class ShopInputData {
    private final String username;
    private final String password;
    private final Integer balance;

    public ShopInputData(String username, String password, Integer balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    public Integer getBalance() {
        return balance;
    }
}
