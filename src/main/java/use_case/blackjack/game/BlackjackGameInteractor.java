package use_case.blackjack.game;

/**
 * The Blackjack Game Use Case Interactor.
 */
public class BlackjackGameInteractor implements BlackjackGameInputBoundary{

    private final BlackjackGameOutputBoundary outputBoundary;

    public BlackjackGameInteractor(BlackjackGameOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(BlackjackGameInputData blackjackGameInputData) {
        //TODO
    }

    @Override
    public void switchToLoginView() {
        outputBoundary.switchToGameMenuView();
    }
}
