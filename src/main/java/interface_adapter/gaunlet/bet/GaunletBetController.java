package interface_adapter.gaunlet.bet;

import use_case.gaunlet.bet.GaunletBetInputBoundary;

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
    public void execute(String bet) {
        // need to finish this
    }

    // /**
    // * Executes the "switch to Gaunlet guess Use Case.
    // */
    // public void switchToGauntletGuessView() {
    // userGaunletBetUseCaseInteractor.switchToGauntletGuessView();
    // }

    /**
     * Executes the "switch to menu" Use Case.
     */
    public void switchToGaunletGuessView() {
        userGaunletBetUseCaseInteractor.switchToGaunletGuessView();
    }

    public void switchToGameMenuView() {
        userGaunletBetUseCaseInteractor.switchToGameMenuView();
    }

}
