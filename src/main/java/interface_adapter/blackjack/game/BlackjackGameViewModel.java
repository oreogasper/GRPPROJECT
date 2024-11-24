package interface_adapter.blackjack.game;

import interface_adapter.ViewModel;
import view.BlackjackGameView;

/**
 * The ViewModel for the Blackjack Game View.
 */
public class BlackjackGameViewModel extends ViewModel<BlackjackGameState> {
    public static final String TITLE_LABEL = "Play Blackjack!";
    public static final String PLAYER_HAND_LABEL = "Your Hand:";
    public static final String HIT_LABEL = "Hit";
    public static final String STAND_LABEL = "Stand";

    public BlackjackGameViewModel() {
        super("Blackjack Game");
        setState(new BlackjackGameState());
    }

    public void addPropertyChangeListener(BlackjackGameView blackjackGameView) {

    }
}
