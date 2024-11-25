package interface_adapter.add_friend;

import entity.User;
import interface_adapter.gamemenu.GameMenuState;
import interface_adapter.gaunlet.guess.GaunletGuessState;
import interface_adapter.leaderboard.LeaderboardState;
import interface_adapter.leaderboard.LeaderboardViewModel;
import use_case.add_friend.AddFriendOutputBoundary;
import use_case.add_friend.AddFriendOutputData;

/**
 * The Presenter for the Add Friend Use Case.
 */
public class AddFriendPresenter implements AddFriendOutputBoundary {

    private final LeaderboardViewModel leaderboardViewModel;

    public AddFriendPresenter(LeaderboardViewModel leaderboardViewModel) {
        this.leaderboardViewModel = leaderboardViewModel;
    }

    @Override
    public void prepareSuccessView(AddFriendOutputData outputData) {
        // On success, switch to the gauntlet guess view when implemented
        final LeaderboardState leaderboardState = leaderboardViewModel.getState();
        final User currentUser = leaderboardState.getUser();
        leaderboardState.setUser(outputData.getFriend());
        leaderboardViewModel.firePropertyChanged("friend");
        leaderboardState.setUser(currentUser);
        leaderboardViewModel.firePropertyChanged("return");

    }

    @Override
    public void prepareFailView(String error) {
    }
}
