package use_case.Over_Under.play;

import entity.*;
import org.json.JSONObject;

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
        if (gameInputData == null || gameInputData.getUserName() == null) {
            userPresenter.prepareFailView("Invalid input data.");
            return;}
        OverUnderGame overUnderGame = this.game.create();
        if (!userDataAccessObject.hasDeck()) {
            userDataAccessObject.createNewDeck();
            overUnderGame.setDeckId(userDataAccessObject.getDeckID());
        } else if (!overUnderGame.hasDeck()) {
            overUnderGame.setDeckId(userDataAccessObject.getDeckID());}
        if (overUnderGame.getCurrentCard() == null) {
            AbstractCard currentCard = userDataAccessObject.drawCard(overUnderGame.getDeckId());
            if (currentCard == null) {
                userPresenter.prepareFailView("Not enough cards left in the deck.");
                return;}
            overUnderGame.setCurrentCard(currentCard);
        }AbstractCard nextCard = userDataAccessObject.drawCard(overUnderGame.getDeckId());
        if (nextCard == null) {
            userPresenter.prepareFailView("Not enough cards left in the deck.");
            return;}
        overUnderGame.setNextCard(nextCard);
        User user = userPlayDataAccessObject.get(gameInputData.getUserName());
        if (user == null) {
            userPresenter.prepareFailView("User not found.");
            return;}
        boolean isGuessCorrect = overUnderGame.evaluateGuess(gameInputData.getIsHigher());
        JSONObject userJson = user.getInfo();
        OverUnderPlayOutputData outputData = new OverUnderPlayOutputData(
                isGuessCorrect, isGuessCorrect ? "You Win!" : "You Lose!",
                overUnderGame.getCurrentCardImage(),
                overUnderGame.getNextCardImage(),
                false);
        if (isGuessCorrect) {
            userJson.put("wins", userJson.optInt("wins", 0) + 1);
            int newBalance = user.getBalance() + (userPlayDataAccessObject.getBet() * 2);
            userJson.put("balance", newBalance);
            userPresenter.prepareSuccessView(outputData);} else {
            userJson.put("losses", userJson.optInt("losses", 0) + 1);
            userJson.put("balance", user.getBalance() - userPlayDataAccessObject.getBet());
            userPresenter.prepareFailView(outputData.getResultMessage());
        }
        userJson.put("games", userJson.optInt("games", 0) + 1);
        User updatedUser = userFactory.create(user.getName(), user.getPassword(), userJson);
        userPlayDataAccessObject.saveNew(updatedUser, userJson);
    }

    public void switchToGameMenuView() {
        userPresenter.switchToGameMenuView();
    }
}
