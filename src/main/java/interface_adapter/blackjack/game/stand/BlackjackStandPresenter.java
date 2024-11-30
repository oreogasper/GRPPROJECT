package interface_adapter.blackjack.game.stand;

import interface_adapter.ViewManagerModel;
import interface_adapter.blackjack.bet.BlackjackBetViewModel;
import interface_adapter.blackjack.game.BlackjackGameState;
import interface_adapter.blackjack.game.BlackjackGameViewModel;
import interface_adapter.gamemenu.GameMenuViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.blackjack.stand.BlackjackStandOutputBoundary;
import use_case.blackjack.stand.BlackjackStandOutputData;


public class BlackjackStandPresenter implements BlackjackStandOutputBoundary {
    private final SignupViewModel signupViewModel;
    private final BlackjackGameViewModel blackjackGameViewModel;
    private final GameMenuViewModel gameMenuViewModel;
    private final ViewManagerModel viewManagerModel;
    private final BlackjackBetViewModel blackjackBetViewModel;

    public BlackjackStandPresenter(SignupViewModel signupViewModel,
                                   BlackjackGameViewModel blackjackGameViewModel,
                                   GameMenuViewModel gameMenuViewModel,
                                   ViewManagerModel viewManagerModel,
                                   BlackjackBetViewModel blackjackBetViewModel) {
        this.signupViewModel = signupViewModel;
        this.blackjackGameViewModel = blackjackGameViewModel;
        this.gameMenuViewModel = gameMenuViewModel;
        this.viewManagerModel = viewManagerModel;
        this.blackjackBetViewModel = blackjackBetViewModel;
    }

    @Override
    public void prepareSuccessView(BlackjackStandOutputData outputData) {
        final BlackjackGameState gameState = blackjackGameViewModel.getState();
        gameState.setDealerScore(String.valueOf(outputData.getDealerScore()));
        gameState.setTurnState(outputData.getTurnState());
        blackjackGameViewModel.setState(gameState);
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

    @Override
    public void switchToBetView() {
        viewManagerModel.setState(blackjackBetViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

        final BlackjackGameState gameState = new BlackjackGameState();
        blackjackGameViewModel.setState(gameState);
        blackjackGameViewModel.firePropertyChanged();
    }
}
