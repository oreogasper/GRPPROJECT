package use_case.blackjack.hit;

import entity.AbstractCard;
import entity.BlackjackGame;

/**
 * The Blackjack Get Card Use Case Interactor.
 */
public class BlackjackGetCardInteractor implements BlackjackGetCardInputBoundary {

    private final BlackjackGetCardOutputBoundary outputBoundary;
    private final BlackjackGetCardDataAccessInterface blackjackGetCardDataAccessObject;
    private final BlackjackGame blackjackGame;

    public BlackjackGetCardInteractor(BlackjackGetCardOutputBoundary outputBoundary,
                                      BlackjackGetCardDataAccessInterface blackjackGetCardDataAccessObject,
                                      BlackjackGame blackjackGame) {
        this.outputBoundary = outputBoundary;
        this.blackjackGetCardDataAccessObject = blackjackGetCardDataAccessObject;
        this.blackjackGame = blackjackGame;

    }

    @Override
    public void execute(BlackjackGetCardInputData blackjackGameInputData) {

        final String deckId = blackjackGame.getDeckId();

        final AbstractCard card = blackjackGetCardDataAccessObject.drawCard(deckId);

        final BlackjackGetCardOutputData outputData = new BlackjackGetCardOutputData(card, false);

        outputBoundary.prepareSuccessView(outputData);
    }

    @Override
    public void switchToGameMenuView() {
        outputBoundary.switchToGameMenuView();
    }
}
