package use_case.blackjack.get_card;

import entity.CardAbs;

/**
 * DAO for the Get Card Use Case.
 */
public interface BlackjackGetCardDataAccessInterface {

    /**
     * Creates a new deck and returns its ID.
     * @return deckId the ID of the new deck.
     */
    String createNewDeck();

    /**
     * Shuffles the current deck.
     */
    void shuffleDeck();

    /**
     * Draw a card from the current deck.
     */
    CardAbs drawCard();
}
