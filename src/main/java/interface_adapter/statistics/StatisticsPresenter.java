package interface_adapter.statistics;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.menu.MenuState;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.statistics.StatisticsOutputBoundary;

/**
 * The Presenter for the Signup Use Case.
 */
public class StatisticsPresenter implements StatisticsOutputBoundary {

    private final StatisticsViewModel statisticsViewModel;
    private final LoginViewModel loginViewModel;
    private final WelcomeViewModel welcomeViewModel;
    private final ViewManagerModel viewManagerModel;
    private final MenuViewModel menuViewModel;

    public StatisticsPresenter(ViewManagerModel viewManagerModel,
                               StatisticsViewModel statisticsViewModel,
                               LoginViewModel loginViewModel,
                               WelcomeViewModel welcomeViewModel,
                               MenuViewModel menuViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.statisticsViewModel = statisticsViewModel;
        this.loginViewModel = loginViewModel;
        this.welcomeViewModel = welcomeViewModel;
        this.menuViewModel = menuViewModel;
    }

    @Override
    public void prepareSuccessView() {
    }

    @Override
    public void prepareFailView(String error) {
    }

    @Override
    public void switchToWelcomeView() {
        viewManagerModel.setState(welcomeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToMenuView() {

        final MenuState menuState = menuViewModel.getState();
        menuState.setUser(statisticsViewModel.getState().getUser());
        this.menuViewModel.setState(menuState);
        this.menuViewModel.firePropertyChanged();

        viewManagerModel.setState(menuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
