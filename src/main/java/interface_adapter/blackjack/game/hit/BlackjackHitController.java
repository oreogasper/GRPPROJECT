package interface_adapter.blackjack.game.hit;

import use_case.blackjack.hit.BlackjackHitInputBoundary;
import use_case.blackjack.hit.BlackjackHitInputData;

/**
 * Controller for the Blackjack Hit Use Case.
 */
public class BlackjackHitController {
    private final BlackjackHitInputBoundary blackjackHitUseCaseInteractor;

    public BlackjackHitController(BlackjackHitInputBoundary blackjackHitInputBoundary) {
        this.blackjackHitUseCaseInteractor = blackjackHitInputBoundary;
    }

    /**
     * Executes the Blackjack Hit Use Case.
     */
    public void execute(boolean isDealerHitUseCase) {

        final BlackjackHitInputData inputData = new BlackjackHitInputData(isDealerHitUseCase);

        blackjackHitUseCaseInteractor.execute(inputData);
    }

    /**
     * Executes the "switch to GameMenuView" Use Case.
     */
    public void switchToGameMenuView() {
        blackjackHitUseCaseInteractor.switchToGameMenuView();
    }

}
