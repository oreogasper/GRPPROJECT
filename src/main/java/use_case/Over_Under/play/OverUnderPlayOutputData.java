package use_case.Over_Under.play;

public class OverUnderOutputData {

    private final int currentCardValue;
    private final int nextCardRank;
    private final int balance;
    private final String guessResult;
    private final String error;

    public OverUnderOutputData(int currentCardValue, int nextCardRank, int balance, String guessResult, String error) {
        this.currentCardValue = currentCardValue;
        this.nextCardRank = nextCardRank;
        this.balance = balance;
        this.guessResult = guessResult;
        this.error = error;
    }

    // Getters for the fields
    public int getCurrentCardValue() {
        return currentCardValue;
    }

    public int getNextCardRank() {
        return nextCardRank;
    }

    public int getBalance() {
        return balance;
    }

    public String getGuessResult() {
        return guessResult;
    }

    public String getError() {
        return error;
    }
}
