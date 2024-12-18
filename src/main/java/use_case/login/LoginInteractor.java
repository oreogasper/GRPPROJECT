package use_case.login;

import entity.User;

/**
 * The Login Interactor.
 */
public class LoginInteractor implements LoginInputBoundary {
    private final LoginUserDataAccessInterface userDataAccessObject;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        final String username = loginInputData.getUsername();
        final String password = loginInputData.getPassword();

        if (!userDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView("Account with username \"" + username + "\" does NOT exist!");
            return;
        }

        final String pwd = userDataAccessObject.get(username).getPassword();
        if (!password.equals(pwd)) {
            loginPresenter.prepareFailView("INCORRECT password for \"" + username + "\"!");
            return;
        }

        final User user = userDataAccessObject.get(loginInputData.getUsername());
        userDataAccessObject.setCurrentUsername(user.getName());
        final LoginOutputData loginOutputData = new LoginOutputData(user.getName(), false);
        loginPresenter.prepareSuccessView(loginOutputData);
    }

    @Override
    public User get(String username) {
        return userDataAccessObject.get(username);
    }

    @Override
    public void switchToWelcomeView() {
        loginPresenter.switchToWelcomeView();
    }
}
