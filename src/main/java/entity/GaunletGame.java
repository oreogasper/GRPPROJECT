package entity;

public class GaunletGame implements Game {
    private final int minBet;
    private final int maxBet;
    private final int minAmount = 10;
    private final int maxAmount = 100;
    private final String gameType;
    private final String coinGuess;
    private final String diceGuess;
    private final String rpsGuess;

    public GaunletGame() {
        this.minBet = minAmount;
        this.maxBet = maxAmount;
        this.gameType = "gauntlet";
        this.coinGuess = "";
        this.diceGuess = "";
        this.rpsGuess = "";
    }

    @Override
    public int getMinBet() {
        return minBet;
    }

    @Override
    public int getMaxBet() {
        return maxBet;
    }

    @Override
    public String getGameType() {
        return gameType;
    }

    // Todo need to implement rules
    @Override
    public String getRules() {
        return null;
    }

}
