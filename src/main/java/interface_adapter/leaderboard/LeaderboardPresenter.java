package interface_adapter.leaderboard;

import interface_adapter.ViewManagerModel;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.statistics.StatisticsState;
import interface_adapter.statistics.StatisticsViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.leaderboard.LeaderboardOutputBoundary;

/**
 * The Presenter for the Leaderboard Use Case.
 */
public class LeaderboardPresenter implements LeaderboardOutputBoundary {

    private final StatisticsViewModel statisticsViewModel;
    private final LeaderboardViewModel leaderboardViewModel;
    private final ViewManagerModel viewManagerModel;

    public LeaderboardPresenter(ViewManagerModel viewManagerModel,
                               StatisticsViewModel statisticsViewModel,
                               LeaderboardViewModel leaderboardViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.statisticsViewModel = statisticsViewModel;
        this.leaderboardViewModel = leaderboardViewModel;
    }

    @Override
    public void prepareSuccessView() {
    }

    @Override
    public void prepareFailView(String error) {
    }

    @Override
    public void switchToStatisticsView() {
        final StatisticsState statisticsState = statisticsViewModel.getState();
        statisticsState.setUser(leaderboardViewModel.getState().getUser());
        this.statisticsViewModel.setState(statisticsState);
        this.statisticsViewModel.firePropertyChanged();

        leaderboardViewModel.firePropertyChanged("reset");

        viewManagerModel.setState(statisticsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
