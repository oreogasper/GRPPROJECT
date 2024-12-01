package use_case.statistics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatisticsInteractorTest {
    // A dummy implementation of the ShopOutputBoundary interface
    static class DummyStatisticsOutputBoundary implements StatisticsOutputBoundary {
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
        public void switchToMenuView() {
            lastCalledMethod = "switchToMenuView";
        }

        @Override
        public void switchToLeaderboardView() {
            lastCalledMethod = "switchToLeaderboardView";
        }

    }

    @Test
    void testSwitchToMenuView() {
        StatisticsInteractorTest.DummyStatisticsOutputBoundary dummyPresenter
                = new StatisticsInteractorTest.DummyStatisticsOutputBoundary();
        StatisticsInteractor interactor = new StatisticsInteractor(dummyPresenter);

        interactor.switchToMenuView();

        assertEquals("switchToMenuView", dummyPresenter.lastCalledMethod,
                "switchToMenuView should be called on the presenter");
    }

    @Test
    void testSwitchToLeaderboardView() {
        StatisticsInteractorTest.DummyStatisticsOutputBoundary dummyPresenter
                = new StatisticsInteractorTest.DummyStatisticsOutputBoundary();
        StatisticsInteractor interactor = new StatisticsInteractor(dummyPresenter);

        interactor.switchToLeaderboardView();

        assertEquals("switchToLeaderboardView", dummyPresenter.lastCalledMethod,
                "switchToLeaderboardView should be called on the presenter");
    }

}
