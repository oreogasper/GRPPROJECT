package entity;

import java.awt.Image;

/**
 * Factory for creating a playing card.
 */
public class CardFactory {

    /**
     * Factory for creating a card.
     * @param rank The rank of the card.
     * @param suit The suit of the card.
     * @param name The name of the card.
     * @param img The image of the card.
     * @return the card with the corresponding information.
     */
    public Card create(int rank, String suit, String name, Image img) {
        return new Card(rank, suit, name, img);
    }
}
