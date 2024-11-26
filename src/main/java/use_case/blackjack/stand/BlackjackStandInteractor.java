package use_case.blackjack.stand;

import entity.BlackjackGame;

/**
 * The Blackjack Stand Use Case Interactor.
 */
public class BlackjackStandInteractor implements BlackjackStandInputBoundary {
    private final BlackjackStandOutputBoundary outputBoundary;
    private final BlackjackGame blackjackGame;

    public BlackjackStandInteractor(BlackjackStandOutputBoundary outputBoundary,
                                    BlackjackGame blackjackGame) {
        this.outputBoundary = outputBoundary;
        this.blackjackGame = blackjackGame;
    }

    @Override
    public void execute(BlackjackStandInputData blackjackStandInputData) {

        final int score = blackjackGame.getPlayerScore();

        final BlackjackStandOutputData outputData = new BlackjackStandOutputData(score, false);

        outputBoundary.prepareSuccessView(outputData);
    }

    @Override
    public void switchToGameMenuView() {
        outputBoundary.switchToGameMenuView();
    }
}
