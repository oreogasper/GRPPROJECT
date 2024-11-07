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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;

/**
 * The View for the Welcome Page.
 */

public class WelcomeView extends JPanel {
    private final String viewName = "Welcome";
    private final String TITLE_LABEL = "Welcome Page";
    private final String CREATEACCOUNT_BUTTON_LABEL = "Create an account";
    private final String TO_LOGIN_BUTTON_LABEL = "Go to Login";
    private final JButton createAccount;
    private final JButton toLogin;

    public WelcomeView() {
        final JLabel title = new JLabel(TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        toLogin = new JButton(TO_LOGIN_BUTTON_LABEL);
        buttons.add(toLogin);
        createAccount = new JButton(CREATEACCOUNT_BUTTON_LABEL);
        buttons.add(createAccount);

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(title);
        this.add(buttons);

    }

    public String getViewName() {
        return viewName;
    }
}

