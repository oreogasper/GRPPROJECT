package view;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import entity.AppColors;
import interface_adapter.menu.MenuController;
import interface_adapter.menu.MenuState;
import interface_adapter.menu.MenuViewModel;


/**
 * The View for the Welcome Use Case.
 */
public class MenuView extends JPanel implements PropertyChangeListener {
    private MenuController menuController;
    private final JLabel username;
    private final JLabel balance;

    public MenuView(MenuViewModel menuViewModel) {
        menuViewModel.addPropertyChangeListener(this);

        // Main panel background
        this.setBackground(AppColors.DARK_GREEN);

        final JLabel title = new JLabel(MenuViewModel.TITLE_LABEL);
        title.setFont(new Font("Serif", Font.BOLD, 30));
        title.setForeground(AppColors.YELLOW);
        title.setHorizontalAlignment(JLabel.CENTER);

        final JPanel titlePanel = new JPanel();
        titlePanel.setBackground(AppColors.DARK_GREEN);
        titlePanel.add(title);

        final JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 0, 40));
        buttonPanel.setBackground(AppColors.DARK_GREEN);

        final JButton gamble = createStyledButton(MenuViewModel.GAMBLE_BUTTON_LABEL, AppColors.DARK_RED);
        buttonPanel.add(wrapButton(gamble));
        final JButton stats = createStyledButton(MenuViewModel.STATS_BUTTON_LABEL, AppColors.DARK_GREEN);
        buttonPanel.add(wrapButton(stats));
        final JButton shop = createStyledButton(MenuViewModel.SHOP_BUTTON_LABEL, AppColors.DARK_GREEN);
        buttonPanel.add(wrapButton(shop));

        stats.addActionListener(evt -> menuController.switchToStatisticsView());
        gamble.addActionListener(evt -> menuController.switchToGameMenuView());
        shop.addActionListener(evt -> menuController.switchToShopView());

        // Bottom panel for username and balance
        username = new JLabel("unknown username");
        username.setForeground(AppColors.YELLOW);
        username.setFont(new Font("Serif", Font.PLAIN, 15));
        balance = new JLabel("unknown balance");
        balance.setForeground(AppColors.YELLOW);
        balance.setFont(new Font("Serif", Font.PLAIN, 15));


        final JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        bottomPanel.setBackground(AppColors.DARK_GREEN);
        bottomPanel.add(username);
        bottomPanel.add(balance);


        // Set layout and add components
        this.setLayout(new BorderLayout(0, 10));
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

    }
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(AppColors.YELLOW);
        button.setFont(new Font("Serif", Font.BOLD, 24));
        button.setFocusPainted(false);
        button.setBorder(new LineBorder(AppColors.YELLOW, 2));
        button.setPreferredSize(new Dimension(220, 50));
        return button;
    }

    /**
     * Wraps a button in a panel to ensure fixed width and spacing.
     */
    private JPanel wrapButton(JButton button) {
        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        wrapper.setBackground(AppColors.DARK_GREEN);
        wrapper.add(button);
        return wrapper;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final MenuState state = (MenuState) evt.getNewValue();
            username.setText("Currently logged in: " + state.getUser().getName());
            balance.setText("Current balance: " + state.getUser().getBalance());
        }
    }

    public String getViewName() {
        return "menu";
    }

    public void setMenuController(MenuController controller) {
        this.menuController = controller;
    }
}
