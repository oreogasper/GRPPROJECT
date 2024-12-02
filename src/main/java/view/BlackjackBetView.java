package view;

import interface_adapter.blackjack.bet.BlackjackBetController;
import interface_adapter.blackjack.bet.BlackjackBetState;
import interface_adapter.blackjack.bet.BlackjackBetViewModel;
import interface_adapter.blackjack.game.BlackjackGameController;
import interface_adapter.gaunlet.bet.GaunletBetState;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for the Blackjack bet Use Case.
 */
public class BlackjackBetView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName;

    private final BlackjackBetViewModel blackjackBetViewModel;
    private BlackjackBetController blackjackBetController;
    private BlackjackGameController blackjackGameController;

    private final JSlider betSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
    private final JTextField betInputField = new JTextField();

    private final JButton continueToGame;
    private final JButton back;


    public BlackjackBetView(BlackjackBetViewModel blackjackBetViewModel) {
        this.viewName = blackjackBetViewModel.getViewName();

        this.blackjackBetViewModel = blackjackBetViewModel;
        this.blackjackBetViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(BlackjackBetViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel betPanel = new JPanel();
        betPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        final JLabel betLabel = new JLabel(BlackjackBetViewModel.BET_LABEL);

        betSlider.setMajorTickSpacing(10);
        betSlider.setPaintTicks(true);
        betSlider.setPaintLabels(true);


        betPanel.add(betLabel);
        betPanel.add(betSlider);
        betPanel.add(betInputField);

        final JPanel buttons = new JPanel();
        continueToGame = new JButton(BlackjackBetViewModel.CONTINUE_BUTTON_LABEL);
        back = new JButton(BlackjackBetViewModel.BACK_BUTTON_LABEL);

        buttons.add(continueToGame);
        buttons.add(back);

        continueToGame.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(continueToGame)) {
                            final BlackjackBetState currentState = blackjackBetViewModel.getState();
                            blackjackBetController.execute(
                                    currentState.getUser().getName(),
                                    currentState.getBet()
                            );

                            if (currentState.getBetError() == null) {
                                blackjackGameController.execute("Start", 0, null, null);
                                blackjackBetController.switchToBlackjackGameView();
                            }
                        }

                    }
                }
        );

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(back)) {
                    blackjackBetController.switchToGameMenuView();
                }

            }
        });

        addTextBetListener();
        addSliderBetListener();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(betPanel);
        this.add(buttons);

    }

    private void addTextBetListener() {
        betInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final BlackjackBetState currentState = blackjackBetViewModel.getState();
                currentState.setBet(betInputField.getText().trim());
                blackjackBetViewModel.setState(currentState);

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

    private void addSliderBetListener() {
        betSlider.addChangeListener(e -> {
            int betValue = betSlider.getValue();

            BlackjackBetState currentState = blackjackBetViewModel.getState();
            currentState.setBet(String.valueOf(betValue));
            blackjackBetViewModel.setState(currentState);

        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final BlackjackBetState state = (BlackjackBetState) evt.getNewValue();
        setFields(state);
        if (state.getBetError() != null) {
            JOptionPane.showMessageDialog(this, state.getBetError());
        }

    }

    private void setFields(BlackjackBetState state) {
        betInputField.setText(state.getBet());
        betSlider.setValue(Integer.parseInt(state.getBet()));
    }

    public String getViewName() {
        return viewName;
    }

    public void setBlackjackBetController(BlackjackBetController blackjackBetController) {
        this.blackjackBetController = blackjackBetController;
    }

    public void setBlackjackGameController(BlackjackGameController blackjackGameController) {
        this.blackjackGameController = blackjackGameController;
    }
}
