package use_case.blackjack.bet;


import java.awt.*;
import java.util.List;

/**
 * Output Data for the Blackjack Bet Use Case.
 */
public class BlackjackBetOutputData {

    private final int bet;
    private final boolean useCaseFailed;


    public BlackjackBetOutputData(int bet, boolean useCaseFailed) {
        this.bet = bet;
        this.useCaseFailed = useCaseFailed;
    }

    public int getBet() {
        return bet;
    }


    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }


}
