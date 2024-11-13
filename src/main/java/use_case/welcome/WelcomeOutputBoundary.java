package use_case.welcome;

/**
 * The output boundary for the Login Use Case.
 */
public interface WelcomeOutputBoundary {
    /**
     * Prepares the success view for the Login Use Case.
     * // @param outputData the output data
     */
    // void prepareSuccessView(WelcomeOutputData outputData);
    void prepareSuccessView();

    /**
     * Prepares the failure view for the Login Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Login View.
     */
    void switchToSignupView();

    void switchToLoginView();
}
