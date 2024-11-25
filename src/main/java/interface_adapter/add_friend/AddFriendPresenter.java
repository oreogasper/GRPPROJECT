package interface_adapter.add_friend;

import interface_adapter.statistics.StatisticsViewModel;
import use_case.add_friend.AddFriendOutputBoundary;
import use_case.add_friend.AddFriendOutputData;

/**
 * The Presenter for the Add Friend Use Case.
 */
public class AddFriendPresenter implements AddFriendOutputBoundary {

    private final StatisticsViewModel statisticsViewModel;

    public AddFriendPresenter(StatisticsViewModel statisticsViewModel) {
        this.statisticsViewModel = statisticsViewModel;
    }

    @Override
    public void prepareSuccessView(AddFriendOutputData outputData) {
        statisticsViewModel.firePropertyChanged("friend");

    }

    @Override
    public void prepareFailView(String error) {
    }
}
