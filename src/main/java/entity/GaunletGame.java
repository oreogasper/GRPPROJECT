package entity;

import java.util.Random;

/**
 * Gaunlet game entity.
 */
public class GaunletGame implements Game {
    private static final Random RANDOM = new Random();
    private final int minBet;
    private final int minAmount = 10;
    private final int maxAmount = 0;
    private final String gameType;

    public GaunletGame() {
        this.minBet = minAmount;
        this.gameType = "gauntlet";

    }

    @Override
    public int getMinBet() {
        return minBet;
    }

    @Override
    public int getMaxBet() {
        return 0;
    }

    @Override
    public String getGameType() {
        return gameType;
    }

    // The game outcomes generator
    /**
     * Generates coin flip outcome.
     * @return coin outcome.
     */
    public String flipCoin() {
        final String result;
        if (RANDOM.nextBoolean()) {
            result = "Heads";
        }
        else {
            result = "Tails";
        }
        return result;
    }

    /**
     * Generates dice roll outcome.
     * @return rps outcome.
     */
    public int rollDice() {
        final int diceBound = 6;
        return RANDOM.nextInt(diceBound) + 1;
    }

    /**
     * Generates rock, paper, scissors outcome.
     * @return rps outcome.
     */
    public String getRpsOutcome() {
        final String[] choices = {"Rock", "Paper", "Scissors"};
        return choices[RANDOM.nextInt(choices.length)];
    }

    @Override
    public String getRules() {
        return "";
    }

}
