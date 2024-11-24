package interface_adapter.und_ovr;

import entity.CommonUser;

public class OverUnderGameInteractor {
    private CommonUser user;
    private static final int MIN_BET = 10;

    public OverUnderGameInteractor(CommonUser user) {
        this.user = user;
    }

    public void startGame() {
        System.out.println("Welcome to the Over/Under Game!");
        System.out.println("Your current balance: " + user.getBalance());
    }

    // Method to play a round (basic version)
    public void playRound(int betAmount) {
        if (betAmount < MIN_BET) {
            System.out.println("Bet amount is too low. Minimum bet is " + MIN_BET);
            return;
        }

        if (betAmount > user.getBalance()) {
            System.out.println("You don't have enough balance for this bet.");
            return;
        }

        // Proceed with the game logic (card dealing and comparison omitted for now)
        System.out.println("Bet of " + betAmount + " is accepted.");
        System.out.println("Proceeding with the round...");
    }
}