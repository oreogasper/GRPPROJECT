package use_case.Over_Under.bet;

import org.json.JSONObject;

import entity.User;
import entity.UserFactory;

/**
 * The Over/Under Bet Interactor.
 */
public class OverUnderBetInteractor implements OverUnderBetInputBoundary {
    private final OverUnderBetOutputBoundary userPresenter;
    private final OverUnderBetDataAccessInterface userDataAccessObject;
    private final UserFactory userFactory;

    public OverUnderBetInteractor(OverUnderBetDataAccessInterface userDataAccessObject,
                                  OverUnderBetOutputBoundary userPresenter,
                                  UserFactory userFactory) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(OverUnderBetInputData betInputData) {
        // Retrieve the user and bet details
        final int betAmount = betInputData.getBetAmt();
        final String username = betInputData.getUserName();
        final User user = userDataAccessObject.get(username);
        final int userBalance = user.getBalance();

        // Validate the bet
        if (isValidBet(betAmount, userBalance)) {
            // Deduct the bet amount and update the user's balance
            final JSONObject json = user.getInfo();
            final int newBalance = userBalance - betAmount;
            json.put("balance", newBalance);

            final User updatedUser = userFactory.create(user.getName(), user.getPassword(), json);
            userDataAccessObject.saveNew(updatedUser, json);

            // Set the bet for the user
            user.setBet(betAmount);
            userPresenter.setBet();

            // Notify the presenter of success
            final OverUnderBetOutputData outputData = new OverUnderBetOutputData(betAmount, false);
            userPresenter.prepareSuccessView(outputData);
        }
        else {
            // Notify the presenter of failure
            userPresenter.prepareFailView("Invalid bet amount. Please bet an amount between the "
                    + "minimum bet and your current balance.");
        }
    }

    /**
     * Validates the bet amount.
     *
     * @param betAmount   the amount being bet
     * @param userBalance the user's current balance
     * @return true if the bet is valid, false otherwise
     */
    private boolean isValidBet(int betAmount, int userBalance) {
        return betAmount >= 10 && betAmount <= userBalance;
    }

    @Override
    public void switchToOverUnderGameView() {
        userPresenter.switchToOverUnderPlayView();
    }

    @Override
    public void switchToGameMenuView() {
        userPresenter.switchToGameMenuView();
    }
}
