package use_case.leaderboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeaderboardInteractorTest {
    // A dummy implementation of the ShopOutputBoundary interface
    static class DummyLeaderboardOutputBoundary implements LeaderboardOutputBoundary {
        String lastCalledMethod = null;

        @Override
        public void prepareSuccessView() {
            // Unnecessary for testing.
        }

        @Override
        public void prepareFailView(String response) {
            //Unnecessary for testing.
        }

        @Override
        public void switchToStatisticsView() {
            lastCalledMethod = "switchToStatisticsView";
        }


    }

    @Test
    void testSwitchToMenuView() {
        LeaderboardInteractorTest.DummyLeaderboardOutputBoundary dummyPresenter
                = new LeaderboardInteractorTest.DummyLeaderboardOutputBoundary();
        LeaderboardInteractor interactor = new LeaderboardInteractor(dummyPresenter);

        interactor.switchToStatisticsView();

        assertEquals("switchToStatisticsView", dummyPresenter.lastCalledMethod,
                "switchToStatisticsView should be called on the presenter");
    }
}
