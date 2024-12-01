package use_case.blackjack.hit;

/**
 * Input Data for the Blackjack Hit Use Case.
 */
public class BlackjackHitInputData {
    private final boolean dealerHitUseCase;

    public BlackjackHitInputData(boolean dealerHitUseCase) {
        this.dealerHitUseCase = dealerHitUseCase;

    }

    public boolean isDealerHitUseCase() {
        return dealerHitUseCase;
    }
}
