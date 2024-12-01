package interface_adapter.statistics;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Signup View.
 */
public class StatisticsViewModel extends ViewModel<StatisticsState> {

    public static final String TITLE_LABEL = "MY STATS";
    public static final String RETURN_MENU_BUTTON_LABEL = "RETURN TO MENU";
    public static final String LOGOUT_BUTTON_LABEL = "LOGOUT";
    public static final String LEADERBOARD_BUTTON_LABEL = "LEADERBOARD";
    public static final String CHANGE_PASS_LABEL = "Change Password:";
    public static final String CHANGE_PASS_BUTTON_LABEL = "CHANGE";

    public StatisticsViewModel() {
        super("stats");
        setState(new StatisticsState());
    }

}
