package interface_adapter.gaunlet.guess;

import use_case.gaunlet.guess.GaunletGuessInputBoundary;

/**
 * Controller for the Gaunlet Guess Use Case.
 */
public class GaunletGuessController {

    private final GaunletGuessInputBoundary userGaunletGuessUseCaseInteractor;

    public GaunletGuessController(GaunletGuessInputBoundary userGaunletGuessUseCaseInteractor) {
        this.userGaunletGuessUseCaseInteractor = userGaunletGuessUseCaseInteractor;
    }

    /**
     * Executes the "switch to LoginView" Use Case.
     */
    public void switchToLoginView() {
        userGaunletGuessUseCaseInteractor.switchToLoginView();
    }
}
