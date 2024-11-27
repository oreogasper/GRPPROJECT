package interface_adapter.statistics;

import interface_adapter.ViewManagerModel;
import interface_adapter.leaderboard.LeaderboardState;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.menu.MenuState;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.statistics.StatisticsOutputBoundary;

/**
 * The Presenter for the Statistics Use Case.
 */
public class StatisticsPresenter implements StatisticsOutputBoundary {

    private final StatisticsViewModel statisticsViewModel;
    private final LeaderboardViewModel leaderboardViewModel;
    private final ViewManagerModel viewManagerModel;
    private final MenuViewModel menuViewModel;

    public StatisticsPresenter(ViewManagerModel viewManagerModel,
                               StatisticsViewModel statisticsViewModel,
                               LeaderboardViewModel leaderboardViewModel,
                               MenuViewModel menuViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.statisticsViewModel = statisticsViewModel;
        this.leaderboardViewModel = leaderboardViewModel;
        this.menuViewModel = menuViewModel;
    }

    @Override
    public void prepareSuccessView() {
    }

    @Override
    public void prepareFailView(String error) {
    }

    @Override
    public void switchToLeaderboardView() {
        final LeaderboardState leaderboardState = leaderboardViewModel.getState();
        leaderboardState.setUser(statisticsViewModel.getState().getUser());
        this.leaderboardViewModel.setState(leaderboardState);
        this.leaderboardViewModel.firePropertyChanged();

        statisticsViewModel.firePropertyChanged("reset");

        viewManagerModel.setState(leaderboardViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToMenuView() {

        final MenuState menuState = menuViewModel.getState();
        menuState.setUser(statisticsViewModel.getState().getUser());
        this.menuViewModel.setState(menuState);
        this.menuViewModel.firePropertyChanged();

        statisticsViewModel.firePropertyChanged("reset");

        viewManagerModel.setState(menuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
