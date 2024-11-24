package view;

import interface_adapter.und_ovr.GameStateManager;
import interface_adapter.und_ovr.OverUnderController;
import interface_adapter.und_ovr.OverUnderViewModel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements GameStateManager.GameStateListener {
    private final GameStateManager gameStateManager;
    private final CardLayout cardLayout;
    private final JPanel mainPanel;

    public MainFrame() {
        this.gameStateManager = new GameStateManager();
        this.gameStateManager.addGameStateListener(this);

        this.cardLayout = new CardLayout();
        this.mainPanel = new JPanel(cardLayout);

        // Set up the JFrame
        setTitle("Over Under Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Add views to the CardLayout
        addViews();

        // Add the main panel to the JFrame
        add(mainPanel);

        // Show the initial state
        gameStateManager.setCurrentState(GameStateManager.GameState.BETTING);
    }

    private void addViews() {
        // Create ViewModel and Controller for the OverUnderView
        OverUnderViewModel viewModel = new OverUnderViewModel();
        OverUnderView bettingView = new OverUnderView(viewModel);

        OverUnderController controller = new OverUnderController(gameStateManager);
        bettingView.setController(controller);

        // Add the Betting View to the main panel
        mainPanel.add(bettingView, GameStateManager.GameState.BETTING.name());

        // Add placeholder for the Playing View
        JPanel playingView = new JPanel();
        //playingView.add(new JLabel("Game Playing View"));
        mainPanel.add(playingView, GameStateManager.GameState.PLAYING.name());

        // Add placeholder for the Game Over View
        final JPanel gameOverView = new JPanel();
        gameOverView.add(new JLabel("Game Over View"));
        mainPanel.add(gameOverView, GameStateManager.GameState.GAME_OVER.name());
    }

    @Override
    public void onStateChanged(GameStateManager.GameState newState) {
        // Switch views based on the new state
        cardLayout.show(mainPanel, newState.name());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            final MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
