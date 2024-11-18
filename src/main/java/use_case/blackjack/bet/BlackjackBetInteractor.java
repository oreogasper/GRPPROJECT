package use_case.blackjack.bet;

/**
 * The Blackjack Bet Use Case Interactor.
 */
public class BlackjackBetInteractor implements BlackjackBetInputBoundary{

    private final BlackjackBetOutputBoundary outputBoundary;

    public BlackjackBetInteractor(BlackjackBetOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(BlackjackBetInputData blackjackBetInputData) {
        // TODO
    }

    @Override
    public void switchToBlackjackGameView() {
        outputBoundary.switchToBlackjackGameView();
    }

    @Override
    public void switchToGameMenuView() {
        outputBoundary.switchToGameMenuView();
    }

}
