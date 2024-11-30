package interface_adapter.remove_friend;

import entity.User;
import interface_adapter.leaderboard.LeaderboardState;
import interface_adapter.leaderboard.LeaderboardViewModel;
import use_case.add_friend.AddFriendOutputBoundary;
import use_case.add_friend.AddFriendOutputData;
import use_case.remove_friend.RemoveFriendOutputBoundary;
import use_case.remove_friend.RemoveFriendOutputData;

/**
 * The Presenter for the Add Friend Use Case.
 */

public class RemoveFriendPresenter implements RemoveFriendOutputBoundary {

    private final LeaderboardViewModel leaderboardViewModel;

    public RemoveFriendPresenter(LeaderboardViewModel leaderboardViewModel) {
        this.leaderboardViewModel = leaderboardViewModel;
    }

    @Override
    public void prepareSuccessView(RemoveFriendOutputData outputData) {
        final LeaderboardState leaderboardState = leaderboardViewModel.getState();
        leaderboardState.setUser(outputData.getYOUser());
        leaderboardViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
    }
}
