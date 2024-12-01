package entity;

import java.awt.Image;

public class OverUnderCardFactory {

    /**
     * Factory for creating a playing card.
     */

    /**
     * Factory for creating a card.
     * @param rank The rank of the card.
     * @param img The image of the card.
     * @return the card with the corresponding information.
     */
    public PlayingCard create(int rank, Image img) {
        return new PlayingCard(img, rank);
    }
}

