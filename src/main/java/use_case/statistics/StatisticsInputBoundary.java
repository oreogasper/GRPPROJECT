package use_case.statistics;

/**
 * Input Boundary for actions which are related to logging in.
 */
public interface StatisticsInputBoundary {

    /**
     * Executes the switch to LEADERBOARD view use case.
     */
    void switchToLeaderboardView();

    /**
     * Executes the switch to MENU view use case.
     */
    void switchToMenuView();
}
