package use_case.gaunlet.guess;

import use_case.gaunlet.bet.GaunletBetInputBoundary;
import use_case.gaunlet.bet.GaunletBetInputData;
import use_case.gaunlet.bet.GaunletBetOutputBoundary;

/**
 * The Gaunlet Guess Interactor.
 */
public class GaunletGuessInteractor implements GaunletGuessInputBoundary {
    private final GaunletGuessOutputBoundary userPresenter;

    public GaunletGuessInteractor(GaunletGuessOutputBoundary gaunletGuessOutputBoundary) {
        this.userPresenter = gaunletGuessOutputBoundary;
    }

    @Override
    public void execute(GaunletGuessInputData gaunletGuessInputData) {
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
    public void switchToLoginView() {
        userPresenter.switchToLoginView();
    }
}
