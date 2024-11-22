package entity;

/**
 * Factory for creating Gaunlet game session.
 */
public class GaunletGameFactory implements GaunletFactory {
    private String coinGuess;
    private int diceGuess;
    private String rpsGuess;

    @Override
    public void create(String userCoinGuess, int userDiceGuess, String userRpsGuess) {
        this.coinGuess = userCoinGuess;
        this.diceGuess = userDiceGuess;
        this.rpsGuess = userRpsGuess;
    }
}
