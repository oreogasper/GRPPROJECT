package interface_adapter.und_ovr.play;

import use_case.Over_Under.play.OverUnderPlayInputBoundary;
import use_case.Over_Under.play.OverUnderPlayInputData;

/**
 * Controller for the OverUnder Game Use Case.
 */
public class OverUnderPlayController {
    private final OverUnderPlayInputBoundary overUnderGameUseCaseInteractor;

    public OverUnderPlayController(OverUnderPlayInputBoundary overUnderPlayInputBoundary) {
        this.overUnderGameUseCaseInteractor = overUnderPlayInputBoundary;
    }

    /**
     * Executes the Signup Use Case.
     * @param username the username of the user
     * @param isHigher the users guess of higher/lower
     */
    public void execute(String username, boolean isHigher) {
        final OverUnderPlayInputData overUnderPlayInputData = new OverUnderPlayInputData(username, isHigher);
        this.overUnderGameUseCaseInteractor.execute(overUnderPlayInputData);
    }

    /**
     * Executes the "switch to LoginView" Use Case.
     */
    public void switchToGameMenuView() {
        this.overUnderGameUseCaseInteractor.switchToGameMenuView();
    }
}
