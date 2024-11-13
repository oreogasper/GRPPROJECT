package use_case.statistics;

/**
 * The Statistics Interactor.
 */
public class StatisticsInteractor implements StatisticsInputBoundary {
    private final StatisticsOutputBoundary userPresenter;

    public StatisticsInteractor(StatisticsOutputBoundary statisticsOutputBoundary) {
        this.userPresenter = statisticsOutputBoundary;
    }

    // TODO: change to leaderboard view when implemented
    @Override
    public void switchToWelcomeView() {
        userPresenter.switchToWelcomeView();
    }
}

