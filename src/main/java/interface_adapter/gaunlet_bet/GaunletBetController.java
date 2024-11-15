package interface_adapter.gaunlet_bet;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

/**
 * Controller for the Gaunlet Bet Use Case.
 */
public class GaunletBetController {

    private final GaunletBetInputBoundary userGaunletBetUseCaseInteractor;

    public GaunletBetController(GaunletBetInputBoundary userGaunletBetUseCaseInteractor) {
        this.userGaunletBetUseCaseInteractor = userGaunletBetUseCaseInteractor;
    }

    /**
     * Executes the Signup Use Case.
     * @param bet the amount the user would like to bet in the game
     */
    public void execute(int bet) {
        final GaunletBetInputData gaunletBetInputData = new GaunletBetInputData(bet);

        userGaunletBetUseCaseInteractor.execute(gaunletBetInputData);
    }

    /**
     * Executes the "switch to Gaunlet guess Use Case.
     */
    public void switchToGauntletGuessView() {
        userGaunletBetUseCaseInteractor.switchToGauntletGuessView();
    }

    /**
     * Executes the "switch to menu" Use Case.
     */
    public void switchToGameMenuView() {
        userGaunletBetUseCaseInteractor.switchToGameMenuView();
    }

}
