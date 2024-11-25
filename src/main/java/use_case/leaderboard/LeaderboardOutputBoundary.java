package use_case.leaderboard;

/**
 * The output boundary for the Statistics Use Case.
 */
public interface LeaderboardOutputBoundary {
    /**
     * Prepares the success view for the Statistics Use Case.
     * // @param outputData the output data
     */
    void prepareSuccessView();

    /**
     * Prepares the failure view for the Login Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Statistics View.
     */
    void switchToStatisticsView();
}
