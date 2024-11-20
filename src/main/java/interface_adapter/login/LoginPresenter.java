package interface_adapter.login;

import data_access.DBUserDataAccessObject;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.menu.MenuState;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.statistics.StatisticsState;
import interface_adapter.statistics.StatisticsViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final LoginInteractor loginInteractor;
    private final StatisticsViewModel statisticsViewModel;
    private final ViewManagerModel viewManagerModel;
    private final WelcomeViewModel welcomeViewModel;
    private final MenuViewModel menuViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          StatisticsViewModel statisticsViewModel,
                          LoginViewModel loginViewModel,
                          WelcomeViewModel welcomeViewModel, MenuViewModel menuViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.statisticsViewModel = statisticsViewModel;
        this.loginViewModel = loginViewModel;
        this.welcomeViewModel = welcomeViewModel;
        this.menuViewModel = menuViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {

        final LoginInteractor loginInteractor;
        final MenuState menuState = menuViewModel.getState();
        final User user = loginInteractor.get(response.getUsername());
        menuState.setUser(user);
        this.menuViewModel.firePropertyChanged();

        final StatisticsState statisticsState = statisticsViewModel.getState();
        statisticsState.setUsername(response.getUsername());
        this.statisticsViewModel.setState(statisticsState);

        this.statisticsViewModel.firePropertyChanged();

        // On success, switch to the menu in view.
        this.viewManagerModel.setState(menuViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }

    @Override
    public void switchToWelcomeView() {
        viewManagerModel.setState(welcomeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
