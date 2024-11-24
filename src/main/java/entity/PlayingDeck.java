package entity;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Concrete implementation of a standard deck of 52 playing cards.
 */
public class PlayingDeck implements DeckAbs {
    private final List<PlayingCard> cards;
    private int size;

    public PlayingDeck() {
        final String imagePath = "homework-5/images/";
        this.cards = new ArrayList<>();
        this.cards.add(new PlayingCard(new File(imagePath + "2.jpg"), 2));
        this.cards.add(new PlayingCard(new File(imagePath + "3.png"), 3));
        this.cards.add(new PlayingCard(new File(imagePath + "4.jpg"), 4));
        this.cards.add(new PlayingCard(new File(imagePath + "5.png"), 5));
        this.cards.add(new PlayingCard(new File(imagePath + "6.jpg"), 6));
        this.cards.add(new PlayingCard(new File(imagePath + "7.png"), 7));
        this.cards.add(new PlayingCard(new File(imagePath + "8.jpg"), 8));
        this.cards.add(new PlayingCard(new File(imagePath + "9.jpg"), 9));
        this.cards.add(new PlayingCard(new File(imagePath + "10.jpg"), 10));
        this.cards.add(new PlayingCard(new File(imagePath + "Jack.jpg"), 11));
        this.cards.add(new PlayingCard(new File(imagePath + "Queen.jpg"), 12));
        this.cards.add(new PlayingCard(new File(imagePath + "King.jpg"), 13));
        this.size = this.cards.size();
    }

    @Override
    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    /**
     * Returns card from deck to see if over or under.
     * @return card from deck to see if over or under.
     */

    public PlayingCard dealCard() {
        return cards.isEmpty() ? null : cards.remove(0);
    }
    // Might cause an error later on.

    /**
     * Returns remaining deck cards.
     * @return remaining deck cards.
     */
    @Override
    public int size() {
        return cards.size();
    }
}
