package use_case.blackjack.hit;

import entity.AbstractCard;
import entity.BlackjackGame;

/**
 * The Blackjack Hit Use Case Interactor.
 */
public class BlackjackHitInteractor implements BlackjackHitInputBoundary {

    private final BlackjackHitOutputBoundary outputBoundary;
    private final BlackjackHitDataAccessInterface blackjackGetCardDataAccessObject;
    private final BlackjackGame blackjackGame;

    public BlackjackHitInteractor(BlackjackHitOutputBoundary outputBoundary,
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

        final boolean bust = blackjackGame.isBust(blackjackGame.getPlayerCards());
        final boolean blackjack = blackjackGame.isBlackjack(blackjackGame.getPlayerCards());

        final BlackjackHitOutputData outputData = new BlackjackHitOutputData(card.getImage(), bust, blackjack, false);

        outputBoundary.prepareSuccessView(outputData);
    }

    @Override
    public void switchToGameMenuView() {
        outputBoundary.switchToGameMenuView();
    }
}
