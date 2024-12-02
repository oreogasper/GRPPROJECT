package interface_adapter.und_ovr.play;

import entity.AbstractCard;
import entity.CardAbs;
import entity.User;

import java.awt.*;

/**
 * The state for the Over/Under Play View Model.
 */
public class OverUnderPlayState {
    private User user;
    private AbstractCard currentCard;
    private AbstractCard nextCard;
    private boolean guess;
    private String guessError;
    private int wrongGuesses;
    private boolean gameEnded;

    // User-related methods
    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    // Card-related methods
    public Image getCurrentCard() {
        return currentCard.getImage();
    }

    public void setCurrentCard(AbstractCard currentCard) {
        this.currentCard = currentCard;
    }

    public AbstractCard getNextCard() {
        return nextCard;
    }

    public void setNextCard(AbstractCard nextCard) {
        this.nextCard = nextCard;
    }

    // Guess-related methods
    public boolean getGuess() {
        return guess;
    }

    public void setGuess(boolean guess) {
        this.guess = guess;
    }

    public String getGuessError() {
        return guessError;
    }

    public void setGuessError(String guessError) {
        this.guessError = guessError;
    }

    // Score-related methods
    public int getWrongGuesses() {
        return this.wrongGuesses;
    }

    /**
     * Increments number of wrong guesses by 1.
     */
    public void setWrongGuesses() {
        this.wrongGuesses += 1;
    }

    // Game status
    public boolean isGameEnded() {
        return gameEnded;
    }

    public void setGameEnded(boolean gameEnded) {
        this.gameEnded = gameEnded;
    }

    @Override
    public String toString() {
        return "OverUnderPlayState{" + "user=" + user + ", currentCard=" + currentCard + '\''
                + ", nextCard=" + nextCard + '\''
                + ", guess='" + guess + '\''
                + ", guessError='" + guessError + '\''
                + ", wrongGuesses=" + wrongGuesses + '\''
                + ", gameEnded=" + gameEnded + '}';
    }
}
