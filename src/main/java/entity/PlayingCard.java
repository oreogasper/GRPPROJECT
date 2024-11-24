package entity;

import java.io.File;
/**
 * Concrete implementation of a playing card.
 */

public class PlayingCard implements CardAbs {
    private final int rank;
    private final File card;

    public PlayingCard(File card, int rank) {
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

    public File getImage() {
        return this.card;
    }
}
