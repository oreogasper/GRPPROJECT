package view;

import interface_adapter.gaunlet.guess.GaunletGuessController;
import interface_adapter.und_ovr.play.OverUnderPlayController;
import interface_adapter.und_ovr.play.OverUnderPlayState;
import interface_adapter.und_ovr.play.OverUnderPlayViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for the Over/Under Play Use Case.
 */
public class OverUnderPlayView extends JPanel implements PropertyChangeListener {
    private final OverUnderPlayViewModel overUnderPlayViewModel;
    private OverUnderPlayController overUnderPlayController;
    // UI Components
    private final JLabel titleLabel;
    private final JLabel currentCardImageLabel;
    private final JLabel wrongGuessesLabel;
    private final JLabel gameStatusLabel;
    private final JButton higherButton;
    private final JButton lowerButton;
    private final JButton playAgainButton;
    private final JPanel buttonsPanel;

    public OverUnderPlayView(OverUnderPlayViewModel overUnderPlayViewModel) {
        this.overUnderPlayViewModel = overUnderPlayViewModel;
        overUnderPlayViewModel.addPropertyChangeListener(this);

        // Layout setup
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Title
        titleLabel = new JLabel(OverUnderPlayViewModel.TITLE_LABEL);
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        this.add(titleLabel);

        // Current Card
        currentCardImageLabel = new JLabel();
        currentCardImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(currentCardImageLabel);

        // Wrong Guesses
        wrongGuessesLabel = new JLabel(OverUnderPlayViewModel.WRONG_GUESSES_LABEL + " 0");
        wrongGuessesLabel.setAlignmentX(CENTER_ALIGNMENT);
        this.add(wrongGuessesLabel);

        // Game Status
        gameStatusLabel = new JLabel("Make your guess!");
        gameStatusLabel.setAlignmentX(CENTER_ALIGNMENT);
        this.add(gameStatusLabel);

        // Buttons
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());

        higherButton = new JButton(OverUnderPlayViewModel.HIGHER_BUTTON_LABEL);
        lowerButton = new JButton(OverUnderPlayViewModel.LOWER_BUTTON_LABEL);

        buttonsPanel.add(higherButton);
        buttonsPanel.add(lowerButton);
        this.add(buttonsPanel);

        // Play Again Button
        playAgainButton = new JButton("Play Again");
        playAgainButton.setAlignmentX(CENTER_ALIGNMENT);
        playAgainButton.setVisible(false);
        this.add(playAgainButton);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        OverUnderPlayState newState = (OverUnderPlayState) evt.getNewValue();
        setFields(newState);
    }

    private void setFields(OverUnderPlayState state) {
        // Update current card image
        if (state.getCurrentCard() != null) {
            ImageIcon cardIcon = new ImageIcon(state.getCurrentCard());
            currentCardImageLabel.setIcon(cardIcon);
        } else {
            currentCardImageLabel.setIcon(null); // Clear image if no card
        }

        // Update wrong guesses
        wrongGuessesLabel.setText(OverUnderPlayViewModel.WRONG_GUESSES_LABEL + " " + state.getWrongGuesses());

        // Update game status and buttons
        if (state.isGameEnded()) {
            gameStatusLabel.setText(OverUnderPlayViewModel.GAME_OVER_LABEL);
            playAgainButton.setVisible(true);
            buttonsPanel.setVisible(false);
        } else {
            gameStatusLabel.setText("Make your guess!");
            playAgainButton.setVisible(false);
            buttonsPanel.setVisible(true);
        }
    }

    public void setOverUnderPlayController(OverUnderPlayController controller) {
        this.overUnderPlayController = controller;
    }
}
