package use_case.Over_Under.play;

import entity.AbstractCard;
import org.json.JSONObject;
import entity.User;

/**
 * DAO for the Over/Under Play Use Case.
 */
public interface OverUnderPlayDataAccessInterface {

    /**
     * Creates a new deck of cards.
     */
    void createNewDeck();

    /**
     * Ensures connected to the API network for deck.
     * @return True or False if connected to cards API network.
     */
    boolean hasDeck();

    /**
     * Shuffles the deck randomly.
     */
    void shuffleDeck(String deckId);

    /**
     * Draws a card for the overHand game randomly.
     */
    AbstractCard drawCard(String deckId);


    /**
     * Returns deck id required to access deck.
     * @return
     */
    String getDeckID();
}
