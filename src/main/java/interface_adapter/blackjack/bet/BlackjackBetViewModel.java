package interface_adapter.blackjack.bet;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Blackjack Bet View.
 */
public class BlackjackBetViewModel extends ViewModel<BlackjackBetState> {
    public static final String TITLE_LABEL = "PLACE YOUR BET";
    public static final String BET_LABEL = "Bet amount";
    public static final String CONTINUE_BUTTON_LABEL = "continue";
    public static final String BACK_BUTTON_LABEL = "back";

    public BlackjackBetViewModel() {
        super("Blackjack bet");
        setState(new BlackjackBetState());
    }
}
