package view;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

import entity.AppColors;
import interface_adapter.shop.button.ShopButtonController;
import interface_adapter.shop.button.ShopButtonState;
import interface_adapter.shop.button.ShopButtonViewModel;
import org.jetbrains.annotations.NotNull;

/**
 * The view class for the Shop button class.
 */
public class ShopButtonView extends JPanel implements PropertyChangeListener {
    private static final String FONT_NAME = "Serif";
    private transient ShopButtonController shopButtonController;
    private final JLabel username;
    private final JLabel balance;
    private final JLabel clicksMade;

    public ShopButtonView(ShopButtonViewModel shopButtonViewModel) {
        this.setBackground(AppColors.DARK_GREEN);

        // Add title
        final JLabel title = new JLabel(ShopButtonViewModel.TITLE_LABEL);
        title.setFont(new Font(FONT_NAME, Font.BOLD, 30));
        title.setForeground(AppColors.YELLOW);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        final JPanel titlePanel = new JPanel();
        titlePanel.setBackground(AppColors.DARK_GREEN);
        titlePanel.add(title);

        // Labels
        username = new JLabel("unknown username");
        username.setForeground(AppColors.YELLOW);
        username.setFont(new Font(FONT_NAME, Font.PLAIN, 15));
        balance = new JLabel("unknown balance");
        balance.setForeground(AppColors.YELLOW);
        balance.setFont(new Font(FONT_NAME, Font.PLAIN, 15));
        clicksMade = new JLabel("Tokens earned this session: 0");
        clicksMade.setForeground(AppColors.YELLOW);
        clicksMade.setFont(new Font(FONT_NAME, Font.PLAIN, 20));

        // Compose center panel
        final JButton clicker = createStyledButton(ShopButtonViewModel.CLICK_BUTTON_LABEL);
        clicker.addActionListener(evt -> shopButtonController.buttonClick(shopButtonViewModel.getState().getClicksMade()));
        clicker.setFont(new Font(FONT_NAME, Font.PLAIN, 30));
        clicker.setPreferredSize(new Dimension(80, 80));

// Wrap clicksMade label for consistent alignment
        final JPanel clicksPanel = new JPanel();
        clicksPanel.setBackground(AppColors.DARK_GREEN);
        clicksPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        clicksPanel.add(clicksMade);
        clicksMade.setAlignmentX(Component.CENTER_ALIGNMENT);
        clicksMade.setHorizontalAlignment(SwingConstants.CENTER);
        clicksMade.setPreferredSize(new Dimension(300, 30)); // Adjust as needed

// Center panel with vertical alignment
        final JPanel centerPanel = new JPanel();
        centerPanel.setBackground(AppColors.DARK_GREEN);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(wrapButton(clicker));
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        centerPanel.add(clicksPanel); // Add wrapped panel
        centerPanel.add(Box.createVerticalGlue());



        // Bottom panel(s)
        final JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(AppColors.DARK_GREEN);

        final JPanel leftBottomPanel = new JPanel(new GridLayout(2, 1));
        leftBottomPanel.setBackground(AppColors.DARK_GREEN);
        leftBottomPanel.add(username);
        leftBottomPanel.add(balance);

        final JPanel rightBottomPanel = new JPanel();
        final JButton back = createStyledButton(ShopButtonViewModel.SHOP_BUTTON_LABEL);
        back.setFont(new Font(FONT_NAME, Font.BOLD, 15));
        back.setPreferredSize(new Dimension(120, 25));

        rightBottomPanel.setBackground(AppColors.DARK_GREEN);
        rightBottomPanel.add(wrapButton(back));
        back.addActionListener(e -> {
            shopButtonController.switchToShopView();
            shopButtonController.saveData(shopButtonViewModel.getState().getUser().getName(),
                    shopButtonViewModel.getState().getUser().getBalance());
        });

        bottomPanel.add(leftBottomPanel, BorderLayout.WEST);
        bottomPanel.add(rightBottomPanel, BorderLayout.EAST);

        // Set layout
        this.setLayout(new BorderLayout());
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

        // Register property change listener
        shopButtonViewModel.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            final ShopButtonState updatedState = (ShopButtonState) evt.getNewValue();
            username.setText("Currently logged in: " + updatedState.getUser().getName());
            balance.setText("Current balance: " + updatedState.getUser().getBalance());
            clicksMade.setText("Clicks made this session: "
                    + (updatedState.getClicksMade() / ShopButtonViewModel.DIVIDER));
        } else if (evt.getPropertyName().equals("logout")) {
            final ShopButtonState updatedState = (ShopButtonState) evt.getNewValue();
            updatedState.resetClicks();
            clicksMade.setText(String.valueOf(updatedState.getClicksMade()));
        }
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(AppColors.DARK_GREEN);
        return getjButton(button, FONT_NAME);
    }

    @NotNull
    static JButton getjButton(JButton button, String fontName) {
        button.setForeground(AppColors.YELLOW);
        button.setFont(new Font(fontName, Font.BOLD, 24));
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

    public String getViewName() {
        return "shop button";
    }

    public void setShopButtonController(ShopButtonController controller) {
        this.shopButtonController = controller;
    }
}
