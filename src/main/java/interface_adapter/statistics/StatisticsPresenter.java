package interface_adapter.statistics;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
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

    public StatisticsPresenter(ViewManagerModel viewManagerModel,
                               StatisticsViewModel statisticsViewModel,
                               LoginViewModel loginViewModel,
                               WelcomeViewModel welcomeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.statisticsViewModel = statisticsViewModel;
        this.loginViewModel = loginViewModel;
        this.welcomeViewModel = welcomeViewModel;
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
}
