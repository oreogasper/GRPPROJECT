package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import interface_adapter.gaunlet.guess.GaunletGuessController;
import interface_adapter.gaunlet.guess.GaunletGuessViewModel;

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
                        gaunletGuessController.switchToLoginView();
                    }
                }
        );

        addCoinListener();
        addDiceListener();
        addRpsListener();
        // TODO need to input this
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(coinFlipInfo);
        this.add(diceInfo);
        this.add(rpsInfo);
        this.add(buttons);
    }

    private void addCoinListener() {

    }

    private void addDiceListener() {

    }

    private void addRpsListener() {

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

    }
}
