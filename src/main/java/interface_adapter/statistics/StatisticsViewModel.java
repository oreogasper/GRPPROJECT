package interface_adapter.statistics;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Signup View.
 */
public class StatisticsViewModel extends ViewModel<StatisticsState> {

    public static final String TITLE_LABEL = "MY STATS";
    public static final String USERNAME_LABEL = "Choose username";
    public static final String PASSWORD_LABEL = "Choose password";
    public static final String TOKENS_LABEL = "MONEYYY";
    public static final String WIN_LABEL = "WINSIWINSIWINS";

    public static final String LOSSES_LABEL = "LOSSESBOOOO";

    public static final String GAMES_LABEL = "GAMES TOTAL AHAHA";

    public static final String WINPERCENTAGE_LABEL = "WIN %";
    public static final String RETURN_MENU_BUTTON_LABEL = "Return to Menu";

    public static final String TO_LEADERBOARD_BUTTON_LABEL = "LEADERBOARD BUTTON WIP";

    public StatisticsViewModel() {
        super("stats");
        setState(new StatisticsState());
    }

}
