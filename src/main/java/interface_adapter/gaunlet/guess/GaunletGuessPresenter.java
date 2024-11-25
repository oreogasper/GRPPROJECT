package interface_adapter.gaunlet.guess;

import javax.swing.JOptionPane;

import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.gamemenu.GameMenuState;
import interface_adapter.gamemenu.GameMenuViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.gaunlet.guess.GaunletGuessOutputBoundary;
import use_case.gaunlet.guess.GaunletGuessOutputData;

/**
 * The Presenter for the Gaunlet Guess Use Case.
 */
public class GaunletGuessPresenter implements GaunletGuessOutputBoundary {
    private static final int RATE_BONUS = 36;
    private final GaunletGuessViewModel gaunletGuessViewModel;
    private final GameMenuViewModel gameMenuViewModel;
    private final ViewManagerModel viewManagerModel;

    public GaunletGuessPresenter(ViewManagerModel viewManagerModel,
                                 GaunletGuessViewModel gaunletGuessViewModel,
                                 GameMenuViewModel gameMenuViewModel) {
        this.viewManagerModel = viewManagerModel;
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
        if (gameOutcome) {
            user.wonGame();
            JOptionPane.showMessageDialog(null,
                    "Congratulations! You won the Gauntlet game! Reward =" + user.getBet());
        }
        else {
            user.lostGame();
            final int newBalance = user.getBalance() - user.getBet();
            JOptionPane.showMessageDialog(null,
                    "Sorry, you lost the Gauntlet game. Better luck next time! "
                            + "Your balance =" + newBalance);
        }

        gaunletGuessState.setCoinGuess(response.getCoinFlip());
        gaunletGuessState.setDiceGuess(response.getDice());
        gaunletGuessState.setRpsGuess(response.getRps());

        this.gaunletGuessViewModel.setState(gaunletGuessState);
        gaunletGuessViewModel.firePropertyChanged();

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
