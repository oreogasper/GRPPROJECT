package use_case.blackjack.bet;

import entity.User;
import org.json.JSONObject;

/**
 * DAO for the Blackjack Bet Use Case.
 */
public interface BlackjackBetUserDataAccessInterface {

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
