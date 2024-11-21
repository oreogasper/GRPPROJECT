package view;

import java.awt.Component;
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

/**
 * The View for the Gaunlet bet Use Case.
 */
public class GaunletBetView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "Gaunlet bet";

    private final GaunletBetViewModel gaunletBetViewModel;
    private final JTextField betInputField = new JTextField(8);
    private final JButton continueToGame;
    private final JButton back;
    private GaunletBetController gaunletBetController;

    public GaunletBetView(GaunletBetViewModel gauntletBetViewModel) {
        this.gaunletBetViewModel = gauntletBetViewModel;
        gauntletBetViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(GaunletBetViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel betInfo = new LabelTextPanel(
                new JLabel(GaunletBetViewModel.BET_LABEL), betInputField);

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
                            final String betInput = betInputField.getText().trim();
                            if (betInput.isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Please enter a bet amount.");
                            }

                            try {
                                final int betVal = Integer.parseInt(betInput);

                                final GaunletBetState currentState = gaunletBetViewModel.getState();
                                currentState.setBet(betVal);
                                gaunletBetViewModel.setState(currentState);

                                gaunletBetController.execute(
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
        setFields(state);
        if (state.getBetError() != null) {
            JOptionPane.showMessageDialog(this, state.getBetError());
        }
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
