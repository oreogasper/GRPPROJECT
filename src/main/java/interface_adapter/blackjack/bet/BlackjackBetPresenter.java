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
    public void prepareSuccessView(BlackjackBetOutputData response) {
        final BlackjackBetState blackjackBetState = blackjackBetViewModel.getState();
        blackjackBetState.setBet(response.getBet());
        this.blackjackBetViewModel.setState(blackjackBetState);
        this.blackjackBetViewModel.firePropertyChanged();

//        this.viewManagerModel.setState(this.blackjackGameViewModel.getViewName());
//        this.viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String errorMessage) {
        // TODO
    }

    @Override
    public void switchToBlackjackGameView(BlackjackBetOutputData outputData) {
        List<Image> playerCards = outputData.getInitialPlayerCards();
        List<Image> dealerCards = outputData.getInitialDealerCards();

        BlackjackGameState blackjackGameState = blackjackGameViewModel.getState();
        blackjackGameState.addPlayerCard(playerCards.get(0));
        blackjackGameState.addPlayerCard(playerCards.get(1));
        blackjackGameState.setPlayerScore(String.valueOf(outputData.getInitialPlayerScore()));

        blackjackGameState.addDealerCard(dealerCards.get(0));
        blackjackGameState.addDealerCard(dealerCards.get(1));
        blackjackGameState.setDealerScore(String.valueOf(outputData.getInitialDealerScore()));

        blackjackGameViewModel.setState(blackjackGameState);
        blackjackGameViewModel.firePropertyChanged();

        viewManagerModel.setState(blackjackGameViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToGameMenuView() {
        viewManagerModel.setState(gameMenuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
