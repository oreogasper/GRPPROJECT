package use_case.blackjack.stand;

import entity.AbstractCard;

public interface BlackjackStandCardDeckDataAccessInterface {

    /**
     * Initializes a new shuffled deck.
     */
    void createNewDeck();

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
    AbstractCard drawCard(String deckId);

}
