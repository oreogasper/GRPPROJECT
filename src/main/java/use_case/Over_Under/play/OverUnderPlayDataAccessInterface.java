package use_case.Over_Under.play;

import org.json.JSONObject;
import entity.User;
import use_case.Ovr.BlackjackHitDataAccessInterface;

/**
 * DAO for the Over/Under Play Use Case.
 */
public interface OverUnderPlayDataAccessInterface {

    /**
     * Returns the bet of the current user for the Over/Under game.
     *
     * @return the bet of the current user;
     */
    boolean getBet();

    /**
     * Saves the updated user data, including balance and game progress.
     *
     * @param user the user to save.
     * @param info the user info to save in JSON format.
     */
    void saveNew(User user, JSONObject info);

    /**
     * Fetches the user data associated with the given username.
     *
     * @param username the username of the user.
     * @return the User object associated with that username.
     */
    User get(String username);
}
