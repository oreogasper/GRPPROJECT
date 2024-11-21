package interface_adapter.login;

import data_access.DBUserDataAccessObject;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.menu.MenuState;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final DBUserDataAccessObject dbUserDataAccessObject;
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    private final WelcomeViewModel welcomeViewModel;
    private final MenuViewModel menuViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoginViewModel loginViewModel,
                          WelcomeViewModel welcomeViewModel,
                          MenuViewModel menuViewModel,
                          DBUserDataAccessObject dbUserDataAccessObject) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.welcomeViewModel = welcomeViewModel;
        this.menuViewModel = menuViewModel;
        this.dbUserDataAccessObject = dbUserDataAccessObject;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {

        final User user = dbUserDataAccessObject.get(response.getUsername());
        final MenuState menuState = menuViewModel.getState();
        menuState.setUser(user);
        this.menuViewModel.firePropertyChanged();

        // On success, switch to the menu in view.
        this.viewManagerModel.setState(menuViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }

    @Override
    public void switchToWelcomeView() {
        viewManagerModel.setState(welcomeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
