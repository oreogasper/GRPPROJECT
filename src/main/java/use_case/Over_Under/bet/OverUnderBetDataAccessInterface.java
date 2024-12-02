package use_case.Over_Under.bet;

import entity.User;
import org.json.JSONObject;

/**
 * DAO for the Over/Under Bet Use Case (Hybrid).
 */
public interface OverUnderBetDataAccessInterface {

    /**
     * Returns the bet of the curren user for the gauntlet game.
     * @return the bet of the current user; null indicates that no one is logged into the application.
     */
    int getBet();

    /**
     * Saves the user's info.
     * @param user the user to save
     * @param info the default user info to save
     */
    void saveNew(User user, JSONObject info);

    /**
     * Retrieves the user associated with the given username.
     * @param username the username of the user.
     * @return the user associated with the username.
     */
    User get(String username);
}
