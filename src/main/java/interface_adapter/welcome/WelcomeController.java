package interface_adapter.welcome;

import use_case.welcome.WelcomeInputBoundary;

/**
 * The controller for the Welcome Use Case.
 */
public class WelcomeController {

    private final WelcomeInputBoundary userWelcomeUseCaseInteractor;

    public WelcomeController(WelcomeInputBoundary welcomeUseCaseInteractor) {
        this.userWelcomeUseCaseInteractor = welcomeUseCaseInteractor;
    }

    public void switchToSignupView() {
        userWelcomeUseCaseInteractor.switchToSignupView();
    }

    public void switchToLoginView() {
        userWelcomeUseCaseInteractor.switchToLoginView();
    }

}
