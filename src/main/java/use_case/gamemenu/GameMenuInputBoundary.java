package use_case.gamemenu;

/**
 * Input Boundary for actions which are related to Game menu.
 */
public interface GameMenuInputBoundary {

    /**
     * Executes the switch to menu view use case.
     */
    void switchToMenuView();

    /**
     * Executes the switch to login view use case.
     */
    void switchToLoginView();

    /**
     * Executes the switch to gaunlet view use case.
     */
    void switchToGaunletView();

    /**
     * Executes the switch to blackjack view use case.
     */
    void switchToBlackjackView();

    /**
     * Executes the switch to over/under bet view use case.
     */
    void switchToOverUnderBetView();
}
