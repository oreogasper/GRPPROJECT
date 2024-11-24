package entity;

import java.io.File;

/**
 * Abstract representation of a Card.
 */
public interface CardAbs {
    /**
     * Returns the rank of the card.
     * @return the rank of the card.
     */
    int getRank();

    /**
     * Returns the string representation.
     * @return the string representation.
     */
    String toString();

    /**
     * Returns the card.
     * @return the visual card.
     */
    File getImage();
}
