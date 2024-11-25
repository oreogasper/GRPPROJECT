package use_case.statistics;

/**
 * The Statistics Interactor.
 */
public class StatisticsInteractor implements StatisticsInputBoundary {
    private final StatisticsOutputBoundary userPresenter;

    public StatisticsInteractor(StatisticsOutputBoundary statisticsOutputBoundary) {
        this.userPresenter = statisticsOutputBoundary;
    }

    @Override
    public void switchToLeaderboardView() {
        userPresenter.switchToLeaderboardView();
    }

    @Override
    public void switchToMenuView() {
        userPresenter.switchToMenuView();
    }
}

