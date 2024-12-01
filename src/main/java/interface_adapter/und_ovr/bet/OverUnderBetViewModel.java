package interface_adapter.und_ovr.bet;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the OverUnder Bet View.
 */
public class OverUnderBetViewModel extends ViewModel<OverUnderBetState> {
    public static final String TITLE_LABEL = "PLACE YOUR BET";
    public static final String BET_LABEL = "Bet amount";
    public static final String CONTINUE_BUTTON_LABEL = "continue";
    public static final String BACK_BUTTON_LABEL = "back";

    public OverUnderBetViewModel() {
        super("OverUnder bet");
        setState(new OverUnderBetState());
    }
}
