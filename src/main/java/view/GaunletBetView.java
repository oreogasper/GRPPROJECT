package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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
import interface_adapter.gaunlet.bet.GaunletBetController;
import interface_adapter.gaunlet.bet.GaunletBetState;
import interface_adapter.gaunlet.bet.GaunletBetViewModel;

/**
 * The View for the Gaunlet bet Use Case.
 */
public class GaunletBetView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "Gaunlet bet";

    private final GaunletBetViewModel gaunletBetViewModel;
    private final JTextField betInputField = createStyledTextComponent(new JTextField());
    private final JLabel username;
    private final JLabel balance;
    private final JButton continueToGame;
    private final JButton back;
    private GaunletBetController gaunletBetController;

    // Set up layout of bet view
    public GaunletBetView(GaunletBetViewModel gauntletBetViewModel) {
        this.gaunletBetViewModel = gauntletBetViewModel;
        this.setBackground(AppColors.DARK_RED);
        gauntletBetViewModel.addPropertyChangeListener(this);

        final JLabel title = createTitleLabel();
        final LabelTextPanel betInfo = createLabelTextPanel(GaunletBetViewModel.BET_LABEL, betInputField);

        username = new JLabel("Currently logged in: unknown");
        username.setFont(new Font(GaunletBetViewModel.FONT_NAME, Font.PLAIN, GaunletBetViewModel.SUBTITLE_SIZE));
        username.setForeground(AppColors.YELLOW);
        balance = new JLabel("Current balance: 0");
        balance.setFont(new Font(GaunletBetViewModel.FONT_NAME, Font.PLAIN, GaunletBetViewModel.SUBTITLE_SIZE));
        balance.setForeground(AppColors.YELLOW);

        final JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        bottomPanel.setBackground(AppColors.DARK_GREEN);
        bottomPanel.add(username);
        bottomPanel.add(balance);

        final JPanel buttons = new JPanel();
        buttons.setBackground(AppColors.DARK_GREEN);
        continueToGame = createStyledButton(GaunletBetViewModel.CONTINUE_BUTTON_LABEL, AppColors.DARK_RED);
        buttons.add(continueToGame);
        back = createStyledButton(GaunletBetViewModel.BACK_BUTTON_LABEL, AppColors.DARK_RED);
        buttons.add(back);

        // Instantiates bet after clicking button
        continueToGame.addActionListener(
                new ActionListener() {
                    // This creates an anonymous subclass of ActionListener and instantiates it.
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(continueToGame)) {
                            final GaunletBetState currentState = gaunletBetViewModel.getState();
                            gaunletBetController.execute(
                                    currentState.getUser().getName(),
                                    Integer.parseInt(currentState.getBet())
                            );
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
        addBetListener();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(betInfo);
        this.add(buttons);
        this.add(bottomPanel);
    }
    private void addBetListener() {
        betInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final GaunletBetState currentState = gaunletBetViewModel.getState();
                currentState.setBet(betInputField.getText().trim());
                gaunletBetViewModel.setState(currentState);
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

    @Override
        public void propertyChange(PropertyChangeEvent evt) {
        final GaunletBetState state = (GaunletBetState) evt.getNewValue();
        setFields(state);
        if (state.getBetError() != null) {
            JOptionPane.showMessageDialog(this, state.getBetError());
            state.setBetError(null);
        }
        username.setText("Currently logged in: " + state.getUser().getName());
        balance.setText("Current balance: " + state.getUser().getBalance());
    }

    private void setFields(GaunletBetState state) {
        betInputField.setText((state.getBet()));
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
        final JLabel title = new JLabel(GaunletBetViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font(GaunletBetViewModel.FONT_NAME, Font.BOLD, GaunletBetViewModel.TITLE_SIZE));
        title.setForeground(AppColors.YELLOW);
        title.setBackground(AppColors.DARK_RED);
        return title;
    }

    private LabelTextPanel createLabelTextPanel(String labelText, JTextField textField) {
        final JLabel label = new JLabel(labelText);
        label.setFont(new Font(GaunletBetViewModel.FONT_NAME, Font.PLAIN, GaunletBetViewModel.SUBTITLE_SIZE));
        label.setForeground(AppColors.YELLOW);
        final LabelTextPanel panel = new LabelTextPanel(label, textField);
        panel.setBackground(AppColors.DARK_GREEN);
        return panel;
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

}
