package interface_adapter.und_ovr;

import java.util.ArrayList;
import java.util.List;

public class GameStateManager {

    private GameState currentState;
    private final List<GameStateListener> listeners = new ArrayList<>();

    // Default constructor
    public GameStateManager() {
        this.currentState = GameState.BETTING; // Set initial state
    }

    public void setCurrentState(GameState newState) {
        if (newState != currentState) {
            this.currentState = newState;
            System.out.println("Switched to state: " + newState);
            notifyListeners(newState);
        }
    }

    public GameState getCurrentState() {
        return currentState;
    }

    // Register listeners for state changes
    public void addGameStateListener(GameStateListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners(GameState newState) {
        for (GameStateListener listener : listeners) {
            listener.onStateChanged(newState);
        }
    }

    // Define the states of the game
    public enum GameState {
        BETTING,
        PLAYING,
        GAME_OVER
    }

    // Listener interface for observing state changes
    public interface GameStateListener {
        void onStateChanged(GameState newState);
    }
}
