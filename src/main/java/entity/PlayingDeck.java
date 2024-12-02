package entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Concrete implementation of a standard deck of 52 playing cards.
 */
public class PlayingDeck implements DeckAbs {
    private final List<PlayingCard> cards;

    public PlayingDeck() {
        this.cards = new ArrayList<>();
        final String imagePath = "homework-5/images/";

        // Helper method to create a card
        addCard(imagePath + "2.jpg", 2);
        addCard(imagePath + "3.png", 3);
        addCard(imagePath + "4.jpg", 4);
        addCard(imagePath + "5.png", 5);
        addCard(imagePath + "6.jpg", 6);
        addCard(imagePath + "7.png", 7);
        addCard(imagePath + "8.jpg", 8);
        addCard(imagePath + "9.jpg", 9);
        addCard(imagePath + "10.jpg", 10);
        addCard(imagePath + "Jack.jpg", 11);
        addCard(imagePath + "Queen.jpg", 12);
        addCard(imagePath + "King.jpg", 13);
    }

    private void addCard(String filePath, int value) {
        try {
            // Attempt to load the image
            final Image cardImage = ImageIO.read(new File(filePath));
            cards.add(new PlayingCard(cardImage, value));
        }
        catch (IOException e) {
            // Log a detailed error message with stack trace
            System.err.println("Error: Failed to load card image.");
            System.err.println("File Path: " + filePath);
            System.err.println("Card Value: " + value);
            System.err.println("Cause: " + e.getMessage());
            e.printStackTrace(System.err);
        }
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
     * Returns the number of remaining deck cards.
     * @return remaining deck cards.
     */
    @Override
    public int size() {
        return cards.size();
    }
}
