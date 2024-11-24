package use_case.blackjack.game;

import org.junit.jupiter.api.Test;
import use_case.blackjack.bet.BlackjackBetOutputData;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BlackjackGameInteractorTest {
    @Test
    void successTest() {
        List<String> playerCards = new ArrayList<>();
        playerCards.add("7 Hearts");
        playerCards.add("6 Clubs");

        List<String> dealerCards = new ArrayList<>();
        dealerCards.add("K Hearts");
        dealerCards.add("J Clubs");

        BlackjackGameInputData inputData = new BlackjackGameInputData(playerCards, dealerCards);

        // This creates a successPresenter that tests whether the test case is as we expect.
        BlackjackGameOutputBoundary successPresenter = new BlackjackGameOutputBoundary() {


            @Override
            public void prepareSuccessView(BlackjackBetOutputData outputData) {

            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }


            @Override
            public void switchToGameMenuView() {

            }
        };

        BlackjackGameInputBoundary interactor = new BlackjackGameInteractor(successPresenter);
        interactor.execute(inputData);

    }
}
