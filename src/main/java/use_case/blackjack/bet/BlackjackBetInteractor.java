package use_case.blackjack.bet;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import data_access.DBCardDeckDataAccessObject;
import entity.BlackjackGame;
import entity.AbstractCard;

/**
 * The Blackjack Bet Use Case Interactor.
 */
public class BlackjackBetInteractor implements BlackjackBetInputBoundary{

    private final BlackjackBetOutputBoundary outputBoundary;
    private final DBCardDeckDataAccessObject dbCardDeckDataAccessObject;
    private final BlackjackGame blackjackGame;

    public BlackjackBetInteractor(BlackjackBetOutputBoundary outputBoundary,
                                  DBCardDeckDataAccessObject dbCardDeckDataAccessObject,
                                  BlackjackGame blackjackGame) {
        this.outputBoundary = outputBoundary;
        this.dbCardDeckDataAccessObject = dbCardDeckDataAccessObject;
        this.blackjackGame = blackjackGame;
    }

    @Override
    public void execute(BlackjackBetInputData blackjackBetInputData) {
        // TODO
    }

    @Override
    public void switchToBlackjackGameView() {
        this.initializeBlackjackGame();
        final BlackjackBetOutputData outputData= new BlackjackBetOutputData(0, false,
                true, blackjackGame.getPlayerCardImages(), blackjackGame.getDealerCardImages(),
                blackjackGame.getPlayerScore(), blackjackGame.getDealerScore());

        outputBoundary.switchToBlackjackGameView(outputData);
    }


    @Override
    public void switchToGameMenuView() {
        outputBoundary.switchToGameMenuView();
    }

    private void initializeBlackjackGame() {
        if (!dbCardDeckDataAccessObject.hasDeck()) {
            dbCardDeckDataAccessObject.createNewDeck();
        }
        dbCardDeckDataAccessObject.shuffleDeck(dbCardDeckDataAccessObject.getDeckID());

        List<AbstractCard> playerCards = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            playerCards.add(dbCardDeckDataAccessObject.drawCard(dbCardDeckDataAccessObject.getDeckID()));
        }

        List<AbstractCard> dealerCards = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            dealerCards.add(dbCardDeckDataAccessObject.drawCard(dbCardDeckDataAccessObject.getDeckID()));
        }

        for (AbstractCard card : playerCards) {
            blackjackGame.addPlayerCard(card);
        }

        for (AbstractCard card : dealerCards) {
            blackjackGame.addDealerCard(card);
        }
    }

}
