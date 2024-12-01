package interface_adapter.gaunlet.bet;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Gaunlet Bet View.
 */
public class GaunletBetViewModel extends ViewModel<GaunletBetState> {
    public static final String TITLE_LABEL = "ENTER YOUR BET";
    public static final String BET_LABEL = "NUMERICAL BET AMOUNT:";
    public static final String CONTINUE_BUTTON_LABEL = "CONTINUE";
    public static final String BACK_BUTTON_LABEL = "BACK";
    public static final int TITLE_SIZE = 25;
    public static final int SUBTITLE_SIZE = 15;
    public static final String FONT_NAME = "Serif";
    public static final int WIDTH_DIM = 180;
    public static final int HEIGHT_DIM = 50;

    public GaunletBetViewModel() {
        super("Gaunlet bet");
        setState(new GaunletBetState());
    }

}

