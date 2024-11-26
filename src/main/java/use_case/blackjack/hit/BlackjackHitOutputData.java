package use_case.blackjack.hit;

import java.awt.Image;

/**
 * Output Data for the Blackjack Hit Use Case.
 */
public class BlackjackHitOutputData {
    private final Image cardImage;
    private final boolean bust;
    private final boolean blackjack;
    private final boolean useCaseFailed;

    public BlackjackHitOutputData(Image cardImage, boolean bust, boolean blackjack, boolean useCaseFailed) {
        this.cardImage = cardImage;
        this.bust = bust;
        this.blackjack = blackjack;
        this.useCaseFailed = useCaseFailed;
    }

    public boolean isBust() {
        return bust;
    }

    public boolean isBlackjackn() {
        return blackjack;
    }

    public Image getCardImage() {
        return cardImage;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

}
