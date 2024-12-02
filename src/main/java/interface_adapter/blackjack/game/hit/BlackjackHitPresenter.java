package interface_adapter.blackjack.game.hit;

import interface_adapter.ViewManagerModel;
import interface_adapter.blackjack.game.BlackjackGameState;
import interface_adapter.blackjack.game.BlackjackGameViewModel;
import interface_adapter.gamemenu.GameMenuViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.blackjack.hit.BlackjackHitOutputBoundary;
import use_case.blackjack.hit.BlackjackHitOutputData;

/**
 * Presenter for the Blackjack Hit Use Case.
 */
public class BlackjackHitPresenter implements BlackjackHitOutputBoundary {
    private final SignupViewModel signupViewModel;
    private final BlackjackGameViewModel blackjackGameViewModel;
    private final GameMenuViewModel gameMenuViewModel;
    private final ViewManagerModel viewManagerModel;

    public BlackjackHitPresenter(SignupViewModel signupViewModel,
                                 BlackjackGameViewModel blackjackGameViewModel,
                                 GameMenuViewModel gameMenuViewModel,
                                 ViewManagerModel viewManagerModel) {
        this.signupViewModel = signupViewModel;
        this.blackjackGameViewModel = blackjackGameViewModel;
        this.gameMenuViewModel = gameMenuViewModel;
        this.viewManagerModel = viewManagerModel;

    }

    @Override
    public void prepareSuccessView(BlackjackHitOutputData outputData) {
        final BlackjackGameState blackjackGameState = blackjackGameViewModel.getState();
        blackjackGameState.addPlayerCard(outputData.getCardImage());
        blackjackGameState.setTurnState(outputData.getTurnState());
        blackjackGameState.setPlayerScore(String.valueOf(outputData.getNewHandScore()));
        blackjackGameViewModel.setState(blackjackGameState);
        blackjackGameViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final SignupState signupState = signupViewModel.getState();
        // signupState.setUsernameError(errorMessage);
        signupViewModel.firePropertyChanged();
    }

    @Override
    public void switchToGameMenuView() {
        viewManagerModel.setState(gameMenuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
