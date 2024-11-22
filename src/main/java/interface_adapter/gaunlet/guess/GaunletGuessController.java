package interface_adapter.gaunlet.guess;

import use_case.gaunlet.guess.GaunletGuessInputBoundary;
import use_case.gaunlet.guess.GaunletGuessInputData;
import use_case.signup.SignupInputData;

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
     * @param coinGuess the username to sign up
     * @param diceGuess the password
     * @param rpsGuess the password repeated
     */
    public void execute(String coinGuess, String diceGuess, String rpsGuess) {
        final GaunletGuessInputData gaunletGuessInputData = new GaunletGuessInputData(
                coinGuess, diceGuess, rpsGuess);

        userGaunletGuessUseCaseInteractor.execute(gaunletGuessInputData);
    }
    /**
     * Executes the "switch to LoginView" Use Case.
     */
    public void switchToLoginView() {
        userGaunletGuessUseCaseInteractor.switchToLoginView();
    }
}
