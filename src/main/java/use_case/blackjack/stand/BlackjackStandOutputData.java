package use_case.blackjack.stand;

import entity.AbstractCard;

import java.util.List;

/**
 * Output Data for the Blackjack Stand Use Case.
 */
public class BlackjackStandOutputData {
    private final String turnState;
    private final boolean useCaseFailed;
    private final List<Integer> dealerScores;
    private final List<AbstractCard> dealerCards;

    public BlackjackStandOutputData(String turnState,
                                    boolean useCaseFailed,
                                    List<Integer> dealerScores, List<AbstractCard> dealerCards) {
        this.turnState = turnState;
        this.useCaseFailed = useCaseFailed;
        this.dealerScores = dealerScores;
        this.dealerCards = dealerCards;
    }

    public String getTurnState() {
        return turnState;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public List<Integer> getDealerScores() {return dealerScores;}

    public List<AbstractCard> getDealerCards() {return dealerCards;}

}
