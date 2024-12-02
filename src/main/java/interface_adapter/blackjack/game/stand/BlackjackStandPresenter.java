package interface_adapter.blackjack.game.stand;

import entity.AbstractCard;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.blackjack.bet.BlackjackBetViewModel;
import interface_adapter.blackjack.game.BlackjackGameState;
import interface_adapter.blackjack.game.BlackjackGameViewModel;
import interface_adapter.gamemenu.GameMenuState;
import interface_adapter.gamemenu.GameMenuViewModel;
import interface_adapter.menu.MenuState;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.blackjack.stand.BlackjackStandOutputBoundary;
import use_case.blackjack.stand.BlackjackStandOutputData;
import view.MenuView;

import java.util.AbstractList;
import java.util.List;


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
        gameState.setHideDealerCard(false);
        gameState.setTurnState(outputData.getTurnState());
        blackjackGameViewModel.setState(gameState);
        blackjackGameViewModel.firePropertyChanged();


        final List<Integer> dealerScores = outputData.getDealerScores();
        final List<AbstractCard> dealerCards = outputData.getDealerCards();

        for (int i = 0; i < dealerCards.size(); i++) {
            final AbstractCard card = dealerCards.get(i);
            final Integer score = dealerScores.get(i);
            gameState.setDealerScore(String.valueOf(score));
            gameState.addDealerCard(card.getImage());
            blackjackGameViewModel.setState(gameState);
            blackjackGameViewModel.firePropertyChanged();

        }

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

    }
}
