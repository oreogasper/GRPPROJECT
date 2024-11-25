package interface_adapter.leaderboard;

import use_case.leaderboard.LeaderboardInputBoundary;

/**
 * Controller for the Leaderboard Use Case.
 */
public class LeaderboardController {

    private final LeaderboardInputBoundary userLeaderboardUseCareInteractor;

    public LeaderboardController(LeaderboardInputBoundary userLeaderboardUseCareInteractor) {
        this.userLeaderboardUseCareInteractor = userLeaderboardUseCareInteractor;
    }

    /**
     * Executes the "switch to StatisticsView" Use Case.
     */
    public void switchToStatisticsView() {
        userLeaderboardUseCareInteractor.switchToStatisticsView();
    }
}
