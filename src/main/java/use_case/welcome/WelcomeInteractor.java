package use_case.welcome;

/**
 * The Welcome Interactor.
 */
public class WelcomeInteractor implements WelcomeInputBoundary {
    private final WelcomeOutputBoundary userPresenter;

    public WelcomeInteractor(WelcomeOutputBoundary welcomeOutputBoundary) {
        this.userPresenter = welcomeOutputBoundary;
    }

    @Override
    public void switchToSignupView() {
        userPresenter.switchToSignupView();
    }

    @Override
    public void switchToLoginView() {
        userPresenter.switchToLoginView();
    }
}

