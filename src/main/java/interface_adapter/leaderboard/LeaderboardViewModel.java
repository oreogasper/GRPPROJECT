package interface_adapter.leaderboard;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Leaderboard View.
 */
public class LeaderboardViewModel extends ViewModel<LeaderboardState> {

    public static final String TITLE_LABEL = "MY LEADERBOARD";
    public static final String RETURN_STATS_BUTTON_LABEL = "Return to Statistics";

    public static final String REMOVE_FRIENDS = "REMOVE ALL FRIENDS";

    public LeaderboardViewModel() {
        super("leaderboard");
        setState(new LeaderboardState());
    }

}
