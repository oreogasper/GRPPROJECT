package interface_adapter.cancel;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.cancel.CancelOutputBoundary;
import use_case.cancel.CancelOutputData;

/**
 * The Presenter for the Cancel Use Case.
 */
public class CancelPresenter implements CancelOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    private SignupViewModel signupViewModel;

    public CancelPresenter(ViewManagerModel viewManagerModel,
                           LoginViewModel loginViewModel,
                           SignupViewModel signupViewModel) {
        // TODO: assign to the three instance variables.
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(CancelOutputData response) {
        // We need to switch to the signup view, which should have
        // an empty username and password.

        // We also need to set the username in the LogIn to
        // the empty string.

        // TODO: have prepareSuccessView update the LoginState
        // 1. get the LogdInState out of the appropriate View Model,
        // 2. set the username in the state to the empty string
        // 3. set the state in the LogInViewModel to the updated state
        // 4. firePropertyChanged so that the View that is listening is updated.
        final LoginState loginState = loginViewModel.getState();
        loginState.setUsername("");
        loginState.setPassword("");
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        // TODO: have prepareSuccessView update the SignupState
        // 5. get the SignUp out of the appropriate View Model,
        // 6. set the username and password in the state to the empty string
        // 7. set the state in the SignupViewModel to the updated state
        // 8. firePropertyChanged so that the View that is listening is updated.
        final SignupState signupState = signupViewModel.getState();
        signupState.setUsername("");
        signupState.setPassword("");
        this.signupViewModel.setState(signupState);
        signupViewModel.firePropertyChanged();

        // This code tells the View Manager to switch to the Signup.
        this.viewManagerModel.setState(signupViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        // No need to add code here. We'll assume that cancel can't fail.
        // Thought question: is this a reasonable assumption?
    }
}
