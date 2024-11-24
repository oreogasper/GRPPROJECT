package entity;

/**
 * The representation of a game in our program.
 */
public interface Game {

    /**
     * Returns the minimum bet of the game.
     * @return the minimum bet of the game.
     */
    int getMinBet();

    /**
     * Returns the maximum bet of the game.
     * @return the maximum bet of the game.
     */
    int getMaxBet();

    /**
     * Returns the rules of the game.
     * @return the rules of the game.
     */
    String getRules();

    /**
     * Returns the type of the game.
     * @return the type of the game.
     */
    String getGameType();

}
