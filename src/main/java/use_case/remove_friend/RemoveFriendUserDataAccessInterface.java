package use_case.remove_friend;

import entity.User;
import org.json.JSONObject;

/**
 * The interface of the DAO for the Remove Friend Use Case.
 */
public interface RemoveFriendUserDataAccessInterface {
    /**
     * Returns the user with the given username.
     * @param username the username to look up
     * @return the user with the given username
     */
    User get(String username);

    /**
     * Updates the system to record this user's password.
     * @param user the user whose password is to be updated
     * @param json the updated user's info
     */
    void saveNew(User user, JSONObject json);
}
