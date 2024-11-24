package interface_adapter.und_ovr;

import use_case.Over_Under.OverUnderInteractor;

public class OverUnderController {

    private final GameStateManager gameStateManager;
    private int betAmount;
    private OverUnderInteractor interactor;
    private OverUnderViewModel viewModel;

    public OverUnderController(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
    }

    public void placeBet(int betAmount) {
        this.interactor = new OverUnderInteractor();
        this.viewModel = new OverUnderViewModel();
        this.betAmount = betAmount;
        System.out.println("Bet placed: " + betAmount);
    }

    public void startGame() {
        // Transition to the game state
        gameStateManager.setCurrentState(GameStateManager.GameState.PLAYING);
        System.out.println("Game started with bet: " + betAmount);
        System.out.println("The rules of the game are as follows: \n");
        System.out.println("You have to guess whether a card is less or more than your card.");
        System.out.println("You have 2 attempts. If you guess 5 cards correct, you win 15 tokens");
        System.out.println("Otherwise you win nothing. Good luck!");
    }

    public int getBetAmount() {
        return betAmount;
    }

    public void makeGuess(boolean isHigher) {
        interactor.processGuess(isHigher, this.betAmount);
        viewModel.setCurrentCard(interactor.getCurrentCard());
    }
}
