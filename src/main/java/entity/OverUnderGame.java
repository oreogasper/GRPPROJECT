package entity;

public class OverUnderGame implements Game {

    private final int minBet;
    private final int maxBet;
    private final String rules;
    private final String gameType;

    private AbstractCard currentCard;
    private AbstractCard nextCard;

    public OverUnderGame() {
        this.minBet = 15;
        this.maxBet = 500;
        this.rules = "Guess if the next card will be higher or lower than the current card.";
        this.gameType = "Over/Under";
    }

    public int getMinBet() {
        return this.minBet;
    }

    public int getMaxBet() {
        return this.maxBet;
    }

    public String getGameType() {
        return this.gameType;
    }

    @Override
    public String getRules() {
        return rules;
    }

    public AbstractCard getCurrentCard() {
        return currentCard;
    }

    public AbstractCard getNextCard() {
        return nextCard;
    }

    public void setCurrentCard(AbstractCard currentCard) {
        this.currentCard = currentCard;
    }

    public void setNextCard(AbstractCard nextCard) {
        this.nextCard = nextCard;
    }
}
