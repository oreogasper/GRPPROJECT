package view;

import interface_adapter.blackjack.game.BlackjackGameController;
import interface_adapter.blackjack.game.BlackjackGameState;
import interface_adapter.blackjack.game.BlackjackGameViewModel;
import interface_adapter.blackjack.game.hit.BlackjackHitController;
import interface_adapter.blackjack.game.stand.BlackjackStandController;

import javax.swing.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The View for the Blackjack Game Use Case.
 */
public class BlackjackGameView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName;
    private final BlackjackGameViewModel blackjackGameViewModel;

    private final JLabel playerScore;
    private final JLabel dealerScore;

    private final JLabel playerCardsLabel;
    private final JLabel dealerCardsLabel;
    private final Image hiddenImage = loadCardHiddenImage();

    private final JPanel playerCardsPanel;
    private final JPanel dealerCardsPanel;

    private final JButton hit;
    private final JButton stand;
    private final JPanel buttons;

    private final JButton playAgain;

    private final JLabel gameStatusLabel;

    private final JLabel betAmountLabel;

    private BlackjackHitController hitController;
    private BlackjackStandController standController;

    public BlackjackGameView(BlackjackGameViewModel blackjackGameViewModel) {
        this.viewName = blackjackGameViewModel.getViewName();
        this.blackjackGameViewModel = blackjackGameViewModel;
        blackjackGameViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(BlackjackGameViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        gameStatusLabel = new JLabel(BlackjackGameViewModel.PLAYER_TURN_LABEL);
        gameStatusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        betAmountLabel = new JLabel(BlackjackGameViewModel.BET_AMOUNT_LABEL +
                blackjackGameViewModel.getState().getBetAmount());
        betAmountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.playerCardsLabel = new JLabel(BlackjackGameViewModel.PLAYER_HAND_LABEL);
        this.playerCardsPanel = new JPanel();
        playerCardsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        playerCardsPanel.add(playerCardsLabel);

        this.dealerCardsLabel = new JLabel(BlackjackGameViewModel.DEALER_HAND_LABEL);
        this.dealerCardsPanel = new JPanel();
        dealerCardsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        dealerCardsPanel.add(dealerCardsLabel);

        final BlackjackGameState initialState = blackjackGameViewModel.getState();
        final List<Image> initialPlayerCards = initialState.getPlayerCards();
        for (Image img : initialPlayerCards) {
            final ImageIcon imageIcon = new ImageIcon(img);
            final JLabel card = new JLabel(imageIcon);
            playerCardsPanel.add(card);
        }

        final List<Image> initialDealerCards = initialState.getDealerCards();
        for (Image img : initialDealerCards) {
            final ImageIcon imageIcon = new ImageIcon(img);
            final JLabel card = new JLabel(imageIcon);
            dealerCardsPanel.add(card);
        }

        final JPanel playerScorePanel = new JPanel();
        this.playerScore = new JLabel(BlackjackGameViewModel.SCORE_LABEL + initialState.getPlayerScore());

        playerScorePanel.add(playerScore);


        final JPanel dealerScorePanel = new JPanel();
        this.dealerScore = new JLabel(BlackjackGameViewModel.SCORE_LABEL + initialState.getDealerScore());

        dealerScorePanel.add(dealerScore);

        buttons = new JPanel();
        this.hit = new JButton(BlackjackGameViewModel.HIT_LABEL);
        this.stand = new JButton(BlackjackGameViewModel.STAND_LABEL);

        playAgain = new JButton(BlackjackGameViewModel.PLAY_AGAIN_LABEL);
        playAgain.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttons.add(hit);
        buttons.add(stand);


        hit.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final BlackjackGameState gameState = blackjackGameViewModel.getState();
                        if (evt.getSource().equals(hit) && gameState.getTurnState().equals("Player")) {

                            hitController.execute(false);
                            if (gameState.getTurnState().equals("Dealer")) {
                                standController.execute(gameState.getTurnState());
                            }
                        }

                    }
                }
        );

        stand.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final BlackjackGameState gameState = blackjackGameViewModel.getState();
                        if (evt.getSource().equals(stand) && gameState.getTurnState().equals("Player")) {

                            standController.execute(gameState.getTurnState());
                        }
                    }
                }
        );

        playAgain.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final BlackjackGameState gameState = blackjackGameViewModel.getState();
                        if (evt.getSource().equals(playAgain) && !gameState.getTurnState().equals("Player")
                        && !gameState.getTurnState().equals("Dealer")) {

                            standController.switchToBetView();
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(gameStatusLabel);
        this.add(betAmountLabel);
        this.add(playerCardsPanel);
        this.add(playerScorePanel);
        this.add(dealerCardsPanel);
        this.add(dealerScorePanel);
        this.add(buttons);
        this.add(playAgain);
        playAgain.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final BlackjackGameState gameState = (BlackjackGameState) evt.getNewValue();
        setFields(gameState);
    }

    private void setFields(BlackjackGameState state) {

        updatePlayerHand(state);

        updateDealerHand(state);

        updateAllButtons(state);

        betAmountLabel.setText(BlackjackGameViewModel.BET_AMOUNT_LABEL + state.getBetAmount());

    }

    private void updateDealerHand(BlackjackGameState state) {
        if (state.hideDealerCard()) {
            dealerScore.setText(BlackjackGameViewModel.SCORE_LABEL + state.getDealerHiddenScore());

            this.dealerCardsPanel.removeAll();
            dealerCardsPanel.add(dealerCardsLabel);

            List<Image> dealerCards = state.getDealerCards();

            Image revealedCard = this.hiddenImage;
            if (!dealerCards.isEmpty()) {
                revealedCard = dealerCards.get(0);
            }

            final ImageIcon revealedCardIcon = new ImageIcon(revealedCard);
            final ImageIcon hiddenCardIcon = new ImageIcon(this.hiddenImage);

            final JLabel revealedCardLabel = new JLabel(revealedCardIcon);
            final JLabel hiddenCardLabel = new JLabel(hiddenCardIcon);

            dealerCardsPanel.add(revealedCardLabel);
            dealerCardsPanel.add(hiddenCardLabel);


        } else {
            dealerScore.setText(BlackjackGameViewModel.SCORE_LABEL + state.getDealerScore());

            this.dealerCardsPanel.removeAll();
            dealerCardsPanel.add(dealerCardsLabel);

            List<Image> dealerCards = state.getDealerCards();

            for (Image img : dealerCards) {
                final ImageIcon imageIcon = new ImageIcon(img);
                final JLabel card = new JLabel(imageIcon);
                dealerCardsPanel.add(card);
            }

        }

        dealerCardsPanel.revalidate();
        dealerCardsPanel.repaint();

    }

    private void updateAllButtons(BlackjackGameState state) {
        switch (state.getTurnState()) {
            case "Lose":
                gameStatusLabel.setText(BlackjackGameViewModel.LOSE_LABEL);
                playAgain.setVisible(true);
                buttons.setVisible(false);
                break;
            case "Win":
                gameStatusLabel.setText(BlackjackGameViewModel.WIN_LABEL);
                playAgain.setVisible(true);
                buttons.setVisible(false);
                break;
            case "Draw":
                gameStatusLabel.setText(BlackjackGameViewModel.DRAW_LABEL);
                playAgain.setVisible(true);
                buttons.setVisible(false);
                break;
            case "Dealer":
                gameStatusLabel.setText(BlackjackGameViewModel.DEALER_TURN_LABEL);
                buttons.setVisible(false);
                playAgain.setVisible(false);
                break;
            default:
                gameStatusLabel.setText(BlackjackGameViewModel.PLAYER_TURN_LABEL);
                buttons.setVisible(true);
                playAgain.setVisible(false);
                break;
        }

    }

    private void updatePlayerHand(BlackjackGameState state) {
        playerScore.setText(BlackjackGameViewModel.SCORE_LABEL + state.getPlayerScore());

        List<Image> playerCards = state.getPlayerCards();

        if (playerCards.isEmpty()) {
            this.playerCardsPanel.removeAll();
            playerCardsPanel.add(playerCardsLabel);

            final ImageIcon hiddenCardIcon = new ImageIcon(this.hiddenImage);
            final JLabel hiddenCardLabel = new JLabel(hiddenCardIcon);

            playerCardsPanel.add(hiddenCardLabel);
            playerCardsPanel.add(hiddenCardLabel);

        } else {
            this.playerCardsPanel.removeAll();
            playerCardsPanel.add(playerCardsLabel);

            for (Image img : playerCards) {
                final ImageIcon imageIcon = new ImageIcon(img);
                final JLabel card = new JLabel(imageIcon);
                playerCardsPanel.add(card);
            }

        }

        playerCardsPanel.revalidate();
        playerCardsPanel.repaint();

    }

    public String getViewName() {
        return viewName;
    }

    public void setHitController(BlackjackHitController hitController) {
        this.hitController = hitController;
    }

    public void setStandController(BlackjackStandController standController) {
        this.standController = standController;
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
