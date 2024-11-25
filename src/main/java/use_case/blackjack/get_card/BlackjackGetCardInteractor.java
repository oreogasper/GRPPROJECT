package use_case.blackjack.get_card;

import entity.AbstractCard;

/**
 * The Blackjack Get Card Use Case Interactor.
 */
public class BlackjackGetCardInteractor implements BlackjackGetCardInputBoundary {

    private final BlackjackGetCardOutputBoundary outputBoundary;
    private final BlackjackGetCardDataAccessInterface blackjackGetCardDataAccessObject;

    public BlackjackGetCardInteractor(BlackjackGetCardOutputBoundary outputBoundary,
                                      BlackjackGetCardDataAccessInterface blackjackGetCardDataAccessObject) {
        this.outputBoundary = outputBoundary;
        this.blackjackGetCardDataAccessObject = blackjackGetCardDataAccessObject;

    }

    @Override
    public void execute(BlackjackGetCardInputData blackjackGameInputData) {
        if (!blackjackGetCardDataAccessObject.hasDeck()) {
            blackjackGetCardDataAccessObject.createNewDeck();
        }

        final String deckId = blackjackGetCardDataAccessObject.getDeckID();

        final AbstractCard card = blackjackGetCardDataAccessObject.drawCard(deckId);

        final BlackjackGetCardOutputData outputData = new BlackjackGetCardOutputData(card, false);

        outputBoundary.prepareSuccessView(outputData);
    }

    @Override
    public void switchToGameMenuView() {
        outputBoundary.switchToGameMenuView();
    }
}
