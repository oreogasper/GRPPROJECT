package view;

import interface_adapter.und_ovr.bet.OverUnderBetController;
import interface_adapter.und_ovr.bet.OverUnderBetState;
import interface_adapter.und_ovr.bet.OverUnderBetViewModel;
import entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for the OverUnder bet Use Case.
 */
public class OverUnderBetView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "Over/Under Bet";

    private final OverUnderBetViewModel overUnderBetViewModel;
    private final JSpinner betSpinner;
    private final JLabel usernameLabel;
    private final JLabel balanceLabel;
    private final JButton continueToGameButton;
    private final JButton backButton;
    private OverUnderBetController overUnderBetController;

    // Constructor to set up the layout and components
    public OverUnderBetView(OverUnderBetViewModel overUnderBetViewModel) {
        this.overUnderBetViewModel = overUnderBetViewModel;
        overUnderBetViewModel.addPropertyChangeListener(this);

        // Title Label
        final JLabel titleLabel = new JLabel(OverUnderBetViewModel.TITLE_LABEL);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Spinner for the bet
        SpinnerModel betModel = new SpinnerNumberModel(
                10, 1, 1000, 1);
        betSpinner = new JSpinner(betModel);

        // Labels for user info
        usernameLabel = new JLabel("Currently logged in: unknown");
        balanceLabel = new JLabel("Current balance: 0");

        // Buttons
        continueToGameButton = new JButton(OverUnderBetViewModel.CONTINUE_BUTTON_LABEL);
        backButton = new JButton(OverUnderBetViewModel.BACK_BUTTON_LABEL);

        // Panels for layout
        final JPanel userInfoPanel = new JPanel(new GridLayout(2, 1));
        userInfoPanel.add(usernameLabel);
        userInfoPanel.add(balanceLabel);

        final JPanel betPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        betPanel.add(new JLabel(OverUnderBetViewModel.BET_LABEL));
        betPanel.add(betSpinner);

        final JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(continueToGameButton);
        buttonsPanel.add(backButton);

        // Add action listeners to buttons
        continueToGameButton.addActionListener(
                evt -> overUnderBetController.switchToOverUnderGameView()
        );

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                overUnderBetController.switchToGameMenuView();
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(titleLabel);
        this.add(betPanel);
        this.add(userInfoPanel);
        this.add(buttonsPanel);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        OverUnderBetState state = (OverUnderBetState) evt.getNewValue();
        setFields(state);
        if (state.getBetError() != null) {
            JOptionPane.showMessageDialog(this, state.getBetError());
        }
        usernameLabel.setText("Currently logged in: " + state.getUser().getName());
        balanceLabel.setText("Current balance: " + state.getUser().getBalance());
    }

    /**
     * Updates the fields based on the current state.
     *
     * @param state the current state of the view model
     */
    private void setFields(OverUnderBetState state) {
        betSpinner.setValue(state.getBet());
    }

    public String getViewName() {
        return viewName;
    }

    public void setOverUnderBetController(OverUnderBetController controller) {
        this.overUnderBetController = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
