package interface_adapter.blackjack.game;


import interface_adapter.ViewManagerModel;
import interface_adapter.gamemenu.GameMenuViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.blackjack.bet.BlackjackBetOutputData;
import use_case.blackjack.game.BlackjackGameOutputBoundary;

/**
 * The Presenter for the Blackjack Game Use Case.
 */
public class BlackjackGamePresenter implements BlackjackGameOutputBoundary {
    private final SignupViewModel signupViewModel;
    private final BlackjackGameViewModel blackjackGameViewModel;
    private final GameMenuViewModel gameMenuViewModel;
    private final ViewManagerModel viewManagerModel;

    public BlackjackGamePresenter(SignupViewModel signupViewModel,
                                  BlackjackGameViewModel blackjackGameViewModel,
                                  GameMenuViewModel gameMenuViewModel,
                                  ViewManagerModel viewManagerModel) {
        this.signupViewModel = signupViewModel;
        this.blackjackGameViewModel  = blackjackGameViewModel;
        this.gameMenuViewModel = gameMenuViewModel;
        this.viewManagerModel = viewManagerModel;

    }

    @Override
    public void prepareSuccessView(BlackjackBetOutputData outputData) {
        //TODO
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final SignupState signupState = signupViewModel.getState();
        signupState.setError(errorMessage);
        signupViewModel.firePropertyChanged();
    }

    @Override
    public void switchToGameMenuView() {
        viewManagerModel.setState(gameMenuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
