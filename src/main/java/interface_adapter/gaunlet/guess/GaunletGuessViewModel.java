package interface_adapter.gaunlet.guess;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Gaunlet Guess View.
 */
public class GaunletGuessViewModel extends ViewModel<GaunletGuessState> {
    public static final String TITLE_LABEL = "Make Your Bets";
    public static final String COIN_LABEL = "Type heads or tails";
    public static final String DICE_LABEL = "Enter numerical dice roll (1-6)";
    public static final String RPS_LABEL = "Type rock, paper, or scissors";

    public static final String CONTINUE_BUTTON_LABEL = "GAME RESULTS";
    public static final int TITLE_SIZE = 20;
    public static final int SUBTITLE_SIZE = 15;
    public static final String FONT_NAME = "Serif";
    public static final int WIDTH_DIM = 180;
    public static final int HEIGHT_DIM = 50;

    public GaunletGuessViewModel() {
        super("gaunlet guess");
        setState(new GaunletGuessState());
    }

}
