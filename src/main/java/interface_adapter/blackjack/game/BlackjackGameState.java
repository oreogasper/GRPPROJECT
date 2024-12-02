package interface_adapter.blackjack.game;

import entity.User;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The state for the Blackjack Game View Model.
 */
public class BlackjackGameState {
    private String playerScore;
    private String dealerScore;
    private java.util.List<Image> playerCards;
    private List<Image> dealerCards;

    private boolean hideDealerCard;
    private String dealerHiddenScore;

    private String turnState;

    private String betAmount;

    private User user;

    public BlackjackGameState() {

        playerCards = new ArrayList<>();
        dealerCards = new ArrayList<>();

        playerScore = "0";
        dealerScore = "0";

        hideDealerCard = true;
        dealerHiddenScore = "0";

        turnState = "Player";

        betAmount = "0";
    }

    public void setBetAmount(String betAmount) {
        this.betAmount = betAmount;
    }

    public String getBetAmount() {
        return betAmount;
    }

    public void setTurnState(String gameState) {
        this.turnState = gameState;
    }

    public String getTurnState() {
        return this.turnState;
    }

    public void setPlayerScore(String score) {
        playerScore = score;
    }

    public void setDealerScore(String score) {
        dealerScore = score;
    }

    public String getPlayerScore() {
        return playerScore;
    }

    public String getDealerScore() {
        return dealerScore;
    }


    public void addPlayerCard(Image card) {
            playerCards.add(card);
    }

    public void addDealerCard(Image card) {
            dealerCards.add(card);
    }

    public List<Image> getPlayerCards() {
        return playerCards;
    }

    public List<Image> getDealerCards() {
        return dealerCards;
    }

    public boolean hideDealerCard() {
        return hideDealerCard;
    }

    public void setHideDealerCard(boolean hideDealerCard) {
        this.hideDealerCard = hideDealerCard;
    }

    public String getDealerHiddenScore() {
        return dealerHiddenScore;
    }

    public void setDealerHiddenScore(String dealerHiddenScore) {
        this.dealerHiddenScore = dealerHiddenScore;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
