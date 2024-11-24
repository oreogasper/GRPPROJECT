package interface_adapter.blackjack.bet;


import interface_adapter.ViewManagerModel;
import interface_adapter.gamemenu.GameMenuViewModel;
import interface_adapter.blackjack.game.BlackjackGameViewModel;
import use_case.blackjack.bet.BlackjackBetOutputBoundary;
import use_case.blackjack.bet.BlackjackBetOutputData;

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
