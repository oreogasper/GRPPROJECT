package use_case.Over_Under;

import entity.PlayingCard;

public interface OverUnderInputBoundary {
    // Starts the game and shuffles the deck
    void startGame();

    // Handles the betting process, checking the amount is valid and adjusting the balance
    void handleBet(int betAmount);

    // Processes the guess, checks if it's correct, updates balance and notifies the output boundary
    void processGuess(boolean isHigher, int betAmount);

    // Method to retrieve the current card
    PlayingCard getCurrentCard();

    // Method to retrieve the current balance
    int getBalance();
}
