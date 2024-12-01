package interface_adapter.blackjack.game;

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

    private boolean playerCardsDefault;
    private boolean dealerCardsDefault;

    private String turnState;

    private boolean isFirstTurn;

    public BlackjackGameState() {

        playerCards = new ArrayList<>();
        playerCards.add(this.loadCardHiddenImage());
        playerCards.add(this.loadCardHiddenImage());
        playerCardsDefault = true;

        dealerCards = new ArrayList<>();
        dealerCards.add(this.loadCardHiddenImage());
        dealerCards.add(this.loadCardHiddenImage());
        dealerCardsDefault = true;

        playerScore = "0";
        dealerScore = "0";

        turnState = "Player";

        isFirstTurn = true;
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

    public boolean isFirstTurn() {return isFirstTurn;}

    public void addPlayerCard(Image card) {
        if (playerCardsDefault) {
            playerCards.clear();
            playerCards.add(card);
            playerCardsDefault = false;
            isFirstTurn = false;
        }
        else {
            playerCards.add(card);
        }
    }

    public void addDealerCard(Image card) {
        if (dealerCardsDefault) {
            dealerCards.clear();
            dealerCards.add(card);
            dealerCardsDefault = false;
            isFirstTurn = false;
        }
        else {
            dealerCards.add(card);
        }
    }

    public List<Image> getPlayerCards() {
        return playerCards;
    }

    public List<Image> getDealerCards() {
        return dealerCards;
    }

    private Image loadCardHiddenImage() {
        Image image = null;

        try {
            final BufferedImage bufferedImage1 = ImageIO.read(new File("images/back-card.png"));
            image = bufferedImage1.getScaledInstance(
                    (int) Math.round(bufferedImage1.getWidth() * 0.12),
                    (int) Math.round(bufferedImage1.getHeight() * 0.12), Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;

    }

}
