package entity;

import java.util.ArrayList;
import java.util.List;

public class BlackjackGame implements Game{
    private final int minBet = 100;
    private final int maxBet = 1000;
    private final String rules = "";
    private final String gameName = "Blackjack";

    private List<AbstractCard> playerCards;
    private List<AbstractCard> dealerCards;
    private String deckId;

    public BlackjackGame() {
        playerCards = new ArrayList<AbstractCard>();
        dealerCards = new ArrayList<AbstractCard>();
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

    private int cardsScore(List<AbstractCard> cards) {
        int score = 0;
        for (AbstractCard card : cards) {
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

    public void addPlayerCard(AbstractCard card) {
        playerCards.add(card);
    }

    public void addDealerCard(AbstractCard card) {
        dealerCards.add(card);
    }

    public boolean isBust(List<AbstractCard> cards) {
        int totalScore = this.cardsScore(cards);
        return totalScore > 21;
    }

    public boolean isWin(List<AbstractCard> cards) {
        int totalScore = this.cardsScore(cards);
        return totalScore == 21;
    }

    public List<AbstractCard> getPlayerCards() {
        return playerCards;
    }

    public List<AbstractCard> getDealerCards() {
        return dealerCards;
    }
}
