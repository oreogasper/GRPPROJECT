package use_case.blackjack.stand;

import entity.BlackjackGame;
import use_case.blackjack.hit.BlackjackHitInputBoundary;
import use_case.blackjack.hit.BlackjackHitInputData;
import use_case.blackjack.hit.BlackjackHitInteractor;

/**
 * The Blackjack Stand Use Case Interactor.
 */
public class BlackjackStandInteractor implements BlackjackStandInputBoundary {
    private final BlackjackHitInputBoundary hitInteractor;
    private final BlackjackStandOutputBoundary outputBoundary;
    private final BlackjackGame blackjackGame;

    public BlackjackStandInteractor(BlackjackHitInputBoundary hitInteractor,
                                    BlackjackStandOutputBoundary outputBoundary,
                                    BlackjackGame blackjackGame) {
        this.hitInteractor = hitInteractor;
        this.outputBoundary = outputBoundary;
        this.blackjackGame = blackjackGame;

    }

    @Override
    public void execute(BlackjackStandInputData blackjackStandInputData) {

        while (blackjackGame.getDealerScore() < 17) {
            BlackjackHitInputData hitInputData = new BlackjackHitInputData(true);
            hitInteractor.execute(hitInputData);

        }

        String turnState = null;
        if (blackjackGame.isBust(blackjackGame.getDealerCards())) {
            turnState = "Win";
        } else if (blackjackGame.isBust(blackjackGame.getPlayerCards())) {
            turnState = "Lose";
        } else if (blackjackGame.getPlayerScore() > blackjackGame.getDealerScore()) {
            turnState = "Win";
        } else if (blackjackGame.getPlayerScore() < blackjackGame.getDealerScore()) {
            turnState = "Lose";
        } else {
            turnState = "Draw";
        }

        BlackjackStandOutputData standOutputData = new BlackjackStandOutputData(turnState,
                false, blackjackStandInputData.isEndPlayerTurn(),blackjackGame.getDealerScore());

        outputBoundary.prepareSuccessView(standOutputData);
    }

    @Override
    public void switchToGameMenuView() {
        outputBoundary.switchToGameMenuView();
    }

    @Override
    public void switchToBetView() {
        blackjackGame.resetGame();
        outputBoundary.switchToBetView();
    }
}
