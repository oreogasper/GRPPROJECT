package use_case.gaunlet.bet;

/**
 * The Gaunlet Bet Interactor.
 */
public class GaunletBetInteractor implements GaunletBetInputBoundary {
    private final GaunletBetOutputBoundary userPresenter;
    private final GaunletBetDataAccessInterface userDataAccessObject;

    public GaunletBetInteractor(GaunletBetDataAccessInterface userDataAccessObject,
                                GaunletBetOutputBoundary gaunletBetOutputBoundary) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = gaunletBetOutputBoundary;
    }

    @Override
    public void execute(GaunletBetInputData gaunletBetInputData) {
        String betAmount = gaunletBetInputData.getBet();
        if (!isValidBet(betAmount)) {
            userPresenter.presentInvalidBet("Invalid bet amount. Please check the rules and try again.");
            return;
        }

        // Process the bet, e.g., save it to the database or perform game logic
        userDataAccessObject.saveBet(gaunletBetInputData);

        userPresenter.presentSuccessfulBet("Bet placed successfully!");
    }

    private boolean isValidBet(String betAmount) {
        // Fetch game rules from the Data Access Object
        GameRules gameRules = userDataAccessObject.getGameRules();

        // Validate the bet amount
        return betAmount >= gameRules.getMinBet() && betAmount <= gameRules.getMaxBet();
    }
    }

    @Override
    public void switchToGaunletGuessView() {
        userPresenter.switchToGaunletGuessView();
    }

    @Override
    public void switchToGameMenuView() {
        userPresenter.switchToGameMenuView();
    }
}
