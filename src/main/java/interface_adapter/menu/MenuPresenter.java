package interface_adapter.menu;

import interface_adapter.ViewManagerModel;
import interface_adapter.gamemenu.GameMenuViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.statistics.StatisticsViewModel;
import use_case.menu.MenuOutputBoundary;
import view.GameMenuView;

/**
 * The Presenter for the Welcome Use Case.
 */
public class MenuPresenter implements MenuOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;
    private final StatisticsViewModel statisticsViewModel;
    private final GameMenuViewModel gameMenuViewModel;

    public MenuPresenter(ViewManagerModel viewManagerModel,
                         LoginViewModel loginViewModel,
                         StatisticsViewModel statisticsViewModel, GameMenuViewModel gameMenuViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.statisticsViewModel = statisticsViewModel;
        this.gameMenuViewModel = gameMenuViewModel;
    }

    @Override
    public void prepareSuccessView() {
    }

    @Override
    public void prepareFailView(String error) {
    }

    @Override
    public void switchToLoginView() {
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToGameMenuView() {
        viewManagerModel.setState(gameMenuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToStatisticsView() {
        viewManagerModel.setState(statisticsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
