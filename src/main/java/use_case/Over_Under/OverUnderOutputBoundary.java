package use_case.Over_Under;

import entity.PlayingCard;

/**
 * Output boundary interface for the Over/Under game.
 * This interface allows the interactor to communicate with the view layer.
 */
public interface OverUnderOutputBoundary {
    // Called when the game starts
    void showGameStarted();

    // Called to display the current card (after each guess)
    void updateCardDisplay(String cardInfo);

    // Called when the player makes a correct guess
    void showCorrectGuess(PlayingCard nextCard);

    // Called when the player makes an incorrect guess
    void showWrongGuess(PlayingCard nextCardInfo);

    void showCorrectGuess();

    void showWrongGuess();

    // Called to update the player's balance
    void updateBalance(int newBalance);

    // Called when there is an error (e.g., invalid bet)
    void showError(String errorMessage);
}
