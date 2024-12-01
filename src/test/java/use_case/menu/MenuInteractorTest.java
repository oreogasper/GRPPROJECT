package use_case.menu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the MenuInteractor class.
 */
class MenuInteractorTest {

    // A dummy implementation of the ShopOutputBoundary interface
    static class DummyMenuOutputBoundary implements MenuOutputBoundary {
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

        @Override
        public void switchToGameMenuView() {
            lastCalledMethod = "switchToGameMenuView";
        }

        @Override
        public void switchToShopView() {
            lastCalledMethod = "switchToShopView";
        }

    }

    @Test
    void testSwitchToStatisticsView() {
        DummyMenuOutputBoundary dummyPresenter = new DummyMenuOutputBoundary();
        MenuInteractor interactor = new MenuInteractor(dummyPresenter);

        interactor.switchToStatisticsView();

        assertEquals("switchToStatisticsView", dummyPresenter.lastCalledMethod,
                "switchToStatisticsView should be called on the presenter");
    }

    @Test
    void testSwitchToGameMenuView() {
        DummyMenuOutputBoundary dummyPresenter = new DummyMenuOutputBoundary();
        MenuInteractor interactor = new MenuInteractor(dummyPresenter);

        interactor.switchToGameMenuView();

        assertEquals("switchToGameMenuView", dummyPresenter.lastCalledMethod,
                "switchToGameMenuView should be called on the presenter");
    }

    @Test
    void testSwitchToShopView() {
        DummyMenuOutputBoundary dummyPresenter = new DummyMenuOutputBoundary();
        MenuInteractor interactor = new MenuInteractor(dummyPresenter);

        interactor.switchToShopView();

        assertEquals("switchToShopView", dummyPresenter.lastCalledMethod,
                "switchToShopView should be called on the presenter");
    }
}
