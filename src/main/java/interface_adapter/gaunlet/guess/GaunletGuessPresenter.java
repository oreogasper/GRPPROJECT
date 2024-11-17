package interface_adapter.gaunlet.guess;

import interface_adapter.ViewManagerModel;
import interface_adapter.gamemenu.GameMenuViewModel;
import interface_adapter.gaunlet.guess.GaunletGuessState;
import interface_adapter.gaunlet.bet.GaunletBetViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.gaunlet.bet.GaunletBetOutputBoundary;
import use_case.gaunlet.bet.GaunletBetOutputData;
import use_case.gaunlet.guess.GaunletGuessOutputBoundary;

/**
 * The Presenter for the Gaunlet Guess Use Case.
 */
public class GaunletGuessPresenter implements GaunletGuessOutputBoundary {

    private final SignupViewModel signupViewModel;
    private final GaunletGuessViewModel gaunletGuessViewModel;
    private final GameMenuViewModel gameMenuViewModel;
    private final ViewManagerModel viewManagerModel;

    public GaunletGuessPresenter(ViewManagerModel viewManagerModel,
                                 SignupViewModel signupViewModel,
                                 GaunletGuessViewModel gaunletGuessViewModel,
                                 GameMenuViewModel gameMenuViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.gaunletGuessViewModel = gaunletGuessViewModel;
        this.gameMenuViewModel = gameMenuViewModel;
    }

    @Override
    public void prepareSuccessView(GaunletBetOutputData response) {

        // On success, switch to the gaunlet guess view when implemented
        final GaunletGuessState gaunletGuessState = gaunletGuessViewModel.getState();
        // gaunletGuessState.setBet(response.getBet());
        this.gaunletGuessViewModel.setState(gaunletGuessState);
        gaunletGuessViewModel.firePropertyChanged();

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
    public void switchToLoginView() {
        viewManagerModel.setState(gaunletGuessViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
