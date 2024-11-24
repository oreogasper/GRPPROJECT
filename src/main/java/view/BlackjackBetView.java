package view;

import interface_adapter.blackjack.bet.BlackjackBetController;
import interface_adapter.blackjack.bet.BlackjackBetState;
import interface_adapter.blackjack.bet.BlackjackBetViewModel;

import javax.swing.*;
import javax.swing.event.ChangeListener;
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

    private final JSlider betSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
    // TODO get rid of the magic numbers 0,100,0 and make it based off the users balance
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
        // TODO add listener for bet slider input

        betPanel.add(betLabel);
        betPanel.add(betSlider);

        final JPanel buttons = new JPanel();
        continueToGame = new JButton(BlackjackBetViewModel.CONTINUE_BUTTON_LABEL);
        back = new JButton(BlackjackBetViewModel.BACK_BUTTON_LABEL);

        buttons.add(continueToGame);
        buttons.add(back);

        continueToGame.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        blackjackBetController.switchToBlackjackGameView();
                    }
                }
        );

        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                blackjackBetController.switchToGameMenuView();
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(betPanel);
        this.add(buttons);

    }

    private void addBetListener() {
        // TODO
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

    private void setFields(BlackjackBetState state) {betSlider.setValue(state.getBet());}

    public String getViewName() {
        return viewName;
    }

    public void setBlackjackBetController(BlackjackBetController blackjackBetController) {
        this.blackjackBetController = blackjackBetController;
    }

}
