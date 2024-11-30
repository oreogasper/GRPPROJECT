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
    private final List<Image> initialPlayerCards;
    private final List<Image> initialDealerCards;
    private final int initialScore;

    public BlackjackBetOutputData(int bet, boolean useCaseFailed,
                                  boolean initializeGameUseCase, List<Image> initialPlayerCards, List<Image> dealerCards,
                                  int initialScore) {
        this.bet = bet;
        this.useCaseFailed = useCaseFailed;
        this.initializeGameUseCase = initializeGameUseCase;
        this.initialPlayerCards = initialPlayerCards;
        this.initialDealerCards = dealerCards;
        this.initialScore = initialScore;
    }

    public int getBet() {
        return bet;
    }

    public boolean isInitializeGameUseCase() {return initializeGameUseCase;}

    public List<Image> getInitialPlayerCards() {return initialPlayerCards;}

    public List<Image> getInitialDealerCards() {return initialDealerCards;}

    public int getInitialScore() {return initialScore;}

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

}
