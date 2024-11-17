package interface_adapter.gaunlet.guess;

import interface_adapter.ViewModel;
import view.GaunletGuessView;

/**
 * The ViewModel for the Gaunlet Guess View.
 */
public class GaunletGuessViewModel extends ViewModel<GaunletGuessState> {
    public static final String TITLE_LABEL = "Make Your Bets";
    public static final String COIN_LABEL = "Choose username";
    public static final String DICE_LABEL = "Choose password";
    public static final String RPS_LABEL = "Enter password again";

    public static final String CONTINUE_BUTTON_LABEL = "continue to results";

    public GaunletGuessViewModel() {
        super("gaunlet guess");
        setState(new GaunletGuessState());
    }

    public void addPropertyChangeListener(GaunletGuessView gaunletGuessView) {

    }
}
