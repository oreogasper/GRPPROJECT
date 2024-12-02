package use_case.gamemenu;

/**
 * The Game Menu Interactor.
 */
public class GameMenuInteractor implements GameMenuInputBoundary {
    private final GameMenuOutputBoundary userPresenter;

    public GameMenuInteractor(GameMenuOutputBoundary gameMenuOutputBoundary) {
        this.userPresenter = gameMenuOutputBoundary;
    }

    @Override
    public void switchToMenuView() {
        userPresenter.switchToMenuView();
    }

    @Override
    public void switchToLoginView() {
        userPresenter.switchToLoginView();
    }

    @Override
    public void switchToGaunletView() {
        userPresenter.switchToGaunletView();
    }

    @Override
    public void switchToBlackjackView() {
        userPresenter.switchToBlackjackView();
    }

    public void switchToOverUnderBetView() {
        userPresenter.switchToOverUnderBetView();
        System.out.println("GameMenuInteractor");

    }
}

