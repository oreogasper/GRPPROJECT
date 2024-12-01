package use_case.Over_Under.bet;

/**
 * DAO for the Over Under Use Case.
 */
public interface OverUnderBetDataAccessInterface {

    /**
     * Sets the bet amount indicating who is the current user of the application.
     * @param bet the new current username; null to indicate that no one is currently logged into the application.
     */
    void setBet(int bet);

    /**
     * Returns the bet of the curren user for the over under game.
     * @return the bet of the current user; null indicates that no one is logged into the application.
     */
    int getBet();

}
