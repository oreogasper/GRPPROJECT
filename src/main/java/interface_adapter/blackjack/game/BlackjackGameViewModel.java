package interface_adapter.blackjack.game;

import interface_adapter.ViewModel;


/**
 * The ViewModel for the Blackjack Game View.
 */
public class BlackjackGameViewModel extends ViewModel<BlackjackGameState> {
    public static final String TITLE_LABEL = "Play Blackjack!";
    public static final String PLAYER_HAND_LABEL = "Your Hand:";
    public static final String DEALER_HAND_LABEL = "Dealer Hand:";
    public static final String HIT_LABEL = "Hit";
    public static final String STAND_LABEL = "Stand";
    public static final String SCORE_LABEL = "Score:";
    public static final String PLAYER_TURN_LABEL = "Your Turn";
    public static final String DEALER_TURN_LABEL = "Dealers Turn";
    public static final String WIN_LABEL = "You Win!";
    public static final String LOSE_LABEL = "You Lose!";
    public static final String DRAW_LABEL = "Draw!";
    public static final String PLAY_AGAIN_LABEL = "Play Again";

    public BlackjackGameViewModel() {
        super("Blackjack Game");
        setState(new BlackjackGameState());
    }


}
