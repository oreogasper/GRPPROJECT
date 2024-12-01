package use_case.Over_Under.bet;

import entity.PlayingCard;

public interface OverUnderBetOutputBoundary {
    void presentCard(PlayingCard card);
    void presentError(String errorMessage);

    void showGameStarted();

    void showCorrectGuess(PlayingCard currentCard);

    void showWrongGuess(PlayingCard currentCard);

    void prepareSuccessView(OverUnderOutputData outputData);

    void prepareFailView(String s);
}
