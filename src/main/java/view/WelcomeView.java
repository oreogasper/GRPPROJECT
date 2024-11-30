package view;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import entity.AppColors;
import interface_adapter.welcome.WelcomeController;
import interface_adapter.welcome.WelcomeViewModel;

/**
 * The View for the Welcome Use Case.
 */
public class WelcomeView extends JPanel {

    private WelcomeController welcomeController;

    public WelcomeView(WelcomeViewModel welcomeViewModel) {
        this.setBackground(AppColors.DARK_RED);

        final JLabel title = new JLabel(WelcomeViewModel.TITLE_LABEL);
        title.setFont(new Font("Serif", Font.BOLD, 30));
        title.setForeground(AppColors.YELLOW);
        title.setHorizontalAlignment(JLabel.CENTER);

        final JPanel titlePanel = new JPanel();
        titlePanel.setBackground(AppColors.DARK_RED);
        titlePanel.add(title);

        final JPanel buttons = new JPanel(new GridLayout(2, 1, 0, 0));
        buttons.setBackground(AppColors.DARK_RED);
        final JButton signUp = createStyledButton(WelcomeViewModel.SIGNUP_BUTTON_LABEL, AppColors.DARK_GREEN, AppColors.YELLOW);
        buttons.add(wrapButton(signUp));
        final JButton toLogin = createStyledButton(WelcomeViewModel.TO_LOGIN_BUTTON_LABEL, AppColors.DARK_GREEN, AppColors.YELLOW);
        buttons.add(wrapButton(toLogin));

        toLogin.addActionListener(evt -> welcomeController.switchToLoginView());
        signUp.addActionListener(evt -> welcomeController.switchToSignupView());

        this.setLayout(new BorderLayout(0, 60));
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(buttons, BorderLayout.CENTER);
    }
    private JButton createStyledButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFont(new Font("Serif", Font.BOLD, 24));
        button.setFocusPainted(false);
        button.setBorder(new LineBorder(AppColors.YELLOW, 2));
        button.setPreferredSize(new Dimension(220, 50));
        return button;
    }
    private JPanel wrapButton(JButton button) {
        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        wrapper.setBackground(AppColors.DARK_RED);
        wrapper.add(button);
        return wrapper;
    }

    public String getViewName() {
        return "welcome";
    }

    public void setWelcomeController(WelcomeController controller) {
        this.welcomeController = controller;
    }
}
