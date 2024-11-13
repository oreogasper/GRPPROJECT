package use_case.welcome;

/**
 * Input Boundary for actions which are related to logging in.
 */
public interface WelcomeInputBoundary {

    /**
     * Executes the switch to login view use case.
     */
    void switchToSignupView();

    void switchToLoginView();

}
