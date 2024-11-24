package use_case.Over_Under;

import entity.PlayingCard;
import view.OverUnderView;

public class OverUnderGamePresenter implements OverUnderOutputBoundary {
    private OverUnderView view;

    public OverUnderGamePresenter(OverUnderView view) {
        this.view = view;
    }

    @Override
    public void showGameStarted() {
        view.displayMessage("Game started!");
    }

    @Override
    public void updateCardDisplay(String cardInfo) {
        view.updateCardDisplay(cardInfo);
    }

    @Override
    public void showCorrectGuess(PlayingCard nextCardInfo) {
        String message = "Correct guess! Next card: " + nextCardInfo.getRank();
        view.displayMessage(message);
        // Optionally, update the card display
        view.updateCardDisplay("Next card: " + nextCardInfo.getRank());
    }

    @Override
    public void showWrongGuess(PlayingCard nextCardInfo) {
        String message = "Wrong guess! Next card: " + nextCardInfo.getRank();
        view.displayMessage(message);
        // Optionally, update the card display
        view.updateCardDisplay("Next card: " + nextCardInfo.getRank());
    }

    @Override
    public void updateBalance(int newBalance) {
        view.updateBalanceDisplay(newBalance);
    }

    @Override
    public void showError(String errorMessage) {
        view.displayError(errorMessage);
    }
}
