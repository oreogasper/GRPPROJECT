package use_case.Over_Under.play;

import entity.PlayingCard;
import entity.PlayingDeck;
import org.json.JSONObject;
import entity.User;
import entity.UserFactory;
import use_case.Over_Under.play.OverUnderPlayDataAccessInterface;
import use_case.Over_Under.play.OverUnderPlayOutputBoundary;

import javax.smartcardio.Card;
import java.util.List;

/**
 * The Over/Under Play Interactor.
 */
public class OverUnderPlayInteractor implements OverUnderPlayInputBoundary {
    private final OverUnderPlayOutputBoundary userPresenter;
    private final OverUnderPlayDataAccessInterface userDataAccessObject;
    private final UserFactory userFactory;
    private final PlayingDeck playingDeck;
    private static final int BONUS_RATE = 18;

    public OverUnderPlayInteractor(OverUnderPlayDataAccessInterface userDataAccessObject,
                                   OverUnderPlayOutputBoundary userPresenter,
                                   UserFactory userFactory,
                                   PlayingDeck playingDeck) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
        this.userFactory = userFactory;
        this.playingDeck = playingDeck;
    }

    @@Override
    public void execute(OverUnderPlayInputData betInputData) {
        final boolean bet = betInputData.getIsHigher(); // User's guess: true for higher, false for lower
        final int betAmount = betInputData.getBetAmount(); // Amount being bet by the user

        // Draw the current and next cards from the deck
        final PlayingCard currentCard = this.playingDeck.dealCard();
        final PlayingCard nextCard = this.playingDeck.dealCard();

        // Ensure cards are available in the deck
        if (currentCard == null || nextCard == null) {
            userPresenter.prepareFailView("Not enough cards left in the deck.");
            return;
        }

        // Fetch the user data from the database
        final User user = userDataAccessObject.get(betInputData.getUserName());
        final JSONObject userJson = user.getInfo();
        int userBalance = user.getBalance(); // Current user balance


        boolean isBetCorrect = betCorrect(bet, currentCard, nextCard);

        // Update the user data based on the result
        if (isBetCorrect) {
            // Correct guess: User wins
            userBalance += betAmount * BONUS_RATE; // Reward the user with bonus multiplier

            userJson.put("wins", user.getWins() + 1);
            userJson.put("games", user.getGames() + 1);
        } else {
            // Incorrect guess: User loses the bet amount
            userJson.put("games", user.getGames() + 1); // Update games played
        }

        // Update user balance in the JSON
        userJson.put("balance", userBalance);

        // Save the updated user data to the data access object
        User updatedUser = userFactory.create(user.getName(), user.getPassword(), userJson);
        userDataAccessObject.saveNew(updatedUser, userJson);

        // Set the bet for the user in the system
        user.setBet(betAmount);
        userPresenter.setBet();

        // Notify the presenter with the result
        OverUnderPlayOutputData outputData = new OverUnderPlayOutputData(betAmount, isBetCorrect);
        if (isBetCorrect) {
            userPresenter.prepareSuccessView(outputData); // Correct guess, show success
        } else {
            userPresenter.prepareFailView(outputData); // Incorrect guess, show failure
        }
    }



    private boolean betCorrect(boolean bet, PlayingCard currentCard, PlayingCard nextCard) {

        return bet ? nextCard.getRank() > currentCard.getRank() : nextCard.getRank() < currentCard.getRank();
    }


    @Override
    public void switchToOverUnderGameView() {
        userPresenter.switchToOverUnderPlayView();
    }

    @Override
    public void switchToGameMenuView() {
        userPresenter.switchToGameMenuView();
    }
}
