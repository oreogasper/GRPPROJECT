package view;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import entity.AppColors;
import interface_adapter.shop.ShopController;
import interface_adapter.shop.ShopState;
import interface_adapter.shop.ShopViewModel;

import static view.ShopButtonView.getjButton;

/**
 * The View for the shop main menu.
 */
public class ShopMainView extends JPanel implements PropertyChangeListener {
    private static final String FONT_NAME = "Serif";
    private transient ShopController shopController;
    private final JLabel username;
    private final JLabel balance;

    public ShopMainView(ShopViewModel shopViewModel) {
        this.setBackground(AppColors.DARK_GREEN);

        final JLabel title = new JLabel(ShopViewModel.TITLE_LABEL);
        title.setFont(new Font(FONT_NAME, Font.BOLD, 30));
        title.setForeground(AppColors.YELLOW);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        final JPanel titlePanel = new JPanel();
        titlePanel.setBackground(AppColors.DARK_GREEN);
        titlePanel.add(title);

        // Add buttons
        final JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 0, 40));
        buttonPanel.setBackground(AppColors.DARK_GREEN);
        final JButton wheel = createStyledButton(ShopViewModel.WHEEL_BUTTON_LABEL, AppColors.DARK_RED);
        buttonPanel.add(wrapButton(wheel));
        final JButton button = createStyledButton(ShopViewModel.BUTTON_BUTTON_LABEL, AppColors.DARK_RED);
        buttonPanel.add(wrapButton(button));


        wheel.addActionListener(evt -> shopController.switchToShopWheelView());
        button.addActionListener(evt -> shopController.switchToShopButtonView());

        // Bottom panel for username and balance
        username = new JLabel("unknown username");
        username.setForeground(AppColors.YELLOW);
        username.setFont(new Font(FONT_NAME, Font.PLAIN, 15));
        balance = new JLabel("unknown balance");
        balance.setForeground(AppColors.YELLOW);
        balance.setFont(new Font(FONT_NAME, Font.PLAIN, 15));

        // Update labels when state changes
        shopViewModel.addPropertyChangeListener(evt -> {
            if ("state".equals(evt.getPropertyName())) {
                final ShopState updatedState = (ShopState) evt.getNewValue();
                final String updatedName = updatedState.getUser().getName();
                final String updatedBalance = String.valueOf(updatedState.getUser().getBalance());

                username.setText("Currently logged in: " + updatedName);
                balance.setText("Current balance: " + updatedBalance);
            }
        });

        final JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(AppColors.DARK_GREEN);

        final JPanel leftBottomPanel = new JPanel(new GridLayout(2, 1));
        leftBottomPanel.setBackground(AppColors.DARK_GREEN);
        leftBottomPanel.add(username);
        leftBottomPanel.add(balance);

        final JPanel rightBottomPanel = new JPanel();
        final JButton back = createStyledButton(ShopViewModel.BACK_BUTTON_LABEL, AppColors.DARK_GREEN);
        back.setFont(new Font(FONT_NAME, Font.BOLD, 15));
        back.setPreferredSize(new Dimension(120, 25));

        rightBottomPanel.setBackground(AppColors.DARK_GREEN);
        rightBottomPanel.add(wrapButton(back));
        back.addActionListener(evt -> shopController.switchToMenuView());

        bottomPanel.add(leftBottomPanel, BorderLayout.WEST);
        bottomPanel.add(rightBottomPanel, BorderLayout.EAST);

        // Set layout and add components
        this.setLayout(new BorderLayout(0, 25));
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        return getjButton(button, FONT_NAME);
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
            final ShopState state = (ShopState) evt.getNewValue();
            username.setText(state.getUser().getName());
            balance.setText(String.valueOf(state.getUser().getBalance()));
        }
    }

    public String getViewName() {
        return "shop menu";
    }

    public void setShopController(ShopController controller) {
        this.shopController = controller;
    }
}
