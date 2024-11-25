package use_case.blackjack.get_card;

import entity.CardAbs;

/**
 * DAO for the Get Card Use Case.
 */
public interface BlackjackGetCardDataAccessInterface {

    /**
     * Initializes a new deck to be drawn from/shuffled/etc.
     * @param shuffle Whether to shuffle the deck or not.
     */
    void createNewDeck(boolean shuffle);

    /**
     * Returns the current decks ID.
     * @return deckId the ID of the new deck if there is one, null otherwise.
     */
    String getDeckID();

    /**
     * Returns whether there is a current deck or not.
     * @return Whether an initial deck and deck ID has been initialized.
     */
    boolean hasDeck();

    /**
     * Shuffles the current deck.
     * @param deckId The ID of the deck to be shuffled.
     */
    void shuffleDeck(String deckId);

    /**
     * Draw a card from the current deck.
     * @param deckId The ID of the deck to draw a card from.
     */
    CardAbs drawCard(String deckId);

}
