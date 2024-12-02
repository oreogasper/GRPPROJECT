package interface_adapter.und_ovr.play;

import javax.swing.JOptionPane;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.gamemenu.GameMenuState;
import interface_adapter.gamemenu.GameMenuViewModel;
import interface_adapter.gaunlet.guess.GaunletGuessState;
import interface_adapter.menu.MenuState;
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

        final OverUnderPlayState overUnderPlayState = overUnderPlayViewModel.getState();
        final GameMenuState gameMenuState = gameMenuViewModel.getState();

        final boolean gameOutcome = outputData.getGuessResult();
        final User user = overUnderPlayState.getUser();
        final int rateBonus = 10;
        final int newBal = (user.getBet() + user.getBet() * rateBonus);


        if (gameOutcome) {
            user.updateBalance((user.getBalance() + user.getBet()) * rateBonus);
            user.wonGame();
            JOptionPane.showMessageDialog(null,
                    "Congratulations! You won the OverUnder game! Your Reward = " + newBal);
        }
        else {
            user.updateBalance(-user.getBet());
            user.lostGame();
            JOptionPane.showMessageDialog(null,
                    "Sorry, you lost the OverUnder game. Better luck next time! "
                            + "Your balance = " + user.getBalance());
        }

        this.overUnderPlayViewModel.setState(overUnderPlayState);
        overUnderPlayViewModel.firePropertyChanged();

        final MenuState menuState = menuViewModel.getState();
        menuState.setUser(overUnderPlayState.getUser());
        this.menuViewModel.setState(menuState);
        this.menuViewModel.firePropertyChanged();

        // Updates game menu on switch
        this.gameMenuViewModel.setState(gameMenuState);
        gameMenuViewModel.firePropertyChanged();
        this.viewManagerModel.setState(gameMenuViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
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
}
