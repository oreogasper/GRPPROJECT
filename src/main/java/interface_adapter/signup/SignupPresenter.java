package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

/**
 * The Presenter for the Signup Use Case.
 */
public class SignupPresenter implements SignupOutputBoundary {

    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private final WelcomeViewModel welcomeViewModel;
    private final ViewManagerModel viewManagerModel;

    public SignupPresenter(ViewManagerModel viewManagerModel,
                           SignupViewModel signupViewModel,
                           LoginViewModel loginViewModel,
                           WelcomeViewModel welcomeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
        this.welcomeViewModel = welcomeViewModel;
    }

    @Override
    public void prepareSuccessView(SignupOutputData response) {

        // On success, switch to the welcome view.
        final LoginState loginState = loginViewModel.getState();
        loginState.setUsername(response.getUsername());
        loginState.setPassword(response.getPassword());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        final SignupState signupState = signupViewModel.getState();
        signupState.setUsername("");
        signupState.setPassword("");
        signupState.setRepeatPassword("");
        this.signupViewModel.setState(signupState);
        signupViewModel.firePropertyChanged();

        this.viewManagerModel.setState(welcomeViewModel.getViewName());
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
}
