package use_case.add_friend;

import entity.User;
import org.json.JSONObject;

/**
 * The interface of the DAO for the Add Friend Use Case.
 */
public interface AddFriendUserDataAccessInterface {

    /**
     * Updates the system to record this user's password.
     * @param user the user whose password is to be updated
     */
    void addFriend(User user);

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

    /**
     * Checks if the given username exists.
     * @param username the username to look for
     * @return true if a user with the given username exists; false otherwise
     */
    boolean existsByName(String username);
}
