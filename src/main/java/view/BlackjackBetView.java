package view;

import entity.AppColors;
import interface_adapter.blackjack.bet.BlackjackBetController;
import interface_adapter.blackjack.bet.BlackjackBetState;
import interface_adapter.blackjack.bet.BlackjackBetViewModel;
import interface_adapter.blackjack.game.BlackjackGameController;
import interface_adapter.gaunlet.bet.GaunletBetState;
import interface_adapter.gaunlet.bet.GaunletBetViewModel;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
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

    private final JLabel username;
    private final JLabel balance;
    private final JSlider betSlider;
    private final JTextField betInputField = createStyledTextComponent(new JTextField());

    private final JButton continueToGame;
    private final JButton back;


    public BlackjackBetView(BlackjackBetViewModel blackjackBetViewModel) {
        this.viewName = blackjackBetViewModel.getViewName();
        this.setBackground(AppColors.DARK_RED);

        this.blackjackBetViewModel = blackjackBetViewModel;
        this.blackjackBetViewModel.addPropertyChangeListener(this);

        final JLabel title = createTitleLabel();

        betSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        betSlider.setMajorTickSpacing(10);
        betSlider.setPaintTicks(true);
        betSlider.setPaintLabels(true);

        final LabelSliderPanel betPanel = createLabelSliderPanel(BlackjackBetViewModel.BET_LABEL, betSlider,
                betInputField);


        username = new JLabel("Currently logged in: unknown");
        username.setFont(new Font(BlackjackBetViewModel.FONT_NAME, Font.PLAIN, BlackjackBetViewModel.SUBTITLE_SIZE));
        username.setForeground(AppColors.YELLOW);
        balance = new JLabel("Current balance: 0");
        balance.setFont(new Font(BlackjackBetViewModel.FONT_NAME, Font.PLAIN, BlackjackBetViewModel.SUBTITLE_SIZE));
        balance.setForeground(AppColors.YELLOW);



        final JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        bottomPanel.setBackground(AppColors.DARK_GREEN);
        bottomPanel.add(username);
        bottomPanel.add(balance);

        final JPanel buttons = new JPanel();
        buttons.setBackground(AppColors.DARK_GREEN);
        continueToGame = createStyledButton(BlackjackBetViewModel.CONTINUE_BUTTON_LABEL, AppColors.DARK_RED);
        buttons.add(continueToGame);
        back = createStyledButton(BlackjackBetViewModel.BACK_BUTTON_LABEL, AppColors.DARK_RED);
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
        this.add(bottomPanel);
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
            state.setBetError(null);
        }
        username.setText("Currently logged in: " + state.getUser().getName());
        balance.setText("Current balance: " + state.getUser().getBalance());
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

    private JButton createStyledButton(String text, Color bgColor) {
        final JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(AppColors.YELLOW);
        button.setFont(new Font(GaunletBetViewModel.FONT_NAME, Font.BOLD, GaunletBetViewModel.TITLE_SIZE));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(GaunletBetViewModel.WIDTH_DIM, GaunletBetViewModel.HEIGHT_DIM));
        button.setBorder(BorderFactory.createLineBorder(AppColors.YELLOW, 2));
        return button;
    }

    private JLabel createTitleLabel() {
        final JLabel title = new JLabel(BlackjackBetViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font(BlackjackBetViewModel.FONT_NAME, Font.BOLD, BlackjackBetViewModel.TITLE_SIZE));
        title.setForeground(AppColors.YELLOW);
        title.setBackground(AppColors.DARK_RED);
        return title;
    }

    private LabelSliderPanel createLabelSliderPanel(String labelText, JSlider slider, JTextField jTextField) {
        // Create the label
        final JLabel label = new JLabel(labelText);
        label.setFont(new Font(BlackjackBetViewModel.FONT_NAME, Font.PLAIN, BlackjackBetViewModel.SUBTITLE_SIZE));
        label.setForeground(AppColors.YELLOW);

        // Set the slider's background to match the panel (optional)
        slider.setBackground(AppColors.DARK_GREEN);
        slider.setForeground(AppColors.YELLOW);
        jTextField.setBackground(AppColors.DARK_GREEN);
        jTextField.setForeground(AppColors.YELLOW);

        // Create the custom panel
        final LabelSliderPanel panel = new LabelSliderPanel(label, slider, jTextField);
        panel.setBackground(AppColors.DARK_GREEN); // Set panel background
        return panel;
    }

    private <T extends JTextComponent> T createStyledTextComponent(T textComponent) {
        textComponent.setBackground(AppColors.BRIGHT_GREEN);
        textComponent.setForeground(AppColors.YELLOW);
        textComponent.setFont(new Font(BlackjackBetViewModel.FONT_NAME, Font.PLAIN, BlackjackBetViewModel.TITLE_SIZE));
        textComponent.setCaretColor(AppColors.YELLOW);
        textComponent.setPreferredSize(new Dimension(
                BlackjackBetViewModel.WIDTH_DIM, BlackjackBetViewModel.HEIGHT_DIM));
        return textComponent;
    }
}
