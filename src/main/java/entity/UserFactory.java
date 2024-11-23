package entity;

import org.json.JSONObject;

/**
 * Factory for creating users.
 */
public interface UserFactory {
    /**
     * Creates a new User.
     * @param name the name of the new user
     * @param password the password of the new user
     * @param info the info of the user
     * @return the new user
     */
    User create(String name, String password, JSONObject info);

}
