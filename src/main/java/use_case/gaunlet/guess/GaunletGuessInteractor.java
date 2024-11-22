package use_case.gaunlet.guess;

import entity.Game;
import entity.GaunletGame;
import entity.GaunletGameFactory;

/**
 * The Gaunlet Guess Interactor.
 */
public class GaunletGuessInteractor implements GaunletGuessInputBoundary {
    private final GaunletGuessOutputBoundary userPresenter;
    private final GaunletGameFactory game;

    public GaunletGuessInteractor(
            GaunletGuessOutputBoundary gaunletGuessOutputBoundary, GaunletGameFactory gaunletgame) {
        this.userPresenter = gaunletGuessOutputBoundary;
        this.game = gaunletgame;
    }

    @Override
    public void execute(GaunletGuessInputData gaunletGuessInputData) {
        final String coinGuess = gaunletGuessInputData.getCoinFlip();
        final String diceGuess = gaunletGuessInputData.getDice();
        final String rpsGuess = gaunletGuessInputData.getRps();

        if (!isValidCoinFlip(coinGuess)) {
            userPresenter.prepareFailView("Invalid coin guess. Please enter 'Heads' or 'Tails'.");
            return;
        }
        try {
            int dice = isValidDiceGuess(diceGuess);
        }
        catch (IllegalArgumentException e) {
            userPresenter.prepareFailView(e.getMessage());
            return;
        }

        if (!isValidRpsGuess(rpsGuess)) {
            userPresenter.prepareFailView("Invalid RPS guess. Please enter 'Rock', 'Paper', or 'Scissors'."
            );
            return;
        }
        final GaunletGame gaunletGame = game.create(coinGuess, Integer.parseInt(diceGuess), rpsGuess);
        final String actualCoinFlip = gaunletGame.flipCoin();
        final int actualDiceRoll = gaunletGame.rollDice();
        final String actualRpsOutcome = gaunletGame.getRpsOutcome();

        final boolean isWin = actualCoinFlip.equalsIgnoreCase(coinGuess)
                && actualDiceRoll == Integer.parseInt(diceGuess)
                && actualRpsOutcome.equalsIgnoreCase(rpsGuess);

        // Create output data and show result
        final GaunletGuessOutputData gaunletGuessOutputData = new GaunletGuessOutputData(
                coinGuess, diceGuess, rpsGuess, isWin, false);
        userPresenter.prepareSuccessView(gaunletGuessOutputData);

    }

    private boolean isValidCoinFlip(String coinFlip) {
        return "Heads".equalsIgnoreCase(coinFlip) || "Tails".equalsIgnoreCase(coinFlip);
    }

    private int isValidDiceGuess(String diceGuess) {

        try {
            final int diceValue = Integer.parseInt(diceGuess);
            if (diceValue < 1 || diceValue > 6) {
                throw new IllegalArgumentException("Invalid dice guess. Please enter a number between 1 and 6.");
            }
            return diceValue;
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid dice guess. Please enter a valid number.");
        }
    }

    private boolean isValidRpsGuess(String rpsGuess) {
        return "Rock".equalsIgnoreCase(rpsGuess)
                ||
                "Paper".equalsIgnoreCase(rpsGuess)
                ||
                "Scissors".equalsIgnoreCase(rpsGuess);
    }

    @Override
    public void switchToLoginView() {
        userPresenter.switchToLoginView();
    }
}
