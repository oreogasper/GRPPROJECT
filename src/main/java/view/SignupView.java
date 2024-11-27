package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import entity.AppColors;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;

/**
 * The View for the Signup Use Case.
 */
public class SignupView extends JPanel implements PropertyChangeListener {
    private final SignupViewModel signupViewModel;
    final JTextField usernameInputField = createStyledTextComponent(new JTextField());
    final JTextField passwordInputField = createStyledTextComponent(new JPasswordField());
    final JTextField repeatPasswordInputField = createStyledTextComponent(new JPasswordField());
    private SignupController signupController;
    private final JButton signUp;

    public SignupView(SignupViewModel signupViewModel) {

        this.signupViewModel = signupViewModel;
        signupViewModel.addPropertyChangeListener(this);

        final JLabel username = new JLabel(SignupViewModel.USERNAME_LABEL);
        username.setFont(new Font("Serif", Font.BOLD, 20));
        username.setForeground(AppColors.YELLOW);
        final JLabel password = new JLabel(SignupViewModel.PASSWORD_LABEL);
        password.setFont(new Font("Serif", Font.BOLD, 20));
        password.setForeground(AppColors.YELLOW);
        final JLabel rPassword = new JLabel(SignupViewModel.REPEAT_PASSWORD_LABEL);
        rPassword.setFont(new Font("Serif", Font.BOLD, 20));
        rPassword.setForeground(AppColors.YELLOW);

        final LabelTextPanel usernameInfo = new LabelTextPanel(username, usernameInputField);
        usernameInfo.setBackground(AppColors.DARK_GREEN);
        final LabelTextPanel passwordInfo = new LabelTextPanel(password, passwordInputField);
        passwordInfo.setBackground(AppColors.DARK_GREEN);
        final LabelTextPanel rPasswordInfo = new LabelTextPanel(rPassword, repeatPasswordInputField);
        rPasswordInfo.setBackground(AppColors.DARK_GREEN);


        final JPanel buttons = new JPanel();
        buttons.setBackground(AppColors.DARK_GREEN);
        JButton cancel = createStyledButton(SignupViewModel.CANCEL_BUTTON_LABEL, AppColors.BRIGHT_GREEN);
        buttons.add(cancel);
        signUp = createStyledButton(SignupViewModel.SIGNUP_BUTTON_LABEL, AppColors.DARK_RED);
        buttons.add(signUp);

        signUp.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signUp)) {
                            final SignupState currentState = signupViewModel.getState();

                            signupController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword(),
                                    currentState.getRepeatPassword()
                            );
                        }
                    }
                }
        );

        cancel.addActionListener(e -> signupController.switchToWelcomeView());

        addUsernameListener();
        addPasswordListener();
        addRepeatPasswordListener();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(AppColors.DARK_GREEN);
        this.add(Box.createVerticalStrut(20));
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(rPasswordInfo);
        this.add(buttons);
    }

    private void addUsernameListener() {
        usernameInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setUsername(usernameInputField.getText());
                signupViewModel.setState(currentState);
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

    private void addPasswordListener() {
        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setPassword(passwordInputField.getText());
                signupViewModel.setState(currentState);
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

    private void addRepeatPasswordListener() {
        repeatPasswordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setRepeatPassword(repeatPasswordInputField.getText());
                signupViewModel.setState(currentState);
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
        final SignupState state = (SignupState) evt.getNewValue();
        setFields(state);
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError());
        }
    }

    private void setFields(SignupState state) {
        usernameInputField.setText(state.getUsername());
        passwordInputField.setText(state.getPassword());
        repeatPasswordInputField.setText(state.getRepeatPassword());
    }

    private <T extends JTextComponent> T createStyledTextComponent(T textComponent) {
        textComponent.setBackground(AppColors.BRIGHT_GREEN);
        textComponent.setForeground(AppColors.YELLOW);
        textComponent.setFont(new Font("Serif", Font.PLAIN, 20));
        textComponent.setCaretColor(AppColors.YELLOW);
        textComponent.setPreferredSize(new Dimension(180, 50));
        return textComponent;
    }
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(AppColors.YELLOW);
        button.setFont(new Font("Serif", Font.BOLD, 20));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(180, 50));
        button.setBorder(BorderFactory.createLineBorder(AppColors.YELLOW, 2));
        return button;
    }

    public String getViewName() {
        return "sign up";
    }

    public void setSignupController(SignupController controller) {
        this.signupController = controller;
    }
}
