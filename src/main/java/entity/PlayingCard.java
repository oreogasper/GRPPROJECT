package entity;

import java.awt.*;
/**
 * Concrete implementation of a playing card.
 */

public class PlayingCard implements CardAbs {
    private final int rank;
    private final Image card;

    public PlayingCard(Image card, int rank) {
        this.rank = rank;
        this.card = card;
    }

    @Override
    public int getRank() {
        return this.rank;
    }

    @Override
    public String toString() {
        return "The rank is: " + rank;
    }

    public Image getImage() {
        return this.card;
    }
}
