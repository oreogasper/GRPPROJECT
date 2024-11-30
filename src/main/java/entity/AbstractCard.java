package entity;

import java.awt.Image;

/**
 * Abstract interface for a playing card.
 */
public interface AbstractCard {
    /**
     * Returns the rank of the card.
     * @return the rank of the card.
     */
    int getRank();

    /**
     * Returns the name of the card.
     * @return the name of the card.
     */
    String getName();

    /**
     * Returns the suit of the card.
     * @return the suit of the card.
     */
    String getSuit();

    /**
     * Returns an image of the card.
     * @return the image of the card card.
     */
    Image getImage();
}
