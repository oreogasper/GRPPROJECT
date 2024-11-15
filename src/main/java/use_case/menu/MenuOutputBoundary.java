package use_case.menu;

/**
 * The output boundary for the Menu Use Case.
 */
public interface MenuOutputBoundary {
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
     * Switches to the Statistics View.
     */
    void switchToStatisticsView();

    void switchToLoginView();
    public void switchToGameMenuView();
}
