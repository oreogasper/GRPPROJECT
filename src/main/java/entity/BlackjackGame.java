package entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.Image;

public class BlackjackGame implements Game{
    private final int minBet = 10;
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
        List<Integer> scores = new ArrayList<Integer>();
        scores.add(0);

        for (AbstractCard card : cards) {
            if (card.getName().equals("A")) {
                List<Integer> heavyScores = new ArrayList<>(scores);
                heavyScores.replaceAll(n -> n + 11);

                scores.replaceAll(n -> n + 1);

                scores.addAll(heavyScores);


            } else if (card.getName().equals("K") || card.getName().equals("Q") || card.getName().equals("J")) {
                scores.replaceAll(n -> n + 10);
            } else {
                scores.replaceAll(n -> n + card.getRank());
            }

        }

        List<Integer> under21Scores = new ArrayList<Integer>();
        for (Integer score : scores) {
            if (score <= 21) {
                under21Scores.add(score);
            }
        }


        if (under21Scores.size() > 0) {
            int bestScore = 0;
            for (Integer score : scores) {
                if (score <= 21 && score > bestScore) {
                    bestScore = score;
                }
            }
            return bestScore;
        } else {
            return Collections.min(scores);
        }


    }

    public int getPlayerScore() {
        return cardsScore(playerCards);
    }

    public int getDealerScore() {
        return cardsScore(dealerCards);
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

    public boolean isBlackjack(List<AbstractCard> cards) {
        int totalScore = this.cardsScore(cards);
        return totalScore == 21;
    }

    public List<AbstractCard> getPlayerCards() {
        return playerCards;
    }

    public List<AbstractCard> getDealerCards() {
        return dealerCards;
    }

    public List<Image> getPlayerCardImages() {
        List<Image> playerCardImages = new ArrayList<>();
        for (AbstractCard card : playerCards) {
            playerCardImages.add(card.getImage());
        }
        return playerCardImages;
    }

    public List<Image> getDealerCardImages() {
        List<Image> dealerCardImages = new ArrayList<>();
        for (AbstractCard card : dealerCards) {
            dealerCardImages.add(card.getImage());
        }
        return dealerCardImages;
    }

    public void resetGame() {
        playerCards = new ArrayList<AbstractCard>();
        dealerCards = new ArrayList<AbstractCard>();
    }
}
