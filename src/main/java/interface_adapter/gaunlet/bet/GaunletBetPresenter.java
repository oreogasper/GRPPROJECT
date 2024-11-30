package interface_adapter.gaunlet.bet;

import interface_adapter.ViewManagerModel;
import interface_adapter.gamemenu.GameMenuViewModel;
import interface_adapter.gaunlet.guess.GaunletGuessState;
import interface_adapter.gaunlet.guess.GaunletGuessViewModel;
import use_case.gaunlet.bet.GaunletBetOutputBoundary;
import use_case.gaunlet.bet.GaunletBetOutputData;

/**
 * The Presenter for the Gaunlet Bet Use Case.
 */
public class GaunletBetPresenter implements GaunletBetOutputBoundary {

    private final GameMenuViewModel gameMenuViewModel;
    private final GaunletBetViewModel gaunletBetViewModel;
    private final GaunletGuessViewModel gaunletGuessViewModel;
    private final ViewManagerModel viewManagerModel;

    public GaunletBetPresenter(ViewManagerModel viewManagerModel,
                               GameMenuViewModel gameMenuViewModel,
                               GaunletBetViewModel gaunletBetViewModel,
                               GaunletGuessViewModel gaunletGuessViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.gameMenuViewModel = gameMenuViewModel;
        this.gaunletBetViewModel = gaunletBetViewModel;
        this.gaunletGuessViewModel = gaunletGuessViewModel;
    }

    @Override
    public void prepareSuccessView(GaunletBetOutputData response) {

        // On success, switch to the gaunlet guess view when implemented
        final GaunletBetState gaunletBetState = gaunletBetViewModel.getState();
        // reset bet input field
        gaunletBetState.setBet("0");
        gaunletBetState.setBetError(null);
        this.gaunletBetViewModel.setState(gaunletBetState);
        gaunletBetViewModel.firePropertyChanged();

        // Moves user info to guess view
        final GaunletGuessState gaunletGuessState = gaunletGuessViewModel.getState();
        gaunletGuessState.setUser(gaunletBetViewModel.getState().getUser());
        this.gaunletGuessViewModel.setState(gaunletGuessState);
        this.gaunletGuessViewModel.firePropertyChanged();

        this.viewManagerModel.setState(gaunletGuessViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final GaunletBetState betState = gaunletBetViewModel.getState();
        betState.setBetError(error);
        this.gaunletBetViewModel.setState(betState);
        gaunletBetViewModel.firePropertyChanged();
    }

    @Override
    public void switchToGaunletGuessView() {

        viewManagerModel.setState(gaunletGuessViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToGameMenuView() {
        viewManagerModel.setState(gameMenuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void setUserBet() {
        final int betAmt = Integer.parseInt(this.gaunletBetViewModel.getState().getBet());
        this.gaunletBetViewModel.getState().getUser().setBet(betAmt);
        this.gaunletBetViewModel.firePropertyChanged();
    }
}
