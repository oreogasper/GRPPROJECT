package use_case.blackjack.bet;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import data_access.DBCardDeckDataAccessObject;
import entity.BlackjackGame;
import entity.AbstractCard;
import entity.User;
import entity.UserFactory;
import org.json.JSONObject;
import use_case.gaunlet.bet.GaunletBetOutputData;

/**
 * The Blackjack Bet Use Case Interactor.
 */
public class BlackjackBetInteractor implements BlackjackBetInputBoundary{

    private final BlackjackBetOutputBoundary outputBoundary;
    private final BlackjackBetUserDataAccessInterface blackjackBetUserDataAccessInterface;
    private final DBCardDeckDataAccessObject dbCardDeckDataAccessObject;
    private final BlackjackGame blackjackGame;
    private final UserFactory userFactory;

    public BlackjackBetInteractor(BlackjackBetOutputBoundary outputBoundary,
                                  BlackjackBetUserDataAccessInterface blackjackBetUserDataAccessInterface,
                                  DBCardDeckDataAccessObject dbCardDeckDataAccessObject,
                                  BlackjackGame blackjackGame,
                                  UserFactory userFactory) {
        this.outputBoundary = outputBoundary;
        this.blackjackBetUserDataAccessInterface = blackjackBetUserDataAccessInterface;
        this.dbCardDeckDataAccessObject = dbCardDeckDataAccessObject;
        this.blackjackGame = blackjackGame;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(BlackjackBetInputData blackjackBetInputData) {
        final int betAmount = blackjackBetInputData.getBet();
        final User user = blackjackBetUserDataAccessInterface.get(blackjackBetInputData.getUsername());
        final int userBalance = user.getBalance();

        if (!isValidBet(betAmount, userBalance)) {
            outputBoundary.prepareFailView("Invalid bet amount. "
                    + "Please bet a value between 10 tokens and your current balance.");
        } else {
            final JSONObject json = user.getInfo();
            final int newBalance = userBalance - betAmount;

            json.put("balance", newBalance);
            final User updatedUser = userFactory.create(user.getName(), user.getPassword(), json);
            blackjackBetUserDataAccessInterface.saveNew(updatedUser, json);


            // Notify the presenter of success
            final BlackjackBetOutputData blackjackBetOutputData = new BlackjackBetOutputData(betAmount,
                    false, false, null, null, 0, 0
                    );
            outputBoundary.prepareSuccessView(blackjackBetOutputData);
        }

    }

    private boolean isValidBet(int betAmount, int userBalance) {
        if (betAmount > userBalance || betAmount < blackjackGame.getMinBet()) {
            return false;
        } else {
            return true;
        }
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
