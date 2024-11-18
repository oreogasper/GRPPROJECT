package use_case.blackjack.game;


import java.util.List;

/**
 * Output Data for the Blackjack Game Use Case.
 */
public class BlackjackGameOutputData {

    private List<String> playerCards;
    private List<String> dealerCards;
    private final boolean useCaseFailed;

    public BlackjackGameOutputData(List<String> playerCards, List<String> dealerCards, boolean useCaseFailed) {
        this.playerCards = playerCards;
        this.dealerCards = dealerCards;
        this.useCaseFailed = useCaseFailed;

    }

    public List<String> getPlayerCards() {
        return playerCards;
    }

    public List<String> getDealerCards() {
        return dealerCards;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

}
