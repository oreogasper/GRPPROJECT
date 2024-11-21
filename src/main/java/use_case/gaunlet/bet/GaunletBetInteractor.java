package use_case.gaunlet.bet;

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
        String betAmount = gaunletBetInputData.getBet();
        if
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
