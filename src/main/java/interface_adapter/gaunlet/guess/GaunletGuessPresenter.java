package interface_adapter.gaunlet.guess;

import interface_adapter.ViewManagerModel;
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
        final boolean gameOutcome = response.isWon();
        if (gameOutcome) {
            // If the user won, show a success message and keep the guesses
            JOptionPane.showMessageDialog(null, "Congratulations! You won the Gauntlet game!");
        }
        else {
            // If the user lost, show a failure message and keep the guesses
            JOptionPane.showMessageDialog(null, "Sorry, you lost the Gauntlet game. Better luck next time!");
        }

        // Update the state with the user's guesses and game result
        gaunletGuessState.setCoinGuess(response.getCoinFlip());
        gaunletGuessState.setDiceGuess(response.getDice());
        gaunletGuessState.setRpsGuess(response.getRps());

        // Update the state in the ViewModel
        this.gaunletGuessViewModel.setState(gaunletGuessState);

        // Notify the ViewModel that the state has been updated
        gaunletGuessViewModel.firePropertyChanged();

        // Switch to the results view to display the outcome (if there's a separate results view)
        this.viewManagerModel.setState(gameMenuViewModel.getViewName()); // Use the appropriate view name for the results screen
        this.viewManagerModel.firePropertyChanged();
        // gaunletGuessState.setCoinGuess("");
        // gaunletGuessState.setDiceGuess("");
        // gaunletGuessState.setRpsGuess("");
        // this.gaunletGuessViewModel.setState(gaunletGuessState);
        // gaunletGuessViewModel.firePropertyChanged();

        // this.viewManagerModel.setState(gameMenuViewModel.getViewName());
        // this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final GaunletGuessState gaunletGuessState = gaunletGuessViewModel.getState();
        gaunletGuessState.setCoinGuessError(error);
        gaunletGuessViewModel.firePropertyChanged();
    }

    @Override
    public void switchToLoginView() {
        viewManagerModel.setState(gaunletGuessViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
