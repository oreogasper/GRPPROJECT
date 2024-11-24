package use_case.statistics;

import use_case.signup.SignupOutputData;

/**
 * The output boundary for the Statistics Use Case.
 */
public interface StatisticsOutputBoundary {
    /**
     * Prepares the success view for the Statistics Use Case.
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
    // TODO: change to leaderboard view when implemented
    void switchToWelcomeView();

    void switchToMenuView();
}
