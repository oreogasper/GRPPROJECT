package view;

import interface_adapter.blackjack.game.BlackjackGameController;
import interface_adapter.blackjack.game.BlackjackGameViewModel;

import javax.swing.*;
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
    private BlackjackGameController blackjackGameController;

    public BlackjackGameView(BlackjackGameViewModel blackjackGameViewModel) {
        this.viewName = blackjackGameViewModel.getViewName();
        this.blackjackGameViewModel = blackjackGameViewModel;

        final JLabel title = new JLabel(BlackjackGameViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel playerCardsPanel = new JPanel();
        playerCardsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        final JLabel cardsLabel = new JLabel(BlackjackGameViewModel.PLAYER_HAND_LABEL);

        playerCardsPanel.add(cardsLabel);

        try {
            final BufferedImage bufferedImage1 = ImageIO.read(new File("images/back-card.png"));
            final Image scaledImage1 = bufferedImage1.getScaledInstance(
                    (int) Math.round(bufferedImage1.getWidth() * 0.12),
                    (int) Math.round(bufferedImage1.getHeight() * 0.12), Image.SCALE_SMOOTH);
            final ImageIcon cardImage1 = new ImageIcon(scaledImage1);
            final JLabel card1 = new JLabel(cardImage1);

            final BufferedImage bufferedImage2 = ImageIO.read(new File("images/back-card.png"));
            final Image scaledImage2 = bufferedImage2.getScaledInstance(
                    (int) Math.round(bufferedImage2.getWidth() * 0.12),
                    (int) Math.round(bufferedImage2.getHeight() * 0.12), Image.SCALE_SMOOTH);
            final ImageIcon cardImage2 = new ImageIcon(scaledImage2);
            final JLabel card2 = new JLabel(cardImage2);

            // Add the images to the container
            playerCardsPanel.add(card1);
            playerCardsPanel.add(card2);

        } catch (IOException e) {
            e.printStackTrace();
        }

        final JPanel buttons = new JPanel();
        final JButton hitButton = new JButton(BlackjackGameViewModel.HIT_LABEL);
        final JButton standButton = new JButton(BlackjackGameViewModel.STAND_LABEL);

        buttons.add(hitButton);
        buttons.add(standButton);

        // TODO add action listeners

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(playerCardsPanel);
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

    public void setBlackjackGameController(BlackjackGameController blackjackGameController) {
        this.blackjackGameController = blackjackGameController;
    }
}
