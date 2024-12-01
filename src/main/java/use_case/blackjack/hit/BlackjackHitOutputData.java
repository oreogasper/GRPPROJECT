package use_case.blackjack.hit;

import java.awt.Image;

/**
 * Output Data for the Blackjack Hit Use Case.
 */
public class BlackjackHitOutputData {
    private final Image cardImage;
    private final String turnState;
    private final boolean useCaseFailed;
    private final int newHandScore;
    private final boolean dealerHitUseCase;

    public BlackjackHitOutputData(Image cardImage, String turnState,
                                  int playerScore, boolean useCaseFailed,
                                  boolean dealerHitUseCase) {
        this.cardImage = cardImage;
        this.turnState = turnState;
        this.newHandScore = playerScore;
        this.useCaseFailed = useCaseFailed;
        this.dealerHitUseCase = dealerHitUseCase;
    }

    public boolean isdealerHitUseCase() {
        return dealerHitUseCase;
    }

    public int getNewHandScore() {
        return newHandScore;
    }

    public String getTurnState() {
        return turnState;
    }

    public Image getCardImage() {
        return cardImage;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

}
