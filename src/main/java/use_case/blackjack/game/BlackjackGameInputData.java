package use_case.blackjack.game;

import java.util.List;

/**
 * Input Data for the Blackjack Game Use Case.
 */
public class BlackjackGameInputData {
    private List<String> playerCards;
    private List<String> dealerCards;

    public BlackjackGameInputData(List<String> playerCards, List<String> dealerCards) {
        this.playerCards = playerCards;
        this.dealerCards = dealerCards;

    }

    public List<String> getPlayerCards() {
        return playerCards;
    }

    public List<String> getDealerCards() {
        return dealerCards;
    }
}
