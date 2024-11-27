package use_case.blackjack.stand;

import entity.BlackjackGame;
import use_case.blackjack.hit.BlackjackHitInputData;
import use_case.blackjack.hit.BlackjackHitInteractor;

/**
 * The Blackjack Stand Use Case Interactor.
 */
public class BlackjackStandInteractor implements BlackjackStandInputBoundary {
    private final BlackjackHitInteractor hitInteractor;
    private final BlackjackStandOutputBoundary outputBoundary;
    private final BlackjackGame blackjackGame;

    public BlackjackStandInteractor(BlackjackHitInteractor hitInteractor,
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
        if (blackjackGame.getDealerScore() > blackjackGame.getPlayerScore()) {
            turnState = "Lose";
        } else {
            turnState = "Win";
        }

        BlackjackStandOutputData standOutputData = new BlackjackStandOutputData(turnState,
                false, blackjackStandInputData.isEndPlayerTurn());

        outputBoundary.prepareSuccessView(standOutputData);
    }

    @Override
    public void switchToGameMenuView() {
        outputBoundary.switchToGameMenuView();
    }
}
