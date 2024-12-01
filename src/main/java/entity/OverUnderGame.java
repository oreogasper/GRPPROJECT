package entity;

import java.awt.Image;

public class OverUnderGame implements Game {
    private final int minBet = 50;
    private final int maxBet = 500;
    private final String rules = "Guess if the next card will be higher or lower than the current card.";
    private final String gameName = "Over/Under";

    private PlayingDeck deck;
    private CardAbs currentCard;
    private CardAbs nextCard;

    public OverUnderGame() {
        this.deck = new PlayingDeck();
        this.deck.shuffle();
        this.currentCard = deck.dealCard();
        this.nextCard = null;
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
    public String getRules() {
        return rules;
    }

    @Override
    public String getGameType() {
        return gameName;
    }

    /**
     * Starts a new round by dealing a new next card.
     */
    public void startRound() {
        if (deck.size() == 0) {
            throw new IllegalStateException("Deck is empty. Cannot deal more cards.");
        }
        nextCard = deck.dealCard();
    }

    /**
     * Compares the value of the next card to the current card.
     * @return true if nextCard is higher, false if lower.
     */
    public boolean isNextCardHigher() {
        if (currentCard == null || nextCard == null) {
            throw new IllegalStateException("Both currentCard and nextCard must be dealt before comparison.");
        }
        return nextCard.getRank() > currentCard.getRank();
    }

    /**
     * Resets the game and shuffles the deck.
     */
    public void resetGame() {
        this.deck = new PlayingDeck();
        this.deck.shuffle();
        currentCard = deck.dealCard();
        nextCard = null;
    }

    /**
     * Gets the image of the current card.
     * @return Image of the current card.
     */
    public Image getCurrentCardImage() {
        return currentCard != null ? currentCard.getImage() : null;
    }

    /**
     * Gets the image of the next card.
     * @return Image of the next card.
     */
    public Image getNextCardImage() {
        return nextCard != null ? nextCard.getImage() : null;
    }

    /**
     * Returns the number of remaining cards in the deck.
     * @return Remaining cards in the deck.
     */
    public int remainingCards() {
        return deck.size();
    }
}
