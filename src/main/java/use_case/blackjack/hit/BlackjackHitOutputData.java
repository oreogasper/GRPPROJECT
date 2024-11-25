package use_case.blackjack.hit;

import entity.CardAbs;

/**
 * Output Data for the Blackjack Get Card Use Case.
 */
public class BlackjackHitOutputData {

    private final CardAbs card;
    private final boolean useCaseFailed;

    public BlackjackHitOutputData(CardAbs card, boolean useCaseFailed) {
        this.card = card;
        this.useCaseFailed = useCaseFailed;
    }

    public CardAbs getCard() {
        return this.card;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

}
