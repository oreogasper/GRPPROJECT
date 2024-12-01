package interface_adapter.und_ovr.play;

import javax.swing.JOptionPane;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.gamemenu.GameMenuState;
import interface_adapter.gamemenu.GameMenuViewModel;
import interface_adapter.menu.MenuViewModel;
import use_case.Over_Under.play.OverUnderPlayOutputBoundary;
import use_case.Over_Under.play.OverUnderPlayOutputData;


public class OverUnderPlayPresenter implements OverUnderPlayOutputBoundary {

    private final MenuViewModel menuViewModel;
    private final OverUnderPlayViewModel overUnderPlayViewModel;
    private final GameMenuViewModel gameMenuViewModel;
    private final ViewManagerModel viewManagerModel;

    public OverUnderPlayPresenter(ViewManagerModel viewManagerModel,
                                 MenuViewModel menuViewModel,
                                  OverUnderPlayViewModel overUnderPlayViewModel,
                                 GameMenuViewModel gameMenuViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.menuViewModel = menuViewModel;
        this.overUnderPlayViewModel = overUnderPlayViewModel;
        this.gameMenuViewModel = gameMenuViewModel;
    }

    public void prepareSuccessView(OverUnderPlayOutputData outputData) {

    }

    @Override
    public void prepareFailView(String error) {
        final OverUnderPlayState overUnderPlayState = overUnderPlayViewModel.getState();
        overUnderPlayState.setGuessError(error);
        overUnderPlayViewModel.setState(overUnderPlayState);
        overUnderPlayViewModel.firePropertyChanged();
    }

    /**
     * Switches the current view to the game menu view.
     */
    @Override
    public void switchToGameMenuView() {
        viewManagerModel.setState(gameMenuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Switches the current view to the login view.
     */
    public void switchToLoginView() {
        viewManagerModel.setState("LoginView");
        viewManagerModel.firePropertyChanged();
    }
}
