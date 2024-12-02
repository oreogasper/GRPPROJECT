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
    public void execute(BlackjackHitInputData inputData) {

        final String deckId = blackjackGetCardDataAccessObject.getDeckID();
        final AbstractCard card = blackjackGetCardDataAccessObject.drawCard(deckId);
        blackjackGame.addPlayerCard(card);

        String turnState = null;

        if (blackjackGame.isBust(blackjackGame.getPlayerCards())) {
            turnState = "Lose";
        } else if (blackjackGame.isBlackjack(blackjackGame.getPlayerCards())) {
            turnState = "Dealer";
        } else {
            turnState = "Player";
        }

        final BlackjackHitOutputData outputData = new BlackjackHitOutputData(card.getImage(), turnState,
                blackjackGame.getPlayerScore(), false);

        outputBoundary.prepareSuccessView(outputData);

    }


    @Override
    public void switchToGameMenuView() {
        outputBoundary.switchToGameMenuView();
    }
}
