package use_case.Over_Under.play;

import entity.*;
import org.json.JSONObject;
import use_case.Over_Under.play.OverUnderPlayDataAccessInterface;
import use_case.Over_Under.play.OverUnderPlayOutputBoundary;

/**
 * The Over/Under Play Interactor.
 */
public class OverUnderPlayInteractor implements OverUnderPlayInputBoundary {
    private final OverUnderPlayOutputBoundary userPresenter;
    private final OverUnderPlayDataAccessInterface userDataAccessObject;
    private final UserFactory userFactory;
    private final OverUnderGameFactory game;
    private final OverUnderUserPlayDataAccessInterface userPlayDataAccessObject;

    public OverUnderPlayInteractor(OverUnderPlayDataAccessInterface userDataAccessObject,
                                   OverUnderPlayOutputBoundary userPresenter,
                                   UserFactory userFactory,
                                   OverUnderGameFactory game,
                                   OverUnderUserPlayDataAccessInterface userPlayDataAccessObject) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
        this.userFactory = userFactory;
        this.game = game;
        this.userPlayDataAccessObject = userPlayDataAccessObject;
    }

    @Override
    public void execute(OverUnderPlayInputData gameInputData) {
        // Validate input
        if (gameInputData == null || gameInputData.getUserName() == null) {
            userPresenter.prepareFailView("Invalid input data.");
            return;
        }
        if (!userDataAccessObject.hasDeck()){
            userDataAccessObject.createNewDeck();
        }
        // Users bet
        final boolean isHigherBet = gameInputData.getIsHigher();

        // Current card, card to be compared to
        final AbstractCard currentCard = userDataAccessObject.drawCard(userDataAccessObject.getDeckID());
        final AbstractCard nextCard = userDataAccessObject.drawCard(userDataAccessObject.getDeckID());
        if (currentCard == null || nextCard == null) {
            userPresenter.prepareFailView("Not enough cards left in the deck.");
            return;
        }

        // Fetch user data
        final User user = userPlayDataAccessObject.get(gameInputData.getUserName());
        if (user == null) {
            userPresenter.prepareFailView("User not found.");
            return;
        }

        // Check if the user's guess is correct
        boolean isGuessCorrect = isHigherBet
                ? nextCard.getRank() > currentCard.getRank()
                : nextCard.getRank() < currentCard.getRank();

        // Update user's stats
        JSONObject userJson = user.getInfo();
        if (!isGuessCorrect) {
            // Increment wrong guesses if the guess was incorrect
            int wrongGuesses = userJson.optInt("wrongGuesses", 0);
            userJson.put("wrongGuesses", wrongGuesses + 1);
        }

        // Update games played
        int totalGames = userJson.optInt("games", 0);
        userJson.put("games", totalGames + 1);

        // Save updated user data
        User updatedUser = userFactory.create(user.getName(), user.getPassword(), userJson);
        userPlayDataAccessObject.saveNew(updatedUser, userJson);

        // Notify the presenter with the result
        OverUnderPlayOutputData outputData = new OverUnderPlayOutputData(isGuessCorrect, "You Lose!" );
        if (isGuessCorrect) {
            userPresenter.prepareSuccessView(outputData);
            userJson.put("losses", user.getLosses() + 1);
            userJson.put("balance", (userPlayDataAccessObject.getBet() + user.getBalance()) * 10);
        } else {
            userPresenter.prepareFailView(outputData.getError());
            userJson.put("wins", user.getWins() + 1);
        }
    }

    public void switchToGameMenuView() {
        userPresenter.switchToGameMenuView();
    }
}
