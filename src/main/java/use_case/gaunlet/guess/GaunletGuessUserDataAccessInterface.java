package use_case.gaunlet.guess;

import entity.User;

/**
 * DAO for the Signup Use Case.
 */
public interface GaunletGuessUserDataAccessInterface {

    /**
     * Checks if the given bet is valid bet.
     * @param bet the bet amount to look for
     * @return true if the bet meets the minimum requirements; false otherwise
     */
    boolean validBet(String bet);

    /**
     * Saves the bet.
     * @param user the bet to save
     */
    void save(User user);
    // need to implement bet entity
}
