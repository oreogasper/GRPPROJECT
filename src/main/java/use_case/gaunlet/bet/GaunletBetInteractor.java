package use_case.gaunlet.bet;

import entity.GaunletGame;
import entity.User;
import entity.UserFactory;
import org.json.JSONObject;

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
        final int betAmount = gaunletBetInputData.getBet();
        final boolean validBet = isValidBet(betAmount);

        if (validBet) {
            userPresenter.setUserBet();
            final GaunletBetOutputData gaunletBetOutputData = new GaunletBetOutputData(betAmount, false);
            userPresenter.prepareSuccessView(gaunletBetOutputData);
        }
        else {
            userPresenter.prepareFailView("Invalid bet amount. Please bet a value "
                    +
                    "that is between 10 tokens and your current balance.");
        }
        userPresenter.setUserBet();

        final User userr = userDataAccessObject.get(gaunletBetInputData.getUsername());
        final JSONObject json = userr.getInfo();
        final int newBalance = userr.getBalance() - userr.getBet();

        json.put("balance", newBalance);
        final User user = userFactory.create(userr.getName(), userr.getPassword(), json);
        userDataAccessObject.saveNew(user, json);

        final GaunletBetOutputData gaunletBetOutputData = new GaunletBetOutputData(betAmount, false);
        userPresenter.prepareSuccessView(gaunletBetOutputData);
    }

    // Helper that checks if bet input fits requirements
    private boolean isValidBet(int betAmount) {
        final GaunletGame betRules = new GaunletGame();

        return betAmount >= betRules.getMinBet() && betAmount <= betRules.getMaxBet();
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
