package interface_adapter.und_ovr.play;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Over/Under Play View.
 */
public class OverUnderPlayViewModel extends ViewModel<OverUnderPlayState> {
    // UI Labels
    public static final String TITLE_LABEL = "Over/Under Game";
    public static final String CURRENT_CARD_LABEL = "Current Card:";
    public static final String HIGHER_BUTTON_LABEL = "Higher";
    public static final String LOWER_BUTTON_LABEL = "Lower";
    public static final String WRONG_GUESSES_LABEL = "Wrong Guesses:";
    public static final String GAME_OVER_LABEL = "Game Over!";

    // Constructor
    public OverUnderPlayViewModel() {
        super("OverUnder play");
        setState(new OverUnderPlayState());
    }
}
