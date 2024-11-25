package interface_adapter.gaunlet.bet;

import use_case.gaunlet.bet.GaunletBetInputBoundary;
import use_case.gaunlet.bet.GaunletBetInputData;

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
     * @param username the username of the user
     * @param bet the amount the user would like to bet in the game
     */
    public void execute(String username, int bet) {
        final GaunletBetInputData gaunletBetInputData = new GaunletBetInputData(
                username, bet);

        userGaunletBetUseCaseInteractor.execute(gaunletBetInputData, bet);
    }

    /**
     * Executes the "switch to gaunlet guess" Use Case.
     */
    public void switchToGaunletGuessView() {
        userGaunletBetUseCaseInteractor.switchToGaunletGuessView();
    }

    /**
     * Executes the "switch to game menu" Use Case.
     */
    public void switchToGameMenuView() {
        userGaunletBetUseCaseInteractor.switchToGameMenuView();
    }

}
