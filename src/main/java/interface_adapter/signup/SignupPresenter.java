package interface_adapter.signup;

import data_access.DBUserDataAccessObject;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.menu.MenuState;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

/**
 * The Presenter for the Signup Use Case.
 */
public class SignupPresenter implements SignupOutputBoundary {

    private final DBUserDataAccessObject dbUserDataAccessObject;

    private final SignupViewModel signupViewModel;
    private final MenuViewModel menuViewModel;
    private final WelcomeViewModel welcomeViewModel;
    private final ViewManagerModel viewManagerModel;

    public SignupPresenter(DBUserDataAccessObject dbUserDataAccessObject,
                           ViewManagerModel viewManagerModel,
                           SignupViewModel signupViewModel,
                           MenuViewModel menuViewModel,
                           WelcomeViewModel welcomeViewModel) {
        this.dbUserDataAccessObject = dbUserDataAccessObject;
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.menuViewModel = menuViewModel;
        this.welcomeViewModel = welcomeViewModel;
    }

    @Override
    public void prepareSuccessView(SignupOutputData response) {

        // reset the signup state
        final SignupState signupState = signupViewModel.getState();
        signupState.setUsername("");
        signupState.setPassword("");
        signupState.setRepeatPassword("");
        this.signupViewModel.setState(signupState);
        signupViewModel.firePropertyChanged();

        // set the newly signed up user to the user of the menu state
        final User user = dbUserDataAccessObject.get(response.getUsername());
        final MenuState menuState = menuViewModel.getState();
        menuState.setUser(user);
        this.menuViewModel.firePropertyChanged();

        this.viewManagerModel.setState(menuViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final SignupState signupState = signupViewModel.getState();
        signupState.setUsernameError(error);
        signupViewModel.firePropertyChanged();
    }

    @Override
    public void switchToWelcomeView() {
        viewManagerModel.setState(welcomeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToMenuView() {
        viewManagerModel.setState(menuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
