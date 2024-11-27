package interface_adapter.logout;

import interface_adapter.ViewManagerModel;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.shop.button.ShopButtonViewModel;
import interface_adapter.statistics.StatisticsState;
import interface_adapter.statistics.StatisticsViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.logout.LogoutOutputBoundary;
import use_case.logout.LogoutOutputData;

/**
 * The Presenter for the Logout Use Case.
 */
public class LogoutPresenter implements LogoutOutputBoundary {

    private final StatisticsViewModel statisticsViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;
    private final ShopButtonViewModel shopButtonViewModel;
    private final WelcomeViewModel welcomeViewModel;

    public LogoutPresenter(ViewManagerModel viewManagerModel,
                           StatisticsViewModel statisticsViewModel,
                           LoginViewModel loginViewModel,
                           ShopButtonViewModel shopButtonViewModel,
                           WelcomeViewModel welcomeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.statisticsViewModel = statisticsViewModel;
        this.loginViewModel = loginViewModel;
        this.shopButtonViewModel = shopButtonViewModel;
        this.welcomeViewModel = welcomeViewModel;
    }

    @Override
    public void prepareSuccessView(LogoutOutputData response) {
        // We need to switch to the login view, which should have an empty username and password.
        // We also need to set the username in the StatisticsState to the empty string.
        // We also need to update the shop button tokens made this session to 0

        shopButtonViewModel.firePropertyChanged("logout");

        // 1. get the StatisticsState out of the appropriate View Model,
        // 2. set the username in the state to the empty string
        // 3. set the state in the LoggedInViewModel to the updated state
        // 4. firePropertyChanged so that the View that is listening is updated.
        final StatisticsState statisticsState = statisticsViewModel.getState();
        statisticsState.setUsername("");
        this.statisticsViewModel.setState(statisticsState);
        statisticsViewModel.firePropertyChanged();
        statisticsViewModel.firePropertyChanged("reset");

        final LoginState loginState = loginViewModel.getState();
        loginState.setUsername("");
        loginState.setPassword("");
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        // This code tells the View Manager to switch to the WelcomeView.
        this.viewManagerModel.setState(welcomeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
    }
}
