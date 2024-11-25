package entity;

import java.util.ArrayList;
import java.util.List;

public class BlackjackGame implements Game{
    private final int minBet = 100;
    private final int maxBet = 1000;
    private final String rules = "";
    private final String gameName = "Blackjack";

    private List<Card> playerCards;
    private List<Card> dealerCards;
    private String deckId;

    public BlackjackGame() {
        playerCards = new ArrayList<Card>();
        dealerCards = new ArrayList<Card>();
    }

    @Override
    public int getMinBet() {
        return minBet;
    }

    @Override
    public int getMaxBet() {
        return maxBet;
    }

    @Override
    public String getRules() {
        return rules;
    }

    @Override
    public String getGameType() {
        return gameName;
    }

    private int cardsScore(List<Card> cards) {
        int score = 0;
        for (Card card : cards) {
            score += card.getRank();
        }
        return score;
    }

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }

    public boolean hasDeck() {
        return deckId != null;
    }

    public String getDeckId() {
        return deckId;
    }

    public void addPlayerCard(Card card) {
        playerCards.add(card);
    }

    public void addDealerCard(Card card) {
        dealerCards.add(card);
    }

    public boolean isBust(List<Card> cards) {
        int totalScore = this.cardsScore(cards);
        return totalScore > 21;
    }

    public boolean isWin(List<Card> cards) {
        int totalScore = this.cardsScore(cards);
        return totalScore == 21;
    }

}
