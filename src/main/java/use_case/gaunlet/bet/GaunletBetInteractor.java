package use_case.gaunlet.bet;

import org.json.JSONObject;

import entity.GaunletGame;
import entity.User;
import entity.UserFactory;

/**
 * The Gaunlet Bet Interactor.
 */
public class GaunletBetInteractor implements GaunletBetInputBoundary {
    private final GaunletBetOutputBoundary userPresenter;
    private final GaunletBetDataAccessInterface userDataAccessObject;
    private final UserFactory userFactory;

    public GaunletBetInteractor(GaunletBetDataAccessInterface userDataAccessObject,
                                GaunletBetOutputBoundary gaunletBetOutputBoundary, UserFactory userFactory) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = gaunletBetOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(GaunletBetInputData gaunletBetInputData, int bet) {
        // Parse and validate the bet input
        final int betAmount = gaunletBetInputData.getBet();
        final User user = userDataAccessObject.get(gaunletBetInputData.getUsername());
        final int userBalance = user.getBalance();

        // Check if the bet is valid
        if (!isValidBet(betAmount, userBalance)) {
            System.out.println("bet = " + betAmount);
            // Handle invalid bet case
            userPresenter.prepareFailView("Invalid bet amount. "
                    + "Please bet a value between 10 tokens and your current balance.");
        } else {
            // Deduct the bet amount from the user's balance
            final JSONObject json = user.getInfo();
            final int newBalance = userBalance - betAmount;

            json.put("balance", newBalance);
            final User updatedUser = userFactory.create(user.getName(), user.getPassword(), json);
            userDataAccessObject.saveNew(updatedUser, json);

            // Update the bet in the presenter
            user.setBet(betAmount);
            userPresenter.setUserBet();

            // Notify the presenter of success
            final GaunletBetOutputData gaunletBetOutputData = new GaunletBetOutputData(betAmount, false);
            userPresenter.prepareSuccessView(gaunletBetOutputData);
        }
    }

    // Helper method to validate the bet
    private boolean isValidBet(int betAmount, int userBalance) {
        final GaunletGame betMin = new GaunletGame();
        return betAmount >= betMin.getMinBet() && betAmount <= userBalance;
    }

    @Override
    public void switchToGaunletGuessView() {
        userPresenter.switchToGaunletGuessView();
    }

    @Override
    public void switchToGameMenuView() {
        userPresenter.switchToGameMenuView();
    }
}
