package view;

import entity.AppColors;
import interface_adapter.blackjack.bet.BlackjackBetViewModel;
import interface_adapter.blackjack.game.BlackjackGameController;
import interface_adapter.blackjack.game.BlackjackGameState;
import interface_adapter.blackjack.game.BlackjackGameViewModel;
import interface_adapter.blackjack.game.hit.BlackjackHitController;
import interface_adapter.blackjack.game.stand.BlackjackStandController;
import interface_adapter.gaunlet.bet.GaunletBetViewModel;

import javax.swing.*;
import java.security.PrivateKey;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * The View for the Blackjack Game Use Case.
 */
public class BlackjackGameView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName;
    private final BlackjackGameViewModel blackjackGameViewModel;

    private final JLabel playerScore;
    private final JLabel dealerScore;

    private final JLabel playerCardsLabel;
    private final JLabel dealerCardsLabel;
    private final Image hiddenImage = loadCardHiddenImage();

    private final JPanel playerCardsPanel;
    private final JPanel dealerCardsPanel;

    private final JButton hit;
    private final JButton stand;
    private final JPanel buttons;

    private final JPanel playAgainPanel;

    private final JLabel gameStatusLabel;

    private final JLabel betAmountLabel;


    private BlackjackGameController blackjackGameController;


    public BlackjackGameView(BlackjackGameViewModel blackjackGameViewModel) {
        this.viewName = blackjackGameViewModel.getViewName();
        this.setBackground(AppColors.DARK_RED);
        this.blackjackGameViewModel = blackjackGameViewModel;
        blackjackGameViewModel.addPropertyChangeListener(this);

//        username = new JLabel("Currently logged in: unknown");
//        balance = new JLabel("Current balance: 0");
//
//        final JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
//        bottomPanel.add(username);
//        bottomPanel.add(balance);


        final JLabel title = createTitleLabel();

        gameStatusLabel = createTurnLabel();

        final JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
        bottomPanel.setBackground(AppColors.DARK_GREEN);
        betAmountLabel = new JLabel(BlackjackGameViewModel.BET_AMOUNT_LABEL +
                blackjackGameViewModel.getState().getBetAmount());
        betAmountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        betAmountLabel.setFont(new Font(BlackjackBetViewModel.FONT_NAME, Font.PLAIN, BlackjackBetViewModel.SUBTITLE_SIZE));
        betAmountLabel.setForeground(AppColors.YELLOW);
        bottomPanel.add(betAmountLabel);
        bottomPanel.setBackground(AppColors.DARK_GREEN);

        this.playerCardsLabel = new JLabel(BlackjackGameViewModel.PLAYER_HAND_LABEL);
        playerCardsLabel.setFont(new Font(BlackjackBetViewModel.FONT_NAME, Font.PLAIN, BlackjackBetViewModel.SUBTITLE_SIZE));
        playerCardsLabel.setForeground(AppColors.YELLOW);
        this.playerCardsPanel = new JPanel();
        playerCardsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        playerCardsPanel.add(playerCardsLabel);
        playerCardsPanel.setBackground(AppColors.DARK_GREEN);

        this.dealerCardsLabel = new JLabel(BlackjackGameViewModel.DEALER_HAND_LABEL);
        dealerCardsLabel.setFont(new Font(BlackjackBetViewModel.FONT_NAME, Font.PLAIN, BlackjackBetViewModel.SUBTITLE_SIZE));
        dealerCardsLabel.setForeground(AppColors.YELLOW);
        this.dealerCardsPanel = new JPanel();
        dealerCardsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        dealerCardsPanel.add(dealerCardsLabel);
        dealerCardsPanel.setBackground(AppColors.DARK_GREEN);

        final BlackjackGameState initialState = blackjackGameViewModel.getState();
        final List<Image> initialPlayerCards = initialState.getPlayerCards();
        for (Image img : initialPlayerCards) {
            final ImageIcon imageIcon = new ImageIcon(img);
            final JLabel card = new JLabel(imageIcon);
            playerCardsPanel.add(card);
        }

        final List<Image> initialDealerCards = initialState.getDealerCards();
        for (Image img : initialDealerCards) {
            final ImageIcon imageIcon = new ImageIcon(img);
            final JLabel card = new JLabel(imageIcon);
            dealerCardsPanel.add(card);
        }

        final JPanel playerScorePanel = new JPanel();
        this.playerScore = new JLabel(BlackjackGameViewModel.SCORE_LABEL + initialState.getPlayerScore());
        playerScore.setFont(new Font(BlackjackBetViewModel.FONT_NAME, Font.PLAIN, BlackjackBetViewModel.SUBTITLE_SIZE));
        playerScore.setForeground(AppColors.YELLOW);
        playerScorePanel.add(playerScore);
        playerScorePanel.setBackground(AppColors.DARK_GREEN);
        playerScorePanel.setForeground(AppColors.YELLOW);


        final JPanel dealerScorePanel = new JPanel();
        this.dealerScore = new JLabel(BlackjackGameViewModel.SCORE_LABEL + initialState.getDealerScore());
        dealerScore.setFont(new Font(BlackjackBetViewModel.FONT_NAME, Font.PLAIN, BlackjackBetViewModel.SUBTITLE_SIZE));
        dealerScore.setForeground(AppColors.YELLOW);

        dealerScorePanel.add(dealerScore);
        dealerScorePanel.setBackground(AppColors.DARK_GREEN);
        dealerScorePanel.setForeground(AppColors.YELLOW);

        buttons = new JPanel();
        this.hit = createStyledButton(BlackjackGameViewModel.HIT_LABEL, AppColors.DARK_RED);
        this.stand = createStyledButton(BlackjackGameViewModel.STAND_LABEL, AppColors.DARK_RED);
        buttons.setBackground(AppColors.DARK_GREEN);


        final JButton playAgain = createStyledButton(blackjackGameViewModel.PLAY_AGAIN_LABEL, AppColors.YELLOW);
        playAgain.setBackground(AppColors.DARK_GREEN);
        playAgain.setAlignmentX(Component.CENTER_ALIGNMENT);
        playAgainPanel = new JPanel();
        playAgainPanel.setBackground(AppColors.DARK_GREEN);
        playAgainPanel.add(playAgain);

        buttons.add(hit);
        buttons.add(stand);


        hit.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final BlackjackGameState gameState = blackjackGameViewModel.getState();
                        if (evt.getSource().equals(hit) && gameState.getTurnState().equals("Player")) {

                            blackjackGameController.execute("Hit", 0, null, null);

                            if (gameState.getTurnState().equals("Dealer")) {
                                blackjackGameController.execute("Stand", 0, null, null);
                                blackjackGameController.execute("Stop",
                                        Integer.parseInt(gameState.getBetAmount()),
                                        gameState.getUser(), gameState.getTurnState());
                            } else if (gameState.getTurnState().equals("Lose")) {
                                blackjackGameController.execute("Stop",
                                        Integer.parseInt(gameState.getBetAmount()),
                                        gameState.getUser(), gameState.getTurnState());
                            }
                        }

                    }
                }
        );

        stand.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final BlackjackGameState gameState = blackjackGameViewModel.getState();
                        if (evt.getSource().equals(stand) && gameState.getTurnState().equals("Player")) {

                            blackjackGameController.execute("Stand", 0, null, null);
                            blackjackGameController.execute("Stop",
                                    Integer.parseInt(gameState.getBetAmount()),
                                    gameState.getUser(), gameState.getTurnState());

                        }
                    }
                }
        );

        playAgain.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final BlackjackGameState gameState = blackjackGameViewModel.getState();
                        if (evt.getSource().equals(playAgain) && !gameState.getTurnState().equals("Player")
                        && !gameState.getTurnState().equals("Dealer")) {

                            blackjackGameController.execute("Play Again", 0, null, null);
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(gameStatusLabel);
        this.add(playerCardsPanel);
        this.add(playerScorePanel);
        this.add(dealerCardsPanel);
        this.add(dealerScorePanel);
        this.add(buttons);
        this.add(playAgainPanel);
        playAgainPanel.setVisible(false);
        this.add(bottomPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final BlackjackGameState gameState = (BlackjackGameState) evt.getNewValue();
        setFields(gameState);
    }

    private void setFields(BlackjackGameState state) {

        updatePlayerHand(state);

        updateDealerHand(state);

        updateAllButtons(state);

        betAmountLabel.setText(BlackjackGameViewModel.BET_AMOUNT_LABEL + state.getBetAmount());

    }

    private void updateDealerHand(BlackjackGameState state) {
        if (state.hideDealerCard()) {
            dealerScore.setText(BlackjackGameViewModel.SCORE_LABEL + state.getDealerHiddenScore());

            this.dealerCardsPanel.removeAll();
            dealerCardsPanel.add(dealerCardsLabel);

            List<Image> dealerCards = state.getDealerCards();

            Image revealedCard = this.hiddenImage;
            if (!dealerCards.isEmpty()) {
                revealedCard = dealerCards.get(0);
            }

            final ImageIcon revealedCardIcon = new ImageIcon(revealedCard);
            final ImageIcon hiddenCardIcon = new ImageIcon(this.hiddenImage);

            final JLabel revealedCardLabel = new JLabel(revealedCardIcon);
            final JLabel hiddenCardLabel = new JLabel(hiddenCardIcon);

            dealerCardsPanel.add(revealedCardLabel);
            dealerCardsPanel.add(hiddenCardLabel);


        } else {
            dealerScore.setText(BlackjackGameViewModel.SCORE_LABEL + state.getDealerScore());

            this.dealerCardsPanel.removeAll();
            dealerCardsPanel.add(dealerCardsLabel);

            List<Image> dealerCards = state.getDealerCards();

            for (Image img : dealerCards) {
                final ImageIcon imageIcon = new ImageIcon(img);
                final JLabel card = new JLabel(imageIcon);
                dealerCardsPanel.add(card);
            }

        }

        dealerCardsPanel.revalidate();
        dealerCardsPanel.repaint();

    }

    private void updateAllButtons(BlackjackGameState state) {
        switch (state.getTurnState()) {
            case "Lose":
                gameStatusLabel.setText(BlackjackGameViewModel.LOSE_LABEL);
                gameStatusLabel.setForeground(AppColors.BRIGHT_RED);
                playAgainPanel.setVisible(true);
                buttons.setVisible(false);
                break;
            case "Win":
                gameStatusLabel.setText(BlackjackGameViewModel.WIN_LABEL);
                gameStatusLabel.setForeground(AppColors.BRIGHT_GREEN);
                playAgainPanel.setVisible(true);
                buttons.setVisible(false);
                break;
            case "Draw":
                gameStatusLabel.setText(BlackjackGameViewModel.DRAW_LABEL);
                gameStatusLabel.setForeground(AppColors.BRIGHT_BLUE);
                playAgainPanel.setVisible(true);
                buttons.setVisible(false);
                break;
            case "Dealer":
                gameStatusLabel.setText(BlackjackGameViewModel.DEALER_TURN_LABEL);
                gameStatusLabel.setForeground(AppColors.YELLOW);
                buttons.setVisible(false);
                playAgainPanel.setVisible(false);
                break;
            default:
                gameStatusLabel.setText(BlackjackGameViewModel.PLAYER_TURN_LABEL);
                gameStatusLabel.setForeground(AppColors.YELLOW);
                buttons.setVisible(true);
                playAgainPanel.setVisible(false);
                break;
        }

    }

    private void updatePlayerHand(BlackjackGameState state) {
        playerScore.setText(BlackjackGameViewModel.SCORE_LABEL + state.getPlayerScore());

        List<Image> playerCards = state.getPlayerCards();

        if (playerCards.isEmpty()) {
            this.playerCardsPanel.removeAll();
            playerCardsPanel.add(playerCardsLabel);

            final ImageIcon hiddenCardIcon = new ImageIcon(this.hiddenImage);
            final JLabel hiddenCardLabel = new JLabel(hiddenCardIcon);

            playerCardsPanel.add(hiddenCardLabel);
            playerCardsPanel.add(hiddenCardLabel);

        } else {
            this.playerCardsPanel.removeAll();
            playerCardsPanel.add(playerCardsLabel);

            for (Image img : playerCards) {
                final ImageIcon imageIcon = new ImageIcon(img);
                final JLabel card = new JLabel(imageIcon);
                playerCardsPanel.add(card);
            }

        }

        playerCardsPanel.revalidate();
        playerCardsPanel.repaint();

    }

    public String getViewName() {
        return viewName;
    }

    public void setBlackjackGameController(BlackjackGameController gameController) {
        this.blackjackGameController = gameController;
    }


    private Image loadCardHiddenImage() {
        Image image = null;

        try {
            final BufferedImage bufferedImage1 = ImageIO.read(new File("images/back-card.png"));
            image = bufferedImage1.getScaledInstance(
                    (int) Math.round(bufferedImage1.getWidth() * 0.13),
                    (int) Math.round(bufferedImage1.getHeight() * 0.13), Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;

    }

    private JLabel createTitleLabel() {
        final JLabel title = new JLabel(BlackjackGameViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font(BlackjackGameViewModel.FONT_NAME, Font.BOLD, BlackjackGameViewModel.TITLE_SIZE));
        title.setForeground(AppColors.YELLOW);
        title.setBackground(AppColors.DARK_RED);
        return title;
    }

    private JLabel createTurnLabel() {
        final JLabel turnLabel = new JLabel(BlackjackGameViewModel.PLAYER_TURN_LABEL);
        turnLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        turnLabel.setFont(new Font(BlackjackGameViewModel.FONT_NAME, Font.BOLD, BlackjackGameViewModel.MED_TITLE_SIZE));
        turnLabel.setForeground(AppColors.YELLOW);
        turnLabel.setBackground(AppColors.DARK_RED);
        return turnLabel;
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
}
