package interface_adapter.blackjack.game;

import interface_adapter.ViewModel;


/**
 * The ViewModel for the Blackjack Game View.
 */
public class BlackjackGameViewModel extends ViewModel<BlackjackGameState> {
    public static final String TITLE_LABEL = "BLACKJACK";
    public static final String PLAYER_HAND_LABEL = "YOUR HAND: ";
    public static final String DEALER_HAND_LABEL = "DEALER'S HAND:";
    public static final String HIT_LABEL = "HIT";
    public static final String STAND_LABEL = "STAND";
    public static final String SCORE_LABEL = "Score:";
    public static final String PLAYER_TURN_LABEL = "Your Turn";
    public static final String DEALER_TURN_LABEL = "Dealer's Turn";
    public static final String WIN_LABEL = "YOU WIN!";
    public static final String LOSE_LABEL = "YOU LOSE!";
    public static final String DRAW_LABEL = "DRAW!";
    public static final String PLAY_AGAIN_LABEL = "PLAY AGAIN";
    public static final String BET_AMOUNT_LABEL = "Bet Amount:";
    public static final String FONT_NAME = "Serif";
    public static final int TITLE_SIZE = 35;
    public static final int SUBTITLE_SIZE = 20;
    public static final int MED_TITLE_SIZE = 30;

    public BlackjackGameViewModel() {
        super("Blackjack Game");
        setState(new BlackjackGameState());
    }


}
