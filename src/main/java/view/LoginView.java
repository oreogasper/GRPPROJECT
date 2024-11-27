package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import entity.AppColors;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

/**
 * The View for when the user is logging into the program.
 */
public class LoginView extends JPanel implements PropertyChangeListener {
    final JTextField usernameInputField = createStyledTextComponent(new JTextField());
    final JTextField passwordInputField = createStyledTextComponent(new JPasswordField());

    private final JButton logIn;
    private LoginController loginController;

    public LoginView(LoginViewModel loginViewModel) {

        loginViewModel.addPropertyChangeListener(this);

        final JLabel username = new JLabel(LoginViewModel.USERNAME_LABEL);
        username.setFont(new Font("Serif", Font.BOLD, 20));
        username.setForeground(AppColors.YELLOW);
        final JLabel password = new JLabel(LoginViewModel.PASSWORD_LABEL);
        password.setFont(new Font("Serif", Font.BOLD, 20));
        password.setForeground(AppColors.YELLOW);

        final LabelTextPanel usernameInfo = new LabelTextPanel(username, usernameInputField);
        usernameInfo.setBackground(AppColors.DARK_GREEN);
        final LabelTextPanel passwordInfo = new LabelTextPanel(password, passwordInputField);
        passwordInfo.setBackground(AppColors.DARK_GREEN);


        final JPanel buttons = new JPanel();
        buttons.setBackground(AppColors.DARK_GREEN);
        JButton cancel = createStyledButton(LoginViewModel.CANCEL_LABEL, AppColors.BRIGHT_GREEN);
        buttons.add(cancel);
        logIn = createStyledButton(LoginViewModel.LOGIN_LABEL, AppColors.DARK_RED);
        buttons.add(logIn);


        logIn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logIn)) {
                            final LoginState currentState = loginViewModel.getState();

                            loginController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword()
                            );
                        }
                    }
                }
        );

        cancel.addActionListener(e -> loginController.switchToWelcomeView());

        usernameInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoginState currentState = loginViewModel.getState();
                currentState.setUsername(usernameInputField.getText());
                loginViewModel.setState(currentState);
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

        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoginState currentState = loginViewModel.getState();
                currentState.setPassword(passwordInputField.getText());
                loginViewModel.setState(currentState);
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

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(AppColors.DARK_GREEN);
        this.add(Box.createVerticalStrut(20));
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
        if (state.getLoginError() != null) {
            JOptionPane.showMessageDialog(this, state.getLoginError());
        }
    }

    private void setFields(LoginState state) {
        usernameInputField.setText(state.getUsername());
        passwordInputField.setText(state.getPassword());
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
        return "log in";
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
}
