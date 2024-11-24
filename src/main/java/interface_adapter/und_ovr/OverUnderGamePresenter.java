package interface_adapter.und_ovr;

import entity.PlayingCard;
import use_case.Over_Under.OverUnderOutputBoundary;

public class OverUnderGamePresenter implements OverUnderOutputBoundary {
    private final OverUnderViewModel viewModel;

    public OverUnderGamePresenter(OverUnderViewModel viewModel) {
        this.viewModel = viewModel;
    }

    private void updateView(String message) {
        viewModel.setMessage(message);
    }

    @Override
    public void showGameStarted() {
        updateView("Game started! Place your bet to begin.");
    }

    @Override
    public void updateCardDisplay(String cardInfo) {
        // Update the card display for the current card value
        viewModel.setCurrentCardValue(cardInfo);
        updateView("The current card value is: " + cardInfo);
    }

    @Override
    public void showCorrectGuess() {
        // Show success message without revealing the next card
        updateView("Correct guess! Keep going.");
        viewModel.setGuessResult("Correct! Keep guessing.");
    }

    @Override
    public void showWrongGuess() {
        // Show retry message without revealing any new information
        updateView("Wrong guess! Try again.");
        viewModel.setGuessResult("Wrong! Try again.");
    }

    @Override
    public void updateBalance(int newBalance) {
        // Update the balance in the ViewModel
        viewModel.setBalance(newBalance);
        updateView("Your balance is now: $" + newBalance);
    }

    @Override
    public void showError(String errorMessage) {
        // Set an error message in the ViewModel
        viewModel.setError(errorMessage);
        updateView("Error: " + errorMessage);
    }
}
