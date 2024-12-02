package use_case.blackjack.game;

import interface_adapter.blackjack.game.hit.BlackjackHitController;
import interface_adapter.blackjack.game.stand.BlackjackStandController;
import use_case.blackjack.hit.BlackjackHitInputData;

/**
 * The Blackjack Game Use Case Interactor.
 */
public class BlackjackGameInteractor implements BlackjackGameInputBoundary{

    private final BlackjackGameOutputBoundary outputBoundary;
    private final BlackjackGameUserDataAccessInterface dataAccessInterface;
    private final BlackjackHitController hitController;
    private final BlackjackStandController standController;

    public BlackjackGameInteractor(BlackjackGameOutputBoundary outputBoundary,
                                   BlackjackGameUserDataAccessInterface dataAccessInterface,
                                   BlackjackHitController hitController,
                                   BlackjackStandController standController) {
        this.outputBoundary = outputBoundary;
        this.dataAccessInterface = dataAccessInterface;
        this.hitController = hitController;
        this.standController = standController;
    }

    @Override
    public void execute(BlackjackGameInputData blackjackGameInputData) {
        if (blackjackGameInputData.equals("Hit")) {
            hitController.execute();
        } else if (blackjackGameInputData.equals("Stand")) {
            standController.execute();
        } else if (blackjackGameInputData.equals("Start")) {

        } else if (blackjackGameInputData.equals("Stop")) {

        }
    }

    @Override
    public void switchToLoginView() {
        outputBoundary.switchToGameMenuView();
    }
}
