package use_case.blackjack.game;


import java.awt.*;
import java.util.List;

/**
 * Output Data for the Blackjack Game Use Case.
 */
public class BlackjackGameOutputData {
    private String useCase;
    private int amountWon;
    private final List<Image> initialPlayerCards;
    private final List<Image> initialDealerCards;
    private final int initialPlayerScore;
    private final int initialDealerScore;
    private final int hiddenDealerScore;

    public BlackjackGameOutputData(String useCase, int amountWon, List<Image> initialPlayerCards, List<Image> initialDealerCards, int initialPlayerScore, int initialDealerScore, int hiddenDealerScore) {
        this.useCase = useCase;
        this.amountWon = amountWon;

        this.initialPlayerCards = initialPlayerCards;
        this.initialDealerCards = initialDealerCards;
        this.initialPlayerScore = initialPlayerScore;
        this.initialDealerScore = initialDealerScore;
        this.hiddenDealerScore = hiddenDealerScore;
    }


    public int getAmountWon() {
        return amountWon;
    }


    public String getUseCase() {
        return useCase;
    }

    public List<Image> getInitialPlayerCards() {
        return initialPlayerCards;
    }

    public List<Image> getInitialDealerCards() {
        return initialDealerCards;
    }

    public int getInitialPlayerScore() {
        return initialPlayerScore;
    }

    public int getInitialDealerScore() {
        return initialDealerScore;
    }

    public int getHiddenDealerScore() {
        return hiddenDealerScore;
    }
}
