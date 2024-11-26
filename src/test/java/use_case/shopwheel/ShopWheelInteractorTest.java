package use_case.shopwheel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShopWheelInteractorTest {

    private ShopWheelInteractor interactor;
    private StubShopWheelOutputBoundary stubOutputBoundary;

    @BeforeEach
    void setUp() {
        stubOutputBoundary = new StubShopWheelOutputBoundary();
        interactor = new ShopWheelInteractor(stubOutputBoundary, null, null);
    }

    @Test
    void testSwitchToShopView() {
        interactor.switchToShopView();
        assertEquals(1, stubOutputBoundary.shopViewSwitchCount, "Switch to Shop View should be called once.");
    }

    @Test
    void testSpinWheelGeneratesValidPrizeAndAngle() {
        // Simulates spinning the wheel
        interactor.spinWheel(0);
        assertTrue(stubOutputBoundary.lastAngle >= 0 && stubOutputBoundary.lastAngle < 360,
                "The wheel angle should be between 0 and 360 degrees.");
        assertTrue(ShopWheelInteractor.SEGMENT_TO_PRIZE.containsValue(stubOutputBoundary.lastPrize),
                "The prize should match one of the predefined segment prizes.");
    }

    @Test
    void testTooEarlyTriggersPresenter() {
        interactor.tooEarly();
        assertEquals(1, stubOutputBoundary.tooEarlyCount, "Too early should be triggered once.");
    }

    // Stub implementation
    static class StubShopWheelOutputBoundary implements ShopWheelOutputBoundary {
        int shopViewSwitchCount = 0;
        int tooEarlyCount = 0;
        int lastAngle = -1;
        int lastPrize = -1;
        Set<Integer> allPrizes = new HashSet<>();

        @Override
        public void prepareSuccessView() {
            // Unnecessary for testing.
        }

        @Override
        public void prepareFailView(String alert) {
            // Unnecessary for testing.
        }

        @Override
        public void switchToShopView() {
            shopViewSwitchCount++;
        }

        @Override
        public void spinWheel(int targetAngle, int prize) {
            lastAngle = targetAngle;
            lastPrize = prize;
            allPrizes.add(prize);
        }

        @Override
        public void tooEarly() {
            tooEarlyCount++;
        }

        @Override
        public void givePrize(int prize) {
            // Not testing this method.
        }
    }
}
