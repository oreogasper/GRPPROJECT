package interface_adapter.gaunlet.guess;

import javax.swing.JOptionPane;

import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.gamemenu.GameMenuState;
import interface_adapter.gamemenu.GameMenuViewModel;
import interface_adapter.menu.MenuState;
import interface_adapter.menu.MenuViewModel;
import use_case.gaunlet.guess.GaunletGuessOutputBoundary;
import use_case.gaunlet.guess.GaunletGuessOutputData;

/**
 * The Presenter for the Gaunlet Guess Use Case.
 */
public class GaunletGuessPresenter implements GaunletGuessOutputBoundary {

    private final MenuViewModel menuViewModel;
    private final GaunletGuessViewModel gaunletGuessViewModel;
    private final GameMenuViewModel gameMenuViewModel;
    private final ViewManagerModel viewManagerModel;

    public GaunletGuessPresenter(ViewManagerModel viewManagerModel,
                                 MenuViewModel menuViewModel,
                                 GaunletGuessViewModel gaunletGuessViewModel,
                                 GameMenuViewModel gameMenuViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.menuViewModel = menuViewModel;
        this.gaunletGuessViewModel = gaunletGuessViewModel;
        this.gameMenuViewModel = gameMenuViewModel;
    }

    @Override
    public void prepareSuccessView(GaunletGuessOutputData response) {

        // On success, switch to the gauntlet guess view when implemented
        final GaunletGuessState gaunletGuessState = gaunletGuessViewModel.getState();
        final GameMenuState gameMenuState = gameMenuViewModel.getState();

        final boolean gameOutcome = response.isWon();
        final User user = gaunletGuessState.getUser();
        final int rateBonus = 36;
        final int newBal = (user.getBet() + user.getBet() * rateBonus);

        // Prepare to show user game outcome and show user updated stats
        if (gameOutcome) {
            user.updateBalance((user.getBalance() + user.getBet() + user.getBet()) * rateBonus);
            user.wonGame();
            JOptionPane.showMessageDialog(null,
                    "Congratulations! You won the Gauntlet game! Your Reward =" + newBal);
        }
        else {
            user.updateBalance(-user.getBet());
            user.lostGame();
            JOptionPane.showMessageDialog(null,
                    "Sorry, you lost the Gauntlet game. Better luck next time! "
                            + "Your balance =" + user.getBalance());
        }

        gaunletGuessState.setCoinGuess("");
        gaunletGuessState.setDiceGuess("");
        gaunletGuessState.setRpsGuess("");

        this.gaunletGuessViewModel.setState(gaunletGuessState);
        gaunletGuessViewModel.firePropertyChanged();

        final MenuState menuState = menuViewModel.getState();
        menuState.setUser(gaunletGuessState.getUser());
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
        final GaunletGuessState gaunletGuessState = gaunletGuessViewModel.getState();
        gaunletGuessState.setCoinGuessError(error);
        gaunletGuessViewModel.firePropertyChanged();
    }

    @Override
    public void switchToLoginView() {
        viewManagerModel.setState(gameMenuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
