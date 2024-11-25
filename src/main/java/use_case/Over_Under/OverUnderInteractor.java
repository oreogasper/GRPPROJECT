package use_case.Over_Under;

import entity.CommonUser;
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

    public OverUnderInteractor(CommonUser user, OverUnderOutputBoundary outputBoundary) {
        this.deck = new PlayingDeck();
        this.outputBoundary = outputBoundary;
        this.balance = user.getBalance();
    }

    @Override
    public void startGame() {
        deck.shuffle();
        currentCard = deck.dealCard();
        this.outputBoundary.showGameStarted();
    }

    @Override
    public void handleBet(int betAmount) {
        if (betAmount <= 0) {
            throw new IllegalArgumentException("Invalid bet amount.");
        }
        else if (betAmount > this.balance) {
            throw new IllegalArgumentException("Not enough tokens!");
        }
        this.balance -= betAmount;
    }

    @Override
    public void processGuess(boolean isHigher, int betAmount) {
        final PlayingCard nextCard = deck.dealCard();
        final boolean isCorrect = isHigher && nextCard.getRank() > currentCard.getRank()
                || !isHigher && nextCard.getRank() < currentCard.getRank();

        if (isCorrect) {
            outputBoundary.showCorrectGuess(nextCard);
        }
        else {
            outputBoundary.showWrongGuess(nextCard);
        }

        currentCard = nextCard;
    }

    @Override
    public PlayingCard getCurrentCard() {
        return this.currentCard;
    }

    @Override
    public int getBalance() {
        return this.balance;
    }

    public void setOutputBoundary(OverUnderOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }
}
