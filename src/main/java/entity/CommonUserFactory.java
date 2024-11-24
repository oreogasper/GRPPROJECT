package entity;

import org.json.JSONObject;

/**
 * Factory for creating CommonUser objects.
 */
public class CommonUserFactory implements UserFactory {

    @Override
    public User create(String name, String password, JSONObject info) {
        return new CommonUser(name, password, info);
    }
}
