package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.gaunlet.bet.GaunletBetController;
import interface_adapter.gaunlet.bet.GaunletBetState;
import interface_adapter.gaunlet.bet.GaunletBetViewModel;
import interface_adapter.signup.SignupState;

/**
 * The View for the Gaunlet bet Use Case.
 */
public class GaunletBetView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "Gaunlet bet";

    private final GaunletBetViewModel gaunletBetViewModel;
    private final JTextField betInputField = new JTextField(8);
    private final JLabel username;
    private final JLabel balance;
    private final JButton continueToGame;
    private final JButton back;
    private GaunletBetController gaunletBetController;

    public GaunletBetView(GaunletBetViewModel gauntletBetViewModel) {
        this.gaunletBetViewModel = gauntletBetViewModel;
        gauntletBetViewModel.addPropertyChangeListener(this);
        System.out.println("GaunletBetViewModel initialized with state: " + gaunletBetViewModel.getState());

        final JLabel title = new JLabel(GaunletBetViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel betInfo = new LabelTextPanel(
                new JLabel(GaunletBetViewModel.BET_LABEL), betInputField);

        username = new JLabel("Currently logged in: unknown");
        balance = new JLabel("Current balance: 0");

        final JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        bottomPanel.add(username);
        bottomPanel.add(balance);
        this.add(bottomPanel, BorderLayout.SOUTH);

        final JPanel buttons = new JPanel();
        continueToGame = new JButton(GaunletBetViewModel.CONTINUE_BUTTON_LABEL);
        buttons.add(continueToGame);
        back = new JButton(GaunletBetViewModel.BACK_BUTTON_LABEL);
        buttons.add(back);

        continueToGame.addActionListener(
                new ActionListener() {
                    // This creates an anonymous subclass of ActionListener and instantiates it.
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(continueToGame)) {
                            final GaunletBetState currentState = gaunletBetViewModel.getState();
                            final String betInput = betInputField.getText().trim();
                            if (betInput.isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Please enter a bet amount.");
                            }

                            try {
                                final int betVal = Integer.parseInt(betInput);

                                currentState.setBet(betVal);
                                gaunletBetViewModel.setState(currentState);
                                gaunletBetController.execute(
                                        currentState.getUser().getName(),
                                        currentState.getBet()
                                );
                            }
                            catch (NumberFormatException e) {
                                System.out.println("Bet input: '" + betInput + "'");
                                JOptionPane.showMessageDialog(null, "Please enter a valid numeric bet amount.");
                            }
                        }
                    }
                }
        );

        back.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        gaunletBetController.switchToGameMenuView();
                    }
                }
        );
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(betInfo);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final GaunletBetState state = (GaunletBetState) evt.getNewValue();
        System.out.println("GaunletBetViewModel initialized with state: ");
        System.out.println(gaunletBetViewModel.getState());
        setFields(state);
        if (state.getBetError() != null) {
            JOptionPane.showMessageDialog(this, state.getBetError());
        }
        username.setText("Currently logged in: " + state.getUser().getName());
        balance.setText("Current balance: " + state.getUser().getBalance());
    }

    private void setFields(GaunletBetState state) {
        betInputField.setText(String.valueOf(state.getBet()));
    }

    public String getViewName() {
        return viewName;
    }

    public void setGaunletBetController(GaunletBetController controller) {
        this.gaunletBetController = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
