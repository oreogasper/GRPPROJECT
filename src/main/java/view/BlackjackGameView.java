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
    private BlackjackHitController hitController;
    private BlackjackStandController standController;

    public BlackjackGameView(BlackjackGameViewModel blackjackGameViewModel) {
        this.viewName = blackjackGameViewModel.getViewName();
        this.blackjackGameViewModel = blackjackGameViewModel;

        final JLabel title = new JLabel(BlackjackGameViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JLabel playerCardsLabel = new JLabel(BlackjackGameViewModel.PLAYER_HAND_LABEL);
        final JPanel playerCardsPanel = new JPanel();
        playerCardsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        playerCardsPanel.add(playerCardsLabel);

        final JLabel dealerCardsLabel = new JLabel(BlackjackGameViewModel.DEALER_HAND_LABEL);
        final JPanel dealerCardsPanel = new JPanel();
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
        final JLabel playerScoreLabel = new JLabel(
                BlackjackGameViewModel.SCORE_LABEL + initialState.getPlayerScore());

        playerScorePanel.add(playerScoreLabel);


        final JPanel dealerScorePanel = new JPanel();
        final JLabel dealerScoreLabel = new JLabel(
                BlackjackGameViewModel.SCORE_LABEL + initialState.getDealerScore());

        dealerScorePanel.add(dealerScoreLabel);

        final JPanel buttons = new JPanel();
        final JButton hitButton = new JButton(BlackjackGameViewModel.HIT_LABEL);
        final JButton standButton = new JButton(BlackjackGameViewModel.STAND_LABEL);

        buttons.add(hitButton);
        buttons.add(standButton);


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(playerCardsPanel);
        this.add(playerScorePanel);
        this.add(dealerCardsPanel);
        this.add(dealerScorePanel);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

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
