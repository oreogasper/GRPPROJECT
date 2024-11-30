package use_case.statistics;

/**
 * The output boundary for the Statistics Use Case.
 */
public interface StatisticsOutputBoundary {
    /**
     * Prepares the success view for the Statistics Use Case.
     */
    // void prepareSuccessView(WelcomeOutputData outputData);
    void prepareSuccessView();

    /**
     * Prepares the failure view for the Login Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Leaderboard View.
     */
    void switchToLeaderboardView();

    /**
     * Switches to the Menu View.
     */
    void switchToMenuView();
}
