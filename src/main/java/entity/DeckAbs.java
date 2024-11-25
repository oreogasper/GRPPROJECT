package entity;

/**
 * Abstract representation of a Deck of cards.
 */
public interface DeckAbs {

    /**
     * Shuffles to deck to create new arrangement.
     */
    void shuffle();

    /**
     * Pulls a card from the deck to initiate/continue the game.
     */
    PlayingCard dealCard();

    /**
     * Checks how many cards are remaining.
     */
    int size();
}
