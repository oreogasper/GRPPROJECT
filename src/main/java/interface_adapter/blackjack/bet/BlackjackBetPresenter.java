package interface_adapter.blackjack.bet;


import interface_adapter.ViewManagerModel;
import interface_adapter.blackjack.game.BlackjackGameState;
import interface_adapter.gamemenu.GameMenuViewModel;
import interface_adapter.blackjack.game.BlackjackGameViewModel;
import use_case.blackjack.bet.BlackjackBetOutputBoundary;
import use_case.blackjack.bet.BlackjackBetOutputData;

import java.awt.*;
import java.util.List;

/**
 * The Presenter for the Blackjack Bet Use Case.
 */
public class BlackjackBetPresenter implements BlackjackBetOutputBoundary {

    private final GameMenuViewModel gameMenuViewModel;
    private final BlackjackBetViewModel blackjackBetViewModel;
    private final BlackjackGameViewModel blackjackGameViewModel;
    private final ViewManagerModel viewManagerModel;

    public BlackjackBetPresenter(GameMenuViewModel gameMenuViewModel,
                                 BlackjackBetViewModel blackjackBetViewModel,
                                 BlackjackGameViewModel blackjackGameViewModel,
                                 ViewManagerModel viewManagerModel) {

        this.gameMenuViewModel = gameMenuViewModel;
        this.blackjackBetViewModel = blackjackBetViewModel;
        this.blackjackGameViewModel = blackjackGameViewModel;
        this.viewManagerModel = viewManagerModel;

    }

    @Override
    public void prepareSuccessView(BlackjackBetOutputData outputData) {
        final BlackjackBetState blackjackBetState = blackjackBetViewModel.getState();
        blackjackBetState.setBet("0");
        blackjackBetState.setBetError(null);
        blackjackBetViewModel.setState(blackjackBetState);
        blackjackBetViewModel.firePropertyChanged();

        final BlackjackGameState blackjackGameState = blackjackGameViewModel.getState();
        blackjackGameState.setUser(blackjackBetState.getUser());
        blackjackGameState.setBetAmount(String.valueOf(outputData.getBet()));
        blackjackGameViewModel.setState(blackjackGameState);
        blackjackGameViewModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String errorMessage) {
        final BlackjackBetState betState = blackjackBetViewModel.getState();
        betState.setBetError(errorMessage);
        this.blackjackBetViewModel.setState(betState);
        this.blackjackBetViewModel.firePropertyChanged();
    }

    @Override
    public void switchToBlackjackGameView() {
        viewManagerModel.setState(blackjackGameViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToGameMenuView() {
        viewManagerModel.setState(gameMenuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
