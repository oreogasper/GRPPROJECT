package interface_adapter.gaunlet.guess;

import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.gamemenu.GameMenuState;
import interface_adapter.gamemenu.GameMenuViewModel;
import interface_adapter.gaunlet.guess.GaunletGuessState;
import interface_adapter.gaunlet.bet.GaunletBetViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.gaunlet.bet.GaunletBetOutputBoundary;
import use_case.gaunlet.bet.GaunletBetOutputData;
import use_case.gaunlet.guess.GaunletGuessOutputBoundary;
import use_case.gaunlet.guess.GaunletGuessOutputData;

import javax.swing.*;

/**
 * The Presenter for the Gaunlet Guess Use Case.
 */
public class GaunletGuessPresenter implements GaunletGuessOutputBoundary {

    private final SignupViewModel signupViewModel;
    private final GaunletGuessViewModel gaunletGuessViewModel;
    private final GameMenuViewModel gameMenuViewModel;
    private final ViewManagerModel viewManagerModel;

    public GaunletGuessPresenter(ViewManagerModel viewManagerModel,
                                 SignupViewModel signupViewModel,
                                 GaunletGuessViewModel gaunletGuessViewModel,
                                 GameMenuViewModel gameMenuViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.gaunletGuessViewModel = gaunletGuessViewModel;
        this.gameMenuViewModel = gameMenuViewModel;
    }

    @Override
    public void prepareSuccessView(GaunletGuessOutputData response) {

        // On success, switch to the gaunlet guess view when implemented
        final GaunletGuessState gaunletGuessState = gaunletGuessViewModel.getState();
        final GameMenuState gameMenuState = gameMenuViewModel.getState();

        final boolean gameOutcome = response.isWon();
        final User user = gaunletGuessState.getUser();
        if (gameOutcome) {
            user.updateBalance(user.getBet() + user.getBet() * 36);
            user.wonGame();
            JOptionPane.showMessageDialog(null,
                    "Congratulations! You won the Gauntlet game! Reward =" + user.getBet());
        }
        else {
            user.updateBalance(-user.getBet());
            user.lostGame();
            JOptionPane.showMessageDialog(null,
                    "Sorry, you lost the Gauntlet game. Better luck next time!");
        }

        gaunletGuessState.setCoinGuess(response.getCoinFlip());
        gaunletGuessState.setDiceGuess(response.getDice());
        gaunletGuessState.setRpsGuess(response.getRps());

        this.gaunletGuessViewModel.setState(gaunletGuessState);
        gaunletGuessViewModel.firePropertyChanged();

        // Updates game menu of switch
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
