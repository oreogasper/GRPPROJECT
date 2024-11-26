package interface_adapter.blackjack.game.hit;

import use_case.blackjack.hit.BlackjackHitOutputBoundary;
import use_case.blackjack.hit.BlackjackHitOutputData;

/**
 * Presenter for the Blackjack Hit Use Case.
 */
public class BlackjackHitPresenter implements BlackjackHitOutputBoundary {

    @Override
    public void prepareSuccessView(BlackjackHitOutputData outputData) {

    }

    @Override
    public void prepareFailView(String errorMessage) {

    }

    @Override
    public void switchToGameMenuView() {

    }
}
