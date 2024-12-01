package use_case.Over_Under.bet;

public interface OverUnderBetInputBoundary {
    void startGame();
    void processGuess(boolean isHigher, int betAmount);
    void handleBet(int betAmount); // Add this method
}
