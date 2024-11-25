package interface_adapter.statistics;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Signup View.
 */
public class StatisticsViewModel extends ViewModel<StatisticsState> {

    public static final String TITLE_LABEL = "MY STATS";
    public static final String RETURN_MENU_BUTTON_LABEL = "Return to Menu";

    public static final String TO_LEADERBOARD_BUTTON_LABEL = "LEADERBOARD BUTTON WIP";

    public StatisticsViewModel() {
        super("stats");
        setState(new StatisticsState());
    }

}
