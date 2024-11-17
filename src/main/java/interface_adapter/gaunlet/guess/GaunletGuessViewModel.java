package interface_adapter.gaunlet.guess;

import interface_adapter.ViewModel;
import view.GaunletGuessView;

/**
 * The ViewModel for the Gaunlet Guess View.
 */
public class GaunletGuessViewModel extends ViewModel<GaunletGuessState> {
    public static final String TITLE_LABEL = "Make Your Bets";
    public static final String COIN_LABEL = "Type heads or tails";
    public static final String DICE_LABEL = "Enter dice roll (1-6)";
    public static final String RPS_LABEL = "Type rock, paper, or scissors";

    public static final String CONTINUE_BUTTON_LABEL = "continue to results";

    public GaunletGuessViewModel() {
        super("gaunlet guess");
        setState(new GaunletGuessState());
    }

    public void addPropertyChangeListener(GaunletGuessView gaunletGuessView) {

    }
}
