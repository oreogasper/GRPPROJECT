package entity;

public class GaunletGame implements Game {
    private final int minBet;
    private final int maxBet;
    private final int minAmount = 10;
    private final int maxAmount = 100;
    private final String gameType;

    public GaunletGame() {
        this.minBet = minAmount;
        this.maxBet = maxAmount;
        this.gameType = "gauntlet";
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
