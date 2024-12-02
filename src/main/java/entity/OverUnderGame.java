package entity;

import java.awt.Image;

public class OverUnderGame implements Game {
    private final String gameName = "Over/Under";
    private AbstractCard currentCard;
    private AbstractCard nextCard;
    private String deckId;

    public OverUnderGame() {}

    @Override
    public int getMinBet() {
        return 0;
    }

    @Override
    public int getMaxBet() {
        return 0;
    }

    @Override
    public String getRules() {
        return "";
    }

    @Override
    public String getGameType() {
        return gameName;
    }

    public AbstractCard getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(AbstractCard card) {
        this.currentCard = card;
    }

    public AbstractCard getNextCard() {
        return nextCard;
    }

    public void setNextCard(AbstractCard card) {
        this.nextCard = card;
    }

    public String getDeckId() {
        return deckId;
    }

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }

    public boolean hasDeck() {
        return deckId != null;
    }

    // Evaluates if the guess is correct
    public boolean evaluateGuess(boolean isHigher) {
        if (currentCard == null || nextCard == null) {
            throw new IllegalStateException("Cards are not set!");
        }
        int comparison = nextCard.getRank() - currentCard.getRank();
        return (isHigher && comparison > 0) || (!isHigher && comparison < 0);
    }

    // Retrieves the image of the current card
    public Image getCurrentCardImage() {
        if (currentCard == null) {
            throw new IllegalStateException("Current card is not set!");
        }
        return currentCard.getImage();
    }

    // Retrieves the image of the next card
    public Image getNextCardImage() {
        if (nextCard == null) {
            throw new IllegalStateException("Next card is not set!");
        }
        return nextCard.getImage();
    }

    // Resets the game state
    public void resetGame() {
        currentCard = null;
        nextCard = null;
        deckId = null;
    }
}
