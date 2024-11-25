package use_case.blackjack.hit;

import entity.AbstractCard;

/**
 * Output Data for the Blackjack Get Card Use Case.
 */
public class BlackjackHitOutputData {

    private final boolean bust;
    private final boolean win;
    private final boolean useCaseFailed;

    public BlackjackHitOutputData(boolean bust, boolean win, boolean useCaseFailed) {
        this.bust = bust;
        this.win = win;
        this.useCaseFailed = useCaseFailed;
    }

    public boolean isBust() {
        return bust;
    }

    public boolean isWin() {
        return win;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

}
