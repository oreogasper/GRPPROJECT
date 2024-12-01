package use_case.gamemenu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the MenuInteractor class.
 */
class GameMenuInteractorTest {

    // A dummy implementation of the ShopOutputBoundary interface
    static class DummyGameMenuOutputBoundary implements GameMenuOutputBoundary {
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
        public void switchToGaunletView() {
            lastCalledMethod = "switchToGauntletView";
        }

        @Override
        public void switchToBlackjackView() {
            lastCalledMethod = "switchToBlackjackView";
        }

        public void switchToOverUnderView() {
            lastCalledMethod = "switchToOverUnderView";
        }

        @Override
        public void switchToLoginView() {
            lastCalledMethod = "switchToLoginView";
        }

        @Override
        public void switchToMenuView() {
            lastCalledMethod = "switchToMenuView";
        }

    }

    @Test
    void testSwitchToGaunletView() {
        DummyGameMenuOutputBoundary dummyPresenter = new DummyGameMenuOutputBoundary();
        GameMenuInteractor interactor = new GameMenuInteractor(dummyPresenter);

        interactor.switchToGaunletView();

        assertEquals("switchToGauntletView", dummyPresenter.lastCalledMethod,
                "switchToGaunletView should be called on the presenter");
    }

    @Test
    void testSwitchToBlackjackView() {
        DummyGameMenuOutputBoundary dummyPresenter = new DummyGameMenuOutputBoundary();
        GameMenuInteractor interactor = new GameMenuInteractor(dummyPresenter);

        interactor.switchToBlackjackView();

        assertEquals("switchToBlackjackView", dummyPresenter.lastCalledMethod,
                "switchToBlackjackView should be called on the presenter");
    }

    @Test
    void testSwitchToOverUnderView() {
        DummyGameMenuOutputBoundary dummyPresenter = new DummyGameMenuOutputBoundary();
        GameMenuInteractor interactor = new GameMenuInteractor(dummyPresenter);

        interactor.switchToOverUnderView();

        assertEquals("switchToOverUnderView", dummyPresenter.lastCalledMethod,
                "switchToOverUnderView should be called on the presenter");
    }

    @Test
    void testSwitchToLoginView() {
        DummyGameMenuOutputBoundary dummyPresenter = new DummyGameMenuOutputBoundary();
        GameMenuInteractor interactor = new GameMenuInteractor(dummyPresenter);

        interactor.switchToLoginView();

        assertEquals("switchToLoginView", dummyPresenter.lastCalledMethod,
                "switchToLoginView should be called on the presenter");
    }

    @Test
    void testSwitchToMenuView() {
        DummyGameMenuOutputBoundary dummyPresenter = new DummyGameMenuOutputBoundary();
        GameMenuInteractor interactor = new GameMenuInteractor(dummyPresenter);

        interactor.switchToMenuView();

        assertEquals("switchToMenuView", dummyPresenter.lastCalledMethod,
                "switchToMenuView should be called on the presenter");
    }
}
