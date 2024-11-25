package interface_adapter.gaunlet.guess;

import use_case.gaunlet.guess.GaunletGuessInputBoundary;
import use_case.gaunlet.guess.GaunletGuessInputData;

/**
 * Controller for the Gaunlet Guess Use Case.
 */
public class GaunletGuessController {
    private final GaunletGuessInputBoundary userGaunletGuessUseCaseInteractor;

    public GaunletGuessController(GaunletGuessInputBoundary userGaunletGuessUseCaseInteractor) {
        this.userGaunletGuessUseCaseInteractor = userGaunletGuessUseCaseInteractor;
    }

    /**
     * Executes the Signup Use Case.
     * @param username the username of the user
     * @param coinGuess the coin flip guess
     * @param diceGuess the roll flip guess
     * @param rpsGuess  the rps guess
     */
    public void execute(String username, String coinGuess, String diceGuess, String rpsGuess) {
        final GaunletGuessInputData gaunletGuessInputData = new GaunletGuessInputData(
                username, coinGuess, diceGuess, rpsGuess
        );

        userGaunletGuessUseCaseInteractor.execute(gaunletGuessInputData);
    }

    /**
     * Executes the "switch to LoginView" Use Case.
     */
    public void switchToLoginView() {
        userGaunletGuessUseCaseInteractor.switchToLoginView();
    }
}
