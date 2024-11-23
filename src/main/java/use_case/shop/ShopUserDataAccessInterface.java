package use_case.shop;

import org.json.JSONObject;

import entity.User;

/**
 * DAO for the Signup Use Case.
 */
public interface ShopUserDataAccessInterface {
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
