package view;

import entity.AppColors;
import interface_adapter.und_ovr.bet.OverUnderBetController;
import interface_adapter.und_ovr.bet.OverUnderBetState;
import interface_adapter.und_ovr.bet.OverUnderBetViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Enhanced View for the OverUnder Bet Use Case.
 */
public class OverUnderBetView extends JPanel implements PropertyChangeListener {
    private final String viewName;
    private final OverUnderBetViewModel overUnderBetViewModel;
    private final JSpinner betSpinner;
    private final JLabel usernameLabel;
    private final JLabel balanceLabel;
    private final JButton continueToGameButton;
    private final JButton backButton;
    private OverUnderBetController overUnderBetController;

    public OverUnderBetView(OverUnderBetViewModel overUnderBetViewModel) {
        viewName = overUnderBetViewModel.getViewName();
        this.overUnderBetViewModel = overUnderBetViewModel;
        overUnderBetViewModel.addPropertyChangeListener(this);

        // Set panel background
        this.setBackground(AppColors.DARK_GREEN);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel titleLabel = createStyledLabel(OverUnderBetViewModel.TITLE_LABEL, 30, AppColors.YELLOW, Font.BOLD);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(titleLabel);

        // User info panel
        JPanel userInfoPanel = createPanel(AppColors.DARK_GREEN, new GridLayout(2, 1, 10, 10));
        usernameLabel = createStyledLabel("Currently logged in: unknown", 18, AppColors.WHITE, Font.PLAIN);
        balanceLabel = createStyledLabel("Current balance: 0", 18, AppColors.WHITE, Font.PLAIN);
        userInfoPanel.add(usernameLabel);
        userInfoPanel.add(balanceLabel);
        this.add(Box.createVerticalStrut(20)); // Add spacing
        this.add(userInfoPanel);

        // Bet input panel
        JPanel betPanel = createPanel(AppColors.BRIGHT_GREEN, new FlowLayout(FlowLayout.CENTER));
        JLabel betLabel = createStyledLabel(OverUnderBetViewModel.BET_LABEL, 18, AppColors.YELLOW, Font.PLAIN);
        SpinnerModel betModel = new SpinnerNumberModel(10, 1, 1000, 1);
        betSpinner = createStyledSpinner(new JSpinner(betModel));
        betPanel.add(betLabel);
        betPanel.add(betSpinner);
        this.add(Box.createVerticalStrut(20)); // Add spacing
        this.add(betPanel);

        // Buttons
        JPanel buttonPanel = createPanel(AppColors.BRIGHT_GREEN, new FlowLayout(FlowLayout.CENTER, 20, 10));
        continueToGameButton = createStyledButton(OverUnderBetViewModel.CONTINUE_BUTTON_LABEL, AppColors.DARK_GREEN);
        backButton = createStyledButton(OverUnderBetViewModel.BACK_BUTTON_LABEL, AppColors.DARK_GREEN);
        continueToGameButton.addActionListener(e -> overUnderBetController.switchToOverUnderGameView());
        backButton.addActionListener(e -> overUnderBetController.switchToGameMenuView());
        buttonPanel.add(continueToGameButton);
        buttonPanel.add(backButton);
        this.add(Box.createVerticalStrut(20)); // Add spacing
        this.add(buttonPanel);
    }

    private JLabel createStyledLabel(String text, int fontSize, Color color, int style) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", style, fontSize));
        label.setForeground(color);
        return label;
    }

    private JPanel createPanel(Color bgColor, LayoutManager layout) {
        JPanel panel = new JPanel(layout);
        panel.setBackground(bgColor);
        return panel;
    }

    private JSpinner createStyledSpinner(JSpinner spinner) {
        spinner.setFont(new Font("SansSerif", Font.PLAIN, 16));
        spinner.setPreferredSize(new Dimension(100, 30));
        return spinner;
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(AppColors.WHITE);
        button.setFont(new Font("SansSerif", Font.BOLD, 18));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(AppColors.YELLOW, 2));
        return button;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        OverUnderBetState state = (OverUnderBetState) evt.getNewValue();
        betSpinner.setValue(state.getBet());
        usernameLabel.setText("Currently logged in: " + state.getUser().getName());
        balanceLabel.setText("Current balance: " + state.getUser().getBalance());
        if (state.getBetError() != null) {
            JOptionPane.showMessageDialog(this, state.getBetError());
        }
    }

    public void setOverUnderBetController(OverUnderBetController controller) {
        this.overUnderBetController = controller;
    }

    public String getViewName() {
        return viewName;
    }
}
