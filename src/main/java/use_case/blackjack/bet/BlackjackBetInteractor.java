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
    private final BlackjackGame blackjackGame;
    private final UserFactory userFactory;

    public BlackjackBetInteractor(BlackjackBetOutputBoundary outputBoundary,
                                  BlackjackBetUserDataAccessInterface blackjackBetUserDataAccessInterface,
                                  BlackjackGame blackjackGame,
                                  UserFactory userFactory) {
        this.outputBoundary = outputBoundary;
        this.blackjackBetUserDataAccessInterface = blackjackBetUserDataAccessInterface;
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
                    false);
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
        outputBoundary.switchToBlackjackGameView();
    }


    @Override
    public void switchToGameMenuView() {
        outputBoundary.switchToGameMenuView();
    }


}
