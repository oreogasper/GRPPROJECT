package use_case.Over_Under.bet;

import entity.User;
import org.json.JSONObject;

/**
 * DAO for the Over/Under Bet Use Case (Hybrid).
 */
public interface OverUnderBetDataAccessInterface {

    /**
     * Checks if the given bet is valid based on the user's current balance.
     * @param bet the given bet amount.
     * @return true if the bet is valid, otherwise false.
     */
    boolean validBet(int bet);

    /**
     * Saves the user's info.
     * @param user the user to save
     * @param info the default user info to save
     */
    void saveNew(User user, JSONObject info);

    /**
     * Saves the bet information by updating the user's balance.
     * @param user the user who made the bet.
     */
    void updateBalance(User user);

    /**
     * Retrieves the user associated with the given username.
     * @param username the username of the user.
     * @return the user associated with the username.
     */
    User get(String username);
}
