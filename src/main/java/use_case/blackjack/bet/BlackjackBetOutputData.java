package use_case.blackjack.bet;


import java.awt.*;
import java.util.List;

/**
 * Output Data for the Blackjack Bet Use Case.
 */
public class BlackjackBetOutputData {

    private final int bet;
    private final boolean useCaseFailed;
    private final boolean initializeGameUseCase;
    private final List<Image> initialCards;
    private final int initialScore;

    public BlackjackBetOutputData(int bet, boolean useCaseFailed,
                                  boolean initializeGameUseCase, List<Image> initialCards, int initialScore) {
        this.bet = bet;
        this.useCaseFailed = useCaseFailed;
        this.initializeGameUseCase = initializeGameUseCase;
        this.initialCards = initialCards;
        this.initialScore = initialScore;
    }

    public int getBet() {
        return bet;
    }

    public boolean isInitializeGameUseCase() {return initializeGameUseCase;}

    public List<Image> getInitialCards() {return initialCards;}

    public int getInitialScore() {return initialScore;}

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

}
