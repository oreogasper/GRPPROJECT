package use_case.gaunlet.bet;

import org.json.JSONObject;

import entity.User;

/**
 * DAO for the Gaunlet Bet Use Case.
 */
public interface GaunletBetDataAccessInterface {

    /**
     * Sets the bet amount indicating who is the current user of the application.
     * @param bet the new current username; null to indicate that no one is currently logged into the application.
     */
    void setBet(int bet);

    /**
     * Saves the user's info.
     * @param user the user to save
     * @param info the default user info to save
     */
    void saveNew(User user, JSONObject info);

    /**
     * Returns the user associated with that username.
     * @param username the username of the user
     * @return the user associated with that username
     */
    User get(String username);

}
