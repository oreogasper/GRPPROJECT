package use_case.login;

import entity.User;

/**
 * Input Boundary for actions which are related to logging in.
 */
public interface LoginInputBoundary {

    /**
     * Executes the login use case.
     * @param loginInputData the input data
     */
    void execute(LoginInputData loginInputData);

    /**
     * Switches the screen to the welcome view.
     */
    void switchToWelcomeView();

    /**
     * Returns the user given the username key.
     * @param username is the key.
     * @return the user associated with the key.
     */
    User get(String username);

}
