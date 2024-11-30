package use_case.gaunlet.guess;

import org.json.JSONObject;

import entity.GaunletGame;
import entity.GaunletGameFactory;
import entity.User;
import entity.UserFactory;

/**
 * The Gaunlet Guess Interactor.
 */
public class GaunletGuessInteractor implements GaunletGuessInputBoundary {
    private static final int BONUS_RATE = 36;
    private final GaunletGuessOutputBoundary userPresenter;
    private final GaunletGameFactory game;
    private final GaunletGuessUserDataAccessInterface userDataAccessObject;
    private final UserFactory userFactory;

    public GaunletGuessInteractor(
            GaunletGuessOutputBoundary gaunletGuessOutputBoundary,
            GaunletGameFactory gaunletgame,
            GaunletGuessUserDataAccessInterface userDataAccessObject,
            UserFactory userFactory) {
        this.userPresenter = gaunletGuessOutputBoundary;
        this.game = gaunletgame;
        this.userDataAccessObject = userDataAccessObject;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(GaunletGuessInputData gaunletGuessInputData) {

        final String coinGuess = gaunletGuessInputData.getCoinFlip();
        final String diceGuess = gaunletGuessInputData.getDice();
        final String rpsGuess = gaunletGuessInputData.getRps();

        // checks that imputs in each field are valid - could not complete implementation on time, to be continued...
        // if (!isValidCoinFlip(coinGuess)) {
        // userPresenter.prepareFailView("Invalid coin guess. Please enter 'Heads' or 'Tails'.");
        // }
        // int dice = 0;
        // try {
        // dice = isValidDiceGuess(diceGuess);
        // }
        // catch (IllegalArgumentException evt) {
        // userPresenter.prepareFailView(evt.getMessage());
        // }

        // if (!isValidRpsGuess(rpsGuess)) {
        // userPresenter.prepareFailView("Invalid RPS guess. Please enter 'Rock', 'Paper', or 'Scissors'."
        // );
        // }

        final GaunletGame gaunletGame = game.create(coinGuess, Integer.parseInt(diceGuess), rpsGuess);
        final String actualCoinFlip = gaunletGame.flipCoin();
        final int actualDiceRoll = gaunletGame.rollDice();
        final String actualRpsOutcome = gaunletGame.getRpsOutcome();

        final boolean isWin = actualCoinFlip.equalsIgnoreCase(coinGuess)
                && actualDiceRoll == Integer.parseInt(diceGuess)
                && actualRpsOutcome.equalsIgnoreCase(rpsGuess);

        final User userr = userDataAccessObject.get(gaunletGuessInputData.getUsername());
        final JSONObject json = userr.getInfo();
        final int newBalance = (userDataAccessObject.getBet() + userr.getBalance()) * BONUS_RATE;

        if (isWin) {
            // balance x36 + bet amount
            json.put("balance", newBalance);
            json.put("wins", userr.getWins() + 1);
            json.put("games", userr.getGames() + 1);

            final User user = userFactory.create(userr.getName(), userr.getPassword(), json);
            userDataAccessObject.saveNew(user, json);
        }
        else {
            json.put("losses", userr.getLosses() + 1);
            json.put("games", userr.getGames() + 1);

            final User user = userFactory.create(userr.getName(), userr.getPassword(), json);
            userDataAccessObject.saveNew(user, json);
        }

        // Create output data and show result
        final GaunletGuessOutputData gaunletGuessOutputData = new GaunletGuessOutputData(
                coinGuess, diceGuess, rpsGuess, isWin, false);
        userPresenter.prepareSuccessView(gaunletGuessOutputData);

    }

    // Helpers to check coin, dice and rps value
    // private boolean isValidCoinFlip(String coinFlip) {
    // return "Heads".equalsIgnoreCase(coinFlip) || "Tails".equalsIgnoreCase(coinFlip);
    // }

    // private int isValidDiceGuess(String diceGuess) {
    // final int diceLower = 1;
    // final int diceHigher = 6;
    // try {
    // final int diceValue = Integer.parseInt(diceGuess);
    // if (diceValue < diceLower || diceValue > diceHigher) {
    // throw new IllegalArgumentException("Invalid dice guess. Please enter a number between 1 and 6.");
    // }
    // return diceValue;
    // }
    // catch (NumberFormatException evt) {
    // throw new IllegalArgumentException("Invalid dice guess. Please enter a valid number.");
    // }
    // }

    // private boolean isValidRpsGuess(String rpsGuess) {
    // return "Rock".equalsIgnoreCase(rpsGuess)
    // ||
    // "Paper".equalsIgnoreCase(rpsGuess)
    // ||
    // "Scissors".equalsIgnoreCase(rpsGuess);
    // }

    @Override
    public void switchToLoginView() {
        userPresenter.switchToLoginView();
    }
}
