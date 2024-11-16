package interface_adapter.gaunlet_bet;

import interface_adapter.ViewManagerModel;
import interface_adapter.gamemenu.GameMenuViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.gaunlet_bet.GaunletBetOutputBoundary;
import use_case.gaunlet_bet.GaunletBetOutputData;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

/**
 * The Presenter for the Gaunlet Bet Use Case.
 */
public class GaunletBetPresenter implements GaunletBetOutputBoundary {

    private final SignupViewModel signupViewModel;
    private final GaunletBetViewModel gaunletBetViewModel;
    private final GameMenuViewModel gameMenuViewModel;
    private final ViewManagerModel viewManagerModel;

    public GaunletBetPresenter(ViewManagerModel viewManagerModel,
                               SignupViewModel signupViewModel,
                               GaunletBetViewModel gaunletBetViewModel,
                               GameMenuViewModel gameMenuViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.gaunletBetViewModel = gaunletBetViewModel;
        this.gameMenuViewModel = gameMenuViewModel;
    }

    @Override
    public void prepareSuccessView(GaunletBetOutputData response) {

        // On success, switch to the gaunlet guess view when implemented
        final GaunletBetState gaunletBetState = gaunletBetViewModel.getState();
        gaunletBetState.setBet(response.getBet());
        this.gaunletBetViewModel.setState(gaunletBetState);
        gaunletBetViewModel.firePropertyChanged();

        this.viewManagerModel.setState(gameMenuViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final SignupState signupState = signupViewModel.getState();
        signupState.setUsernameError(error);
        signupViewModel.firePropertyChanged();
    }

    @Override
    public void switchToGameMenuView() {
        viewManagerModel.setState(gameMenuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
