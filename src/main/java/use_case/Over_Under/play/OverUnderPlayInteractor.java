package use_case.Over_Under.play;

import entity.PlayingCard;
import entity.PlayingDeck;

/**
 * Interactor for the Over/Under game.
 */
public class OverUnderInteractor implements OverUnderInputBoundary {
    private PlayingDeck deck;
    private PlayingCard currentCard;
    private OverUnderOutputBoundary outputBoundary;
    private int balance;

    public OverUnderInteractor(int balance, OverUnderOutputBoundary outputBoundary) {
        this.deck = new PlayingDeck();
        this.outputBoundary = outputBoundary;
        this.balance = balance;
    }

    @Override
    public void startGame() {
        // Shuffle deck and deal the first card
        deck.shuffle();
        currentCard = deck.dealCard();
        this.outputBoundary.showGameStarted();
    }

    public void handleBet(int betAmount) {
        if (betAmount <= 0) {
            throw new IllegalArgumentException("Invalid bet amount.");
        } else if (betAmount > this.balance) {
            throw new IllegalArgumentException("Not enough tokens!");
        }
        this.balance -= betAmount;
    }

    @Override
    public void processGuess(boolean isHigher, int betAmount) {
        // Deal the next card and check if the guess is correct
        PlayingCard nextCard = deck.dealCard();
        boolean isCorrect = (isHigher && nextCard.getRank() > currentCard.getRank())
                || (!isHigher && nextCard.getRank() < currentCard.getRank());

        // Update balance based on the result
        String guessResult = isCorrect ? "Correct" : "Wrong";
        if (isCorrect) {
            this.balance += betAmount;
        } else {
            this.balance -= betAmount;
        }

        // Create the output data to send to the presenter
        OverUnderOutputData outputData = new OverUnderOutputData(
                currentCard.getRank(),
                nextCard.getRank(),
                this.balance,
                guessResult,
                null
        );

        // Notify the presenter or view model to show the result
        if (isCorrect) {
            outputBoundary.prepareSuccessView(outputData);
        } else {
            outputBoundary.prepareFailView("Your guess was wrong!");
        }

        // Update the current card for the next round
        currentCard = nextCard;
    }

    public PlayingCard getCurrentCard() {
        return this.currentCard;
    }

    public int getBalance() {
        return this.balance;
    }

    public void setOutputBoundary(OverUnderOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }
}
