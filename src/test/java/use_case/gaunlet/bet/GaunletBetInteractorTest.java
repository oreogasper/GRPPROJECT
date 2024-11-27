package use_case.gaunlet.bet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit tests for the GaunletBet interactor class.
 */
public class GaunletBetInteractorTest {
    @Test
    void successTest() {
        GaunletBetInputData inputData = new GaunletBetInputData(
                "valk", 11);

        // This creates a successPresenter that tests whether the test case is as we expect.
        GaunletBetOutputBoundary successPresenter = new GaunletBetOutputBoundary() {
            @Override
            public void prepareSuccessView(GaunletBetOutputData userBet) {
                // check that the output data contains the bet of the player.
                assertEquals(11, userBet.getBet());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToGaunletGuessView() {

            }

            @Override
            public void switchToGameMenuView() {

            }

            @Override
            public void setUserBet() {

            }
        }
    }
}
