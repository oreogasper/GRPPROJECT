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

    private final JPanel playerCardsPanel;
    private final JPanel dealerCardsPanel;

    private final JButton hit;
    private final JButton stand;
    private final JPanel buttons;

    private final JButton playAgain;

    private final JLabel gameStatusLabel;

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

        if (state.getTurnState().equals("Lose")) {
            gameStatusLabel.setText(BlackjackGameViewModel.LOSE_LABEL);
            playAgain.setVisible(true);
        } else if (state.getTurnState().equals("Dealer")) {
            gameStatusLabel.setText(BlackjackGameViewModel.DEALER_TURN_LABEL);
        } else if (state.getTurnState().equals("Win")) {
            gameStatusLabel.setText(BlackjackGameViewModel.WIN_LABEL);
            playAgain.setVisible(true);
        } else if (state.getTurnState().equals("Draw")) {
            gameStatusLabel.setText(BlackjackGameViewModel.DRAW_LABEL);
            playAgain.setVisible(true);
        } else {
            gameStatusLabel.setText(BlackjackGameViewModel.PLAYER_TURN_LABEL);
        }

        if (!state.getTurnState().equals("Player")) {
            buttons.setVisible(false);
        }

        playerScore.setText(BlackjackGameViewModel.SCORE_LABEL + state.getPlayerScore());


        List<Image> playerCards = state.getPlayerCards();

        this.playerCardsPanel.removeAll();
        playerCardsPanel.add(playerCardsLabel);

        for (Image img : playerCards) {
            final ImageIcon imageIcon = new ImageIcon(img);
            final JLabel card = new JLabel(imageIcon);
            playerCardsPanel.add(card);
        }

        playerCardsPanel.revalidate();
        playerCardsPanel.repaint();


        if (state.isFirstTurn()) {
            buttons.setVisible(true);
            playAgain.setVisible(false);
        }


        if (!state.getTurnState().equals("Player") || state.isFirstTurn()) {
            dealerScore.setText(BlackjackGameViewModel.SCORE_LABEL + state.getDealerScore());

            List<Image> dealerCards = state.getDealerCards();
            this.dealerCardsPanel.removeAll();
            dealerCardsPanel.add(dealerCardsLabel);

            for (Image img : dealerCards) {
                final ImageIcon imageIcon = new ImageIcon(img);
                final JLabel card = new JLabel(imageIcon);
                dealerCardsPanel.add(card);
            }
            dealerCardsPanel.revalidate();
            dealerCardsPanel.repaint();
        }
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
}
