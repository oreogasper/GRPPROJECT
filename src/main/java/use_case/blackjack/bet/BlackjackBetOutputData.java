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


    public BlackjackBetOutputData(int bet, boolean useCaseFailed,
                                  boolean initializeGameUseCase, List<Image> initialPlayerCards, List<Image> dealerCards,
                                  int initialPlayerScore, int initialDealerScore, int hiddenDealerScore) {
        this.bet = bet;
        this.useCaseFailed = useCaseFailed;
        this.initializeGameUseCase = initializeGameUseCase;
    }

    public int getBet() {
        return bet;
    }


    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }


}
