package interface_adapter.statistics;

import use_case.statistics.StatisticsInputBoundary;

/**
 * Controller for the Statistics Use Case.
 */
public class StatisticsController {

    private final StatisticsInputBoundary userStatisticsUseCaseInteractor;

    public StatisticsController(StatisticsInputBoundary userStatisticsUseCaseInteractor) {
        this.userStatisticsUseCaseInteractor = userStatisticsUseCaseInteractor;
    }

    /**
     * Executes the "switch to LeaderboardView" Use Case.
     */
    public void switchToLeaderboardView() {
        userStatisticsUseCaseInteractor.switchToLeaderboardView();
    }

    /**
     * Executes the "switch to MenuView" Use Case.
     */
    public void switchToMenuView() {
        userStatisticsUseCaseInteractor.switchToMenuView();
    }
}
