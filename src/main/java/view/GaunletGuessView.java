package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.gaunlet.guess.GaunletGuessController;
import interface_adapter.gaunlet.guess.GaunletGuessState;
import interface_adapter.gaunlet.guess.GaunletGuessViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;

/**
 * The View for the Gaunlet guess Use Case.
 */
public class GaunletGuessView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "gaunlet guess";
    private GaunletGuessController gaunletGuessController;
    private final GaunletGuessViewModel gaunletGuessViewModel;
    private final JTextField coinFlipInputField = new JTextField(8);
    private final JTextField diceInputField = new JTextField(8);
    private final JTextField rpsInputField = new JTextField(8);

    private final JButton continueToResults;

    public GaunletGuessView(GaunletGuessViewModel gaunletGuessViewModel) {

        this.gaunletGuessViewModel = gaunletGuessViewModel;
        gaunletGuessViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(GaunletGuessViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel coinFlipInfo = new LabelTextPanel(
                new JLabel(GaunletGuessViewModel.COIN_LABEL), coinFlipInputField);
        final LabelTextPanel diceInfo = new LabelTextPanel(
                new JLabel(GaunletGuessViewModel.DICE_LABEL), diceInputField);
        final LabelTextPanel rpsInfo = new LabelTextPanel(
                new JLabel(GaunletGuessViewModel.RPS_LABEL), rpsInputField);

        final JPanel buttons = new JPanel();
        continueToResults = new JButton(GaunletGuessViewModel.CONTINUE_BUTTON_LABEL);
        buttons.add(continueToResults);

        continueToResults.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        System.out.println("Button clicked! Executing the action...");
                        if (evt.getSource().equals(continueToResults)) {
                            final GaunletGuessState currentState = gaunletGuessViewModel.getState();
                            gaunletGuessViewModel.setState(currentState);
                            System.out.println("GaunletGuessViewModel initialized with state: ");
                            System.out.println(gaunletGuessViewModel.getState());
                            gaunletGuessController.execute(
                                    currentState.getUser().getName(),
                                    currentState.getCoinGuess(),
                                    currentState.getDiceGuess(),
                                    currentState.getRpsGuess()
                            );
                        }
                    }
                }
        );

        addCoinListener();
        addDiceListener();
        addRpsListener();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(coinFlipInfo);
        this.add(diceInfo);
        this.add(rpsInfo);
        this.add(buttons);
    }

    private void addCoinListener() {
        coinFlipInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final GaunletGuessState currentState = gaunletGuessViewModel.getState();
                currentState.setCoinGuess(coinFlipInputField.getText());
                gaunletGuessViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

    }

    private void addDiceListener() {
        diceInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final GaunletGuessState currentState = gaunletGuessViewModel.getState();
                currentState.setDiceGuess(diceInputField.getText());
                gaunletGuessViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

    }

    private void addRpsListener() {
        rpsInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final GaunletGuessState currentState = gaunletGuessViewModel.getState();
                currentState.setRpsGuess(rpsInputField.getText());
                gaunletGuessViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

    }

    public String getViewName() {
        return viewName;
    }

    public void setGaunletGuessController(GaunletGuessController controller) {
        this.gaunletGuessController = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final GaunletGuessState state = (GaunletGuessState) evt.getNewValue();
        setFields(state);
        if (state.getCoinGuessError() != null) {
            JOptionPane.showMessageDialog(this, state.getCoinGuessError());
        }

    }

    private void setFields(GaunletGuessState state) {
        coinFlipInputField.setText(state.getCoinGuess());
        diceInputField.setText(state.getDiceGuess());
        rpsInputField.setText(state.getRpsGuess());
    }

}
