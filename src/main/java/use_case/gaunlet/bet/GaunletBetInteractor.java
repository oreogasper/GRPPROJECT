package use_case.gaunlet.bet;

import entity.GaunletGame;

/**
 * The Gaunlet Bet Interactor.
 */
public class GaunletBetInteractor implements GaunletBetInputBoundary {
    private final GaunletBetOutputBoundary userPresenter;
    private final GaunletBetDataAccessInterface userDataAccessObject;

    public GaunletBetInteractor(GaunletBetDataAccessInterface userDataAccessObject,
                                GaunletBetOutputBoundary gaunletBetOutputBoundary) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = gaunletBetOutputBoundary;
    }

    @Override
    public void execute(GaunletBetInputData gaunletBetInputData) {
        final int betAmount = gaunletBetInputData.getBet();
        if (!isValidBet(betAmount)) {
            userPresenter.prepareFailView("Invalid bet amount. Please check the rules and try again.");
            return;
        }
        userDataAccessObject.setBet(betAmount);

        final GaunletBetOutputData gaunletBetOutputData = new GaunletBetOutputData(betAmount, false);
        userPresenter.prepareSuccessView(gaunletBetOutputData);
    }

    private boolean isValidBet(int betAmount) {
        final GaunletGame betRules = new GaunletGame();

        // TODO need to find a way to also check bet amount isn't over user's balance
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
