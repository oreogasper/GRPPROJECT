package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import entity.AppColors;
import interface_adapter.gaunlet.bet.GaunletBetViewModel;
import interface_adapter.gaunlet.guess.GaunletGuessController;
import interface_adapter.gaunlet.guess.GaunletGuessState;
import interface_adapter.gaunlet.guess.GaunletGuessViewModel;

/**
 * The View for the Gaunlet guess Use Case.
 */
public class GaunletGuessView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "gaunlet guess";
    private GaunletGuessController gaunletGuessController;
    private final GaunletGuessViewModel gaunletGuessViewModel;
    private final JTextField coinFlipInputField = createStyledTextComponent(new JTextField());
    private final JTextField diceInputField = createStyledTextComponent(new JTextField());
    private final JTextField rpsInputField = createStyledTextComponent(new JTextField());

    private final JButton continueToResults;

    public GaunletGuessView(GaunletGuessViewModel gaunletGuessViewModel) {

        this.gaunletGuessViewModel = gaunletGuessViewModel;
        gaunletGuessViewModel.addPropertyChangeListener(this);

        // Format title
        final JLabel title = createTitleLabel();

        final LabelTextPanel coinFlipInfo = createLabelTextPanel(
                GaunletGuessViewModel.COIN_LABEL, coinFlipInputField);
        final LabelTextPanel diceInfo = createLabelTextPanel(
                GaunletGuessViewModel.DICE_LABEL, diceInputField);
        final LabelTextPanel rpsInfo = createLabelTextPanel(
                GaunletGuessViewModel.RPS_LABEL, rpsInputField);

        // adding buttons
        final JPanel buttons = new JPanel();
        buttons.setBackground(AppColors.DARK_GREEN);
        continueToResults = createStyledButton(GaunletGuessViewModel.CONTINUE_BUTTON_LABEL, AppColors.DARK_RED);
        buttons.add(continueToResults);

        continueToResults.addActionListener(
                // updates the state after user inputs values
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(continueToResults)) {
                            final GaunletGuessState currentState = gaunletGuessViewModel.getState();
                            gaunletGuessViewModel.setState(currentState);

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
        this.setBackground(AppColors.DARK_RED);
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
            coinFlipInputField.setText("");
            diceInputField.setText("");
            rpsInputField.setText("");
        }

    }

    private void setFields(GaunletGuessState state) {
        coinFlipInputField.setText(state.getCoinGuess());
        diceInputField.setText(state.getDiceGuess());
        rpsInputField.setText(state.getRpsGuess());
    }

    private <T extends JTextComponent> T createStyledTextComponent(T textComponent) {
        textComponent.setBackground(AppColors.BRIGHT_GREEN);
        textComponent.setForeground(AppColors.YELLOW);
        textComponent.setFont(new Font(GaunletBetViewModel.FONT_NAME, Font.PLAIN, GaunletBetViewModel.TITLE_SIZE));
        textComponent.setCaretColor(AppColors.YELLOW);
        textComponent.setPreferredSize(new Dimension(
                GaunletBetViewModel.WIDTH_DIM, GaunletBetViewModel.HEIGHT_DIM));
        return textComponent;
    }

    private JButton createStyledButton(String text, Color bgColor) {
        final JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(AppColors.YELLOW);
        button.setFont(new Font(GaunletBetViewModel.FONT_NAME, Font.BOLD, GaunletBetViewModel.SUBTITLE_SIZE));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(GaunletBetViewModel.WIDTH_DIM, GaunletBetViewModel.HEIGHT_DIM));
        button.setBorder(BorderFactory.createLineBorder(AppColors.YELLOW, 2));
        return button;
    }

    private JLabel createTitleLabel() {
        final JLabel title = new JLabel(GaunletGuessViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font(GaunletGuessViewModel.FONT_NAME, Font.BOLD, GaunletGuessViewModel.TITLE_SIZE));
        title.setForeground(AppColors.YELLOW);
        title.setBackground(AppColors.DARK_RED);
        return title;
    }

    private LabelTextPanel createLabelTextPanel(String labelText, JTextField textField) {
        final JLabel label = new JLabel(labelText);
        label.setFont(new Font(GaunletBetViewModel.FONT_NAME, Font.BOLD, GaunletBetViewModel.TITLE_SIZE));
        label.setForeground(AppColors.YELLOW);
        final LabelTextPanel panel = new LabelTextPanel(label, textField);
        panel.setBackground(AppColors.DARK_GREEN);
        return panel;
    }
}
