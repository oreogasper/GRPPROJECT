package view;

import interface_adapter.und_ovr.OverUnderController;
import interface_adapter.und_ovr.OverUnderViewModel;

import javax.swing.*;
import java.awt.*;

public class OverUnderView extends JPanel {

    private final OverUnderViewModel viewModel;
    private JTextField betField;
    private JButton placeBetButton;
    private OverUnderController controller;
    final private String viewName;

    // New UI components
    private JLabel balanceLabel;   // Label to display the user's balance
    private JLabel cardLabel;      // Label to display the current card
    private JButton higherButton;  // Button to guess higher
    private JButton lowerButton;   // Button to guess lower
    private JLabel messageLabel;   // Label to display messages

    public OverUnderView(OverUnderViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewName = "OverUnderView";
        initializeUI();

        // Listen to changes in the ViewModel
        this.viewModel.addPropertyChangeListener(e -> updateView());
    }

    private void initializeUI() {
        // Layout setup
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(Component.CENTER_ALIGNMENT);

        // Balance display
        balanceLabel = new JLabel("Current Balance: $0");
        balanceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(balanceLabel);

        // Card display
        cardLabel = new JLabel("Current Card: ");
        cardLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(cardLabel);

        // Bet input panel
        JPanel betPanel = new JPanel();
        betPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel betLabel = new JLabel("Enter Bet Amount:");
        betField = new JTextField(5);
        betField.setPreferredSize(new Dimension(50, 25));
        betPanel.add(betLabel);
        betPanel.add(betField);
        add(betPanel);

        // Place Bet Button
        placeBetButton = new JButton("Place Bet");
        placeBetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        placeBetButton.addActionListener(e -> handleBet());
        add(placeBetButton);

        // Guessing buttons (Higher/Lower)
        higherButton = new JButton("Higher");
        lowerButton = new JButton("Lower");

        // Action listeners for higher/lower guesses
        higherButton.addActionListener(e -> handleGuess(true));
        lowerButton.addActionListener(e -> handleGuess(false));

        // Add guess buttons to the layout
        add(higherButton);
        add(lowerButton);

        // Message display
        messageLabel = new JLabel("");
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(messageLabel);

        // Optional: Add spacing below the button
        add(Box.createVerticalStrut(10));
    }

    // Handle bet placement
    private void handleBet() {
        try {
            final int betAmount = Integer.parseInt(betField.getText().trim());
            if (betAmount > 10) {
                controller.placeBet(betAmount);
                JOptionPane.showMessageDialog(this, "Bet placed! Proceeding to the game...");
                controller.startGame();
            } else {
                JOptionPane.showMessageDialog(this, "Bet amount must be greater than 10.", "Invalid Bet", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Handle the user's guess (higher or lower)
    private void handleGuess(boolean isHigher) {
        controller.makeGuess(isHigher);
    }

    // Update the UI based on the ViewModel's state
    private void updateView() {
        // Update the balance label
        balanceLabel.setText("Current Balance: $" + viewModel.getBalance());

        // Update the card label
        final int currentCardInfo = viewModel.getCurrentCard().getRank(); // Get current card's info from viewModel
        cardLabel.setText("Current Card: " + currentCardInfo);
    }

    // Set the controller for handling user actions
    public void setController(OverUnderController controller) {
        this.controller = controller;
    }

    // Get the view's name (for debugging or identification purposes)
    public String getViewName() {
        return this.viewName;
    }

    public void displayMessage(String message) {
        messageLabel.setText(message);
    }

    // Method to update the card display
    public void updateCardDisplay(String cardInfo) {
        cardLabel.setText("Current card: " + cardInfo);
    }

    // Method to update the balance display
    public void updateBalanceDisplay(int newBalance) {
        balanceLabel.setText("Balance: " + newBalance);
    }

    // Method to show an error message (can be a popup)
    public void displayError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
