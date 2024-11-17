package use_case.gaunlet.bet;

/**
 * The Gaunlet Bet Interactor.
 */
public class GaunletBetInteractor implements GaunletBetInputBoundary {
    private final GaunletBetOutputBoundary userPresenter;

    public GaunletBetInteractor(GaunletBetOutputBoundary gaunletBetOutputBoundary) {
        this.userPresenter = gaunletBetOutputBoundary;
    }

    @Override
    public void execute(GaunletBetInputData gaunletBetInputData) {
        // if (userDataAccessObject.validBet(GaunletBetInputData.getBet())) {
            // userPresenter.prepareFailView("Bet Amount does not meet minimum requirements.");
        // }
        // else {
            // final User user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword());
            // userDataAccessObject.save(user);

            // final GaunletBetOutputData gaunletBetOutputData = new GaunletBetOutputData(
        // GaunletBetInputData.getBet(), false);
            // userPresenter.prepareSuccessView(gaunletBetOutputData);
        // }
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
