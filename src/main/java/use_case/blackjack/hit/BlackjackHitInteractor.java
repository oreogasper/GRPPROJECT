package use_case.blackjack.hit;

import entity.AbstractCard;
import entity.BlackjackGame;

/**
 * The Blackjack Get Card Use Case Interactor.
 */
public class BlackjackHitInteractor implements BlackjackHitInputBoundary {

    private final BlackjackGetCardOutputBoundary outputBoundary;
    private final BlackjackHitDataAccessInterface blackjackGetCardDataAccessObject;
    private final BlackjackGame blackjackGame;

    public BlackjackHitInteractor(BlackjackGetCardOutputBoundary outputBoundary,
                                  BlackjackHitDataAccessInterface blackjackGetCardDataAccessObject,
                                  BlackjackGame blackjackGame) {
        this.outputBoundary = outputBoundary;
        this.blackjackGetCardDataAccessObject = blackjackGetCardDataAccessObject;
        this.blackjackGame = blackjackGame;

    }

    @Override
    public void execute(BlackjackHitInputData blackjackGameInputData) {

        final String deckId = blackjackGame.getDeckId();

        final AbstractCard card = blackjackGetCardDataAccessObject.drawCard(deckId);

        blackjackGame.addPlayerCard(card);

        boolean bust = blackjackGame.isBust(blackjackGame.getPlayerCards());
        boolean win = blackjackGame.isWin(blackjackGame.getPlayerCards());


        final BlackjackHitOutputData outputData = new BlackjackHitOutputData(bust, win, false);

        outputBoundary.prepareSuccessView(outputData);
    }

    @Override
    public void switchToGameMenuView() {
        outputBoundary.switchToGameMenuView();
    }
}
