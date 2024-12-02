package view;

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

    private final JLabel titleLabel;
    private final JLabel currentCardImageLabel;
    private final JLabel gameStatusLabel;
    private final JButton higherButton;
    private final JButton lowerButton;
    private final JButton playAgainButton;
    private final JPanel buttonsPanel;
    private OverUnderPlayController overUnderPlayController;

    public OverUnderPlayView(OverUnderPlayViewModel overUnderPlayViewModel) {
        this.overUnderPlayViewModel = overUnderPlayViewModel;
        overUnderPlayViewModel.addPropertyChangeListener(this);

        // Layout setup
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Title
        titleLabel = new JLabel(OverUnderPlayViewModel.TITLE_LABEL);
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        this.add(titleLabel);

        // Current Card Image
        currentCardImageLabel = new JLabel();
        currentCardImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        currentCardImageLabel.setPreferredSize(new Dimension(200, 300));
        this.add(currentCardImageLabel);

        // Game Status
        gameStatusLabel = new JLabel("Make your guess!");
        gameStatusLabel.setAlignmentX(CENTER_ALIGNMENT);
        this.add(gameStatusLabel);

        // Buttons Panel
        buttonsPanel = new JPanel(new FlowLayout());

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

        // Button Actions
        higherButton.addActionListener(e -> handleGuess(true));
        lowerButton.addActionListener(e -> handleGuess(false));
        playAgainButton.addActionListener(e -> resetGame());
    }

    private void handleGuess(boolean isHigher) {
        OverUnderPlayState state = overUnderPlayViewModel.getState();
        // Compare currentCard and nextCard
        boolean correct = (isHigher && state.getNextCard().getRank() > state.getCurrentCard().getRank())
                || (!isHigher && state.getNextCard().getRank() < state.getCurrentCard().getRank());

        if (correct) {
            // Update state and UI for a correct guess
            state.setCurrentCard(state.getNextCard());
            gameStatusLabel.setText("Correct! Keep going.");
        } else {
            // Handle wrong guess
            state.setWrongGuesses();
            if (state.getWrongGuesses() >= 3) {
                state.setGameEnded(true);
                gameStatusLabel.setText(OverUnderPlayViewModel.GAME_OVER_LABEL);
            } else {
                gameStatusLabel.setText("Wrong guess! Try again.");
            }
        }
        updateUIComponents(state);
    }

    private void resetGame() {
        OverUnderPlayState state = new OverUnderPlayState();
        overUnderPlayViewModel.setState(state);
        updateUIComponents(state);
    }

    private void updateUIComponents(OverUnderPlayState state) {
        // Update card image
        if (state.getCurrentCard() != null) {
            ImageIcon cardIcon = new ImageIcon(state.getCurrentCard().getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH));
            currentCardImageLabel.setIcon(cardIcon);
        } else {
            currentCardImageLabel.setIcon(null);
        }

        // Update game status and buttons visibility
        if (state.isGameEnded()) {
            gameStatusLabel.setText(OverUnderPlayViewModel.GAME_OVER_LABEL);
            playAgainButton.setVisible(true);
            buttonsPanel.setVisible(false);
        } else {
            gameStatusLabel.setText(state.getGuessError() == null ? "Make your guess!" : state.getGuessError());
            playAgainButton.setVisible(false);
            buttonsPanel.setVisible(true);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            OverUnderPlayState newState = (OverUnderPlayState) evt.getNewValue();
            updateUIComponents(newState);
        }
    }
    public void setOverUnderPlayController(OverUnderPlayController overUnderPlayController) {
        this.overUnderPlayController = overUnderPlayController;
    }
}
