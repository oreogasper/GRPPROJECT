package interface_adapter.menu;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.statistics.StatisticsViewModel;
import use_case.menu.MenuOutputBoundary;

/**
 * The Presenter for the Welcome Use Case.
 */
public class MenuPresenter implements MenuOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;
    private final StatisticsViewModel statisticsViewModel;

    public MenuPresenter(ViewManagerModel viewManagerModel,
                         LoginViewModel loginViewModel,
                         StatisticsViewModel statisticsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.statisticsViewModel = statisticsViewModel;
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
    public void switchToStatisticsView() {
        viewManagerModel.setState(statisticsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
