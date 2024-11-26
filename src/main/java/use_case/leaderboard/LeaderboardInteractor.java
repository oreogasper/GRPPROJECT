package use_case.leaderboard;

/**
 * The Leaderboard Interactor.
 */
public class LeaderboardInteractor implements LeaderboardInputBoundary {
    private final LeaderboardOutputBoundary userPresenter;

    public LeaderboardInteractor(LeaderboardOutputBoundary leaderboardOutputBoundary) {
        this.userPresenter = leaderboardOutputBoundary;
    }

    @Override
    public void switchToStatisticsView() {
        userPresenter.switchToStatisticsView();
    }

}

