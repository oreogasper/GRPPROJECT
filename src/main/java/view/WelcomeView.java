package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.welcome.WelcomeController;
import interface_adapter.welcome.WelcomeViewModel;

/**
 * The View for the Welcome Use Case.
 */
public class WelcomeView extends JPanel {
    private final String viewName = "welcome";

    private WelcomeController welcomeController;

    private final JButton signUp;
    private final JButton toLogin;

    public WelcomeView(WelcomeViewModel welcomeViewModel) {
        // welcomeViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(WelcomeViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        signUp = new JButton(WelcomeViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);
        toLogin = new JButton(WelcomeViewModel.TO_LOGIN_BUTTON_LABEL);
        buttons.add(toLogin);

        toLogin.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        welcomeController.switchToLoginView();
                    }
                }
        );

        signUp.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        welcomeController.switchToSignupView();
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);
    }

    public String getViewName() {
        return viewName;
    }

    public void setWelcomeController(WelcomeController controller) {
        this.welcomeController = controller;
    }
}
