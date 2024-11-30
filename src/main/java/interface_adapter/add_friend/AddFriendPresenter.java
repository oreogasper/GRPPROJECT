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
        final LeaderboardState leaderboardState = leaderboardViewModel.getState();
        leaderboardState.setError(null);
        final User currentUser = leaderboardState.getUser();
        leaderboardState.setUser(outputData.getFriend());
        leaderboardViewModel.firePropertyChanged("friend");
        leaderboardState.setUser(currentUser);
    }

    @Override
    public void prepareFailView(String error) {
        final LeaderboardState leaderboardState = leaderboardViewModel.getState();
        leaderboardState.setError(error);
        leaderboardViewModel.firePropertyChanged("friend");
    }
}
