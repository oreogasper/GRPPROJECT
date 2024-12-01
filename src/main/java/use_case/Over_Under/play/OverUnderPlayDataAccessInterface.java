package use_case.over_under.play;

import org.json.JSONObject;
import entity.User;

/**
 * DAO for the Over/Under Play Use Case.
 */
public interface OverUnderPlayDataAccessInterface {

    /**
     * Returns the bet of the current user for the Over/Under game.
     *
     * @return the bet of the current user; null indicates that no user is logged into the application.
     */
    int getBet();

    /**
     * Saves the updated user data, including balance and game progress.
     *
     * @param user the user to save.
     * @param info the user info to save in JSON format.
     */
    void saveUpdatedUser(User user, JSONObject info);

    /**
     * Fetches the user data associated with the given username.
     *
     * @param username the username of the user.
     * @return the User object associated with that username.
     */
    User getUser(String username);

    /**
     * Logs the results of a game round.
     *
     * @param username the username of the user.
     * @param result the result of the game (e.g., "win", "lose").
     * @param betAmount the amount the user bet in the game.
     * @param finalBalance the balance of the user after the game.
     */
    void logGameResult(String username, String result, int betAmount, int finalBalance);
}
