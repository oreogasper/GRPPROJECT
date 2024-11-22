package entity;

/**
 * Factory for creating Gaunlet game session.
 */
public class GaunletGameFactory implements GaunletFactory {

    @Override
    public GaunletGame create(String userCoinGuess, int userDiceGuess, String userRpsGuess) {
        return new GaunletGame();
    }
}
