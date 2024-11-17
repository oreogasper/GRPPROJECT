package interface_adapter.gaunlet.bet;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Gaunlet Bet View.
 */
public class GaunletBetViewModel extends ViewModel<GaunletBetState> {
    public static final String TITLE_LABEL = "ENTER YOUR BET";
    public static final String BET_LABEL = "Bet amount";
    public static final String CONTINUE_BUTTON_LABEL = "continue";
    public static final String BACK_BUTTON_LABEL = "back";

    public GaunletBetViewModel() {
        super("Gaunlet bet");
        setState(new GaunletBetState());
    }

}

