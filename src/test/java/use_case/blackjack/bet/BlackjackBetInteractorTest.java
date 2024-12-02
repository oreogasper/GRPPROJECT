package use_case.overunder.bet;

import org.junit.jupiter.api.Test;
import use_case.Over_Under.bet.*;


import static org.junit.jupiter.api.Assertions.*;

public class OverUnderBetInteractorTest {

    @Test
    void successTest() {
        // Create the input data, e.g., the amount of bet the user is placing
        OverUnderBetInputData inputData = new OverUnderBetInputData(50, true);  // 50 is the bet, 'true' for higher guess

        // Success presenter to handle the output
        OverUnderBetOutputBoundary successPresenter = new OverUnderBetOutputBoundary() {
            @Override
            public void prepareSuccessView(OverUnderBetOutputData resultData) {
                // Check that the output data contains the expected bet and result
                assertEquals(50, resultData.getBet());
                assertTrue(resultData.isGuessHigher()); // Checking if the guess was higher
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToOverUnderGameView() {
                // Optional: Switch to game view if needed
            }

            @Override
            public void switchToGameMenuView() {
                // Optional: Switch to the game menu if needed
            }
        };

        // Create the interactor and execute the input data
        OverUnderBetInputBoundary interactor = new OverUnderBetInteractor(successPresen
