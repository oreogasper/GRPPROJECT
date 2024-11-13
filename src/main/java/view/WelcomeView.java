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

import interface_adapter.signup.SignupState;
import interface_adapter.welcome.WelcomeController;
// import interface_adapter.signup.SignupState;
import interface_adapter.welcome.WelcomeViewModel;

/**
 * The View for the Welcome Use Case.
 */
public class WelcomeView extends JPanel {
    private final String viewName = "welcome";

    private WelcomeController welcomeController;

    private final JButton signUp;
    private final JButton toLogin;

    // TEMPORARY BUTTONS
    private final JButton stats;
    private final JButton blackjack;
    private final JButton gauntlet;
    private final JButton overUnder;
    private final JButton shop;

    public WelcomeView(WelcomeViewModel welcomeViewModel) {
        // welcomeViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(WelcomeViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        signUp = new JButton(WelcomeViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);
        toLogin = new JButton(WelcomeViewModel.TO_LOGIN_BUTTON_LABEL);
        buttons.add(toLogin);

        // TEMPORARY BUTTONS
        final JPanel tButtons = new JPanel();
        stats = new JButton(WelcomeViewModel.STATS_BUTTON_LABEL);
        tButtons.add(stats);
        blackjack = new JButton(WelcomeViewModel.BLACKJACK_BUTTON_LABEL);
        tButtons.add(blackjack);
        gauntlet = new JButton(WelcomeViewModel.GAUNTLET_BUTTON_LABEL);
        tButtons.add(gauntlet);
        overUnder = new JButton(WelcomeViewModel.OVERUNDER_BUTTON_LABEL);
        tButtons.add(overUnder);
        shop = new JButton(WelcomeViewModel.SHOP_BUTTON_LABEL);
        tButtons.add(shop);

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

        // TEMPORARY BUTTON ACTION LISTENERS

        stats.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        welcomeController.switchToLoginView();
                    }
                }
        );
        blackjack.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        welcomeController.switchToLoginView();
                    }
                }
        );
        gauntlet.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        welcomeController.switchToLoginView();
                    }
                }
        );
        overUnder.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        welcomeController.switchToLoginView();
                    }
                }
        );
        shop.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        welcomeController.switchToLoginView();
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);
        this.add(tButtons);
    }

    public String getViewName() {
        return viewName;
    }

    public void setWelcomeController(WelcomeController controller) {
        this.welcomeController = controller;
    }
}
