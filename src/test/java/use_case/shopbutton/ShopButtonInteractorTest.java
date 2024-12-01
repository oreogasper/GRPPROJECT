package use_case.shopbutton;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShopButtonInteractorTest {

    private ShopButtonInteractor interactor;
    private StubShopButtonOutputBoundary stubOutputBoundary;

    @BeforeEach
    void setUp() {
        stubOutputBoundary = new StubShopButtonOutputBoundary();
        interactor = new ShopButtonInteractor(stubOutputBoundary, null, null);
    }

    @Test
    void testSwitchToShopView() {
        interactor.switchToShopView();
        assertEquals(1, stubOutputBoundary.shopViewSwitchCount, "Switch to Shop View should be called once.");
    }

    @Test
    void testButtonClickIncrementsClickCount() {
        interactor.buttonClick(5); // Simulates 5 previous clicks
        assertEquals(1, stubOutputBoundary.clickCount, "Click count should increment by 1.");
        assertEquals(0, stubOutputBoundary.tokenCount, "No tokens should be added if the requirement isn't met.");
    }

    @Test
    void testButtonClickAddsTokenAfterClickRequirement() {
        interactor.buttonClick(9); // Simulates 9 previous clicks; next click should add a token
        assertEquals(1, stubOutputBoundary.clickCount, "Click count should increment by 1.");
        assertEquals(1, stubOutputBoundary.tokenCount, "A token should be added after reaching the click requirement.");
    }

    // Stub implementation
    static class StubShopButtonOutputBoundary implements ShopButtonOutputBoundary {
        int shopViewSwitchCount = 0;
        int clickCount = 0;
        int tokenCount = 0;

        @Override
        public void prepareFailView(String alert) {
            //Unnecessary for testing.
        }

        @Override
        public void switchToShopView() {
            shopViewSwitchCount++;
        }

        @Override
        public void addClick() {
            clickCount++;
        }

        @Override
        public void addToken() {
            tokenCount++;
        }

        @Override
        public void prepareSuccessView() {
            // No action needed for these tests
        }
    }
}
