package interface_adapter.und_ovr.bet;

import interface_adapter.ViewManagerModel;
import interface_adapter.und_ovr.play.OverUnderPlayState;
import interface_adapter.gamemenu.GameMenuViewModel;
import interface_adapter.und_ovr.play.OverUnderPlayState;
import interface_adapter.und_ovr.play.OverUnderPlayViewModel;
import use_case.Over_Under.bet.OverUnderBetOutputData;
import use_case.Over_Under.bet.OverUnderBetOutputBoundary;

/**
 * Presenter for the Over/Under game.
 */
public class OverUnderBetPresenter implements OverUnderBetOutputBoundary {

    private final GameMenuViewModel gameMenuViewModel;
    private final OverUnderBetViewModel overUnderBetViewModel;
    private final OverUnderPlayViewModel overUnderPlayViewModel;
    private final ViewManagerModel viewManagerModel;

    public OverUnderBetPresenter(ViewManagerModel viewManagerModel,
                                 GameMenuViewModel gameMenuViewModel,
                                 OverUnderBetViewModel overUnderBetViewModel,
                                 OverUnderPlayViewModel overUnderPlayViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.gameMenuViewModel = gameMenuViewModel;
        this.overUnderBetViewModel = overUnderBetViewModel;
        this.overUnderPlayViewModel = overUnderPlayViewModel;
    }

    @Override
    public void prepareSuccessView(OverUnderBetOutputData response) {

        final OverUnderBetState overUnderBetState = overUnderBetViewModel.getState();
        overUnderBetState.setBet(response.getBet());
        this.overUnderBetViewModel.setState(overUnderBetState);
        overUnderBetViewModel.firePropertyChanged();

        final OverUnderPlayState overUnderPlayState = overUnderPlayViewModel.getState();
        overUnderPlayState.setUser(overUnderBetViewModel.getState().getUser());
        this.overUnderPlayViewModel.setState(overUnderPlayState);
        this.overUnderPlayViewModel.firePropertyChanged();

        this.viewManagerModel.setState(overUnderPlayViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final OverUnderBetState betState = overUnderBetViewModel.getState();
        betState.setBetError(error);
        betState.setBet(0);
        this.overUnderBetViewModel.setState(betState);
        overUnderBetViewModel.firePropertyChanged();
    }

    @Override
    public void switchToOverUnderPlayView() {
        viewManagerModel.setState(overUnderPlayViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToGameMenuView() {
        viewManagerModel.setState(gameMenuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void setBet() {
        final int betAmt = this.overUnderBetViewModel.getState().getBet();
        this.overUnderBetViewModel.getState().getUser().setBet(betAmt);
        this.overUnderBetViewModel.firePropertyChanged();
    }
}
