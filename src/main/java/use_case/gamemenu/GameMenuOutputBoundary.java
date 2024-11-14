package use_case.gamemenu;

/**
 * The output boundary for the game Menu Use Case.
 */
public interface GameMenuOutputBoundary {
    /**
     * Prepares the success view for the Menu Use Case.
     */
    // void prepareSuccessView(WelcomeOutputData outputData);
    void prepareSuccessView();

    /**
     * Prepares the failure view for the Login Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Menu View.
     */
    void switchToMenuView();

    /**
     * Switches to the login View.
     */
    void switchToLoginView();

    /**
     * Switches to the gaunlet View.
     */
    void switchToGaunletView();

    /**
     * Switches to the blackjack View.
     */
    void switchToBlackjackView();

    /**
     * Switches to the over/under View.
     */
    void switchToOverUnderView();
}
