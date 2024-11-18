package use_case.blackjack.bet;

import entity.User;

/**
 * DAO for the Blackjack Bet Use Case.
 */
public interface BlackjackBetUserDataAccessInterface {

    /**
     * Checks if the given bet is valid.
     * @param bet the given bet amount.
     * @return true if the bet is valid, otherwise false.
     */
    boolean validBet(int bet);

    /**
     * Logs a record of the bet.
     * @param user the user that made the bet.
     */
    void save(User user);

    // Need to add additional methods for updating the users balance after a bet is placed maybe
}
