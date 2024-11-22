package entity;

/**
 * Factory for creating gaunlet game session.
 */
public interface GaunletFactory {
    /**
     * Creates a new gaunlet session.
     * @param coinGuess the coin guess
     * @param diceGuess the dice guess
     * @param rpsGuess the rps guess
     * @return new game session
     */
    GaunletGame create(String coinGuess, int diceGuess, String rpsGuess);
}
