package use_case.shopwheel;

import org.json.JSONObject;

import entity.User;

/**
 * DAO for the ShopWheel Use Case.
 */
public interface ShopWheelUserDataAccessInterface {
    /**
     * Saves the user's info.
     * @param user the user to save
     * @param info the default user info to save
     */
    void saveNew(User user, JSONObject info);

    /**
     * Saves the user's info.
     * @param username the username of the user
     * @return the user associated with that username
     */
    User get(String username);
}
