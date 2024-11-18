package use_case.statistics;

/**
 * Input Boundary for actions which are related to logging in.
 */
public interface StatisticsInputBoundary {

    /**
     * Executes the switch to LEADERBOARD view use case.
     */
    // TODO: change to leaderboard view when implemented
    void switchToWelcomeView();

    void switchToMenuView();
}
