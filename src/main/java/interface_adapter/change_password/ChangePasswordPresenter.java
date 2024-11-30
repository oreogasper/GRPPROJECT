package interface_adapter.change_password;

import interface_adapter.signup.SignupState;
import interface_adapter.statistics.StatisticsState;
import interface_adapter.statistics.StatisticsViewModel;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.change_password.ChangePasswordOutputData;

/**
 * The Presenter for the Change Password Use Case.
 */
public class ChangePasswordPresenter implements ChangePasswordOutputBoundary {

    private final StatisticsViewModel statisticsViewModel;

    public ChangePasswordPresenter(StatisticsViewModel statisticsViewModel) {
        this.statisticsViewModel = statisticsViewModel;
    }

    @Override
    public void prepareSuccessView(ChangePasswordOutputData outputData) {
        // Fire the property changed event, but just to let the view know that
        // it can alert the user that their password was changed successfully.
        final StatisticsState statisticsState = statisticsViewModel.getState();
        statisticsState.setError(null);
        statisticsViewModel.firePropertyChanged("password");

    }

    @Override
    public void prepareFailView(String error) {
        final StatisticsState statisticsState = statisticsViewModel.getState();
        statisticsState.setError(error);
        statisticsViewModel.firePropertyChanged("password");
    }
}
