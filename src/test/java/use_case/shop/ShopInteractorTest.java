package use_case.shop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the ShopInteractor class.
 */
class ShopInteractorTest {

    // A dummy implementation of the ShopOutputBoundary interface
    static class DummyShopOutputBoundary implements ShopOutputBoundary {
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
        public void switchToShopWheelView() {
            lastCalledMethod = "switchToShopWheelView";
        }

        @Override
        public void switchToShopButtonView() {
            lastCalledMethod = "switchToShopButtonView";
        }
    }

    @Test
    void testSwitchToMenuView() {
        DummyShopOutputBoundary dummyPresenter = new DummyShopOutputBoundary();
        ShopInteractor interactor = new ShopInteractor(dummyPresenter);

        interactor.switchToMenuView();

        assertEquals("switchToMenuView", dummyPresenter.lastCalledMethod,
                "switchToMenuView should be called on the presenter");
    }

    @Test
    void testSwitchToShopWheelView() {
        DummyShopOutputBoundary dummyPresenter = new DummyShopOutputBoundary();
        ShopInteractor interactor = new ShopInteractor(dummyPresenter);

        interactor.switchToShopWheelView();

        assertEquals("switchToShopWheelView", dummyPresenter.lastCalledMethod,
                "switchToShopWheelView should be called on the presenter");
    }

    @Test
    void testSwitchToShopButtonView() {
        DummyShopOutputBoundary dummyPresenter = new DummyShopOutputBoundary();
        ShopInteractor interactor = new ShopInteractor(dummyPresenter);

        interactor.switchToShopButtonView();

        assertEquals("switchToShopButtonView", dummyPresenter.lastCalledMethod,
                "switchToShopButtonView should be called on the presenter");
    }
}
