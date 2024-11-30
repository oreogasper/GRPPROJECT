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
    private final int initialPlayerScore;
    private final int initialDealerScore;


    public BlackjackBetOutputData(int bet, boolean useCaseFailed,
                                  boolean initializeGameUseCase, List<Image> initialPlayerCards, List<Image> dealerCards,
                                  int initialPlayerScore, int initialDealerScore) {
        this.bet = bet;
        this.useCaseFailed = useCaseFailed;
        this.initializeGameUseCase = initializeGameUseCase;
        this.initialPlayerCards = initialPlayerCards;
        this.initialDealerCards = dealerCards;
        this.initialPlayerScore = initialPlayerScore;
        this.initialDealerScore = initialDealerScore;
    }

    public int getBet() {
        return bet;
    }

    public boolean isInitializeGameUseCase() {return initializeGameUseCase;}

    public List<Image> getInitialPlayerCards() {return initialPlayerCards;}

    public List<Image> getInitialDealerCards() {return initialDealerCards;}

    public int getInitialPlayerScore() {return initialPlayerScore;}

    public int getInitialDealerScore() {return initialDealerScore;}

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

}
