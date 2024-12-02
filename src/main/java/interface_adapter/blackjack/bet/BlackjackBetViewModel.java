package interface_adapter.blackjack.bet;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Blackjack Bet View.
 */
public class BlackjackBetViewModel extends ViewModel<BlackjackBetState> {
    public static final String TITLE_LABEL = "PLACE YOUR BET";
    public static final String BET_LABEL = "BET AMOUNT:";
    public static final String CONTINUE_BUTTON_LABEL = "CONTINUE";
    public static final String BACK_BUTTON_LABEL = "BACK";
    public static final String FONT_NAME = "Serif";
    public static final int TITLE_SIZE = 30;
    public static final int SUBTITLE_SIZE = 20;
    public static final int WIDTH_DIM = 50;
    public static final int HEIGHT_DIM = 50;

    public BlackjackBetViewModel() {
        super("Blackjack bet");
        setState(new BlackjackBetState());
    }
}
