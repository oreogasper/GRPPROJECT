package entity;

import org.json.JSONObject;

import java.util.Date;

/**
 * The representation of a user in our program.
 */
public interface User {

    /**
     * Returns the username of the user.
     * @return the username of the user.
     */
    String getName();

    /**
     * Sets the name of the user.
     * @param name is the new name of the user.
     */
    void setName(String name);

    /**
     * Returns the password of the user.
     * @return the password of the user.
     */
    String getPassword();

    /**
     * Sets the password of the user.
     * @param password is the new password of the user.
     */
    void setPassword(String password);

    /**
     * Returns the info of the user.
     * @return the info of the user.
     */
    JSONObject getInfo();

    /**
     * Returns the token count of the user.
     * @return the token count of the user.
     */
    int getBalance();

    /**
     * Updates the token count of the user.
     * @param amount is the amount won or lost.
     */
    void updateBalance(int amount);

    /**
     * Returns the game win count of the user.
     * @return the game win count of the user.
     */
    int getWins();

    /**
     * Updates that the user won a game.
     * Updates that the user played a game.
     */
    void wonGame();

    /**
     * Returns the game loss count of the user.
     * @return the game loss count of the user.
     */
    int getLosses();

    /**
     * Updates that the user lost a game.
     * Updates that the user played a game.
     */
    void lostGame();

    /**
     * Returns the game play count of the user.
     * @return the game play count of the user.
     */
    int getGames();

    /**
     * Returns the last time the user spun the wheel.
     * @return the last time the user spun the wheel.
     */
    long getLastSpin();

    /**
     * Updates the last time the user spun the wheel.
     * @param lastSpin is the time of last spin.
     */
    void setLastSpin(long lastSpin);

    /**
     * Sets current bet of the user.
     * @param bet is the time of last spin.
     */
    void setBet(int bet);

    /**
     * Returns the current bet of the user.
     * @return the current bet of the user.
     */
    int getBet();
}
