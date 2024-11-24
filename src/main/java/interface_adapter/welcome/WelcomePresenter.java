package interface_adapter.welcome;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.welcome.WelcomeOutputBoundary;

/**
 * The Presenter for the Welcome Use Case.
 */
public class WelcomePresenter implements WelcomeOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;
    private final SignupViewModel signupViewModel;

    public WelcomePresenter(ViewManagerModel viewManagerModel,
                            LoginViewModel loginViewModel,
                            SignupViewModel signupViewModel) {
        // TODO: assign to the three instance variables.
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.signupViewModel = signupViewModel;
    }

    @Override
    public void prepareSuccessView() {
    /*
            this.viewManagerModel.setState(signupViewModel.getViewName());
            this.viewManagerModel.firePropertyChanged();*/
    }

    @Override
    public void prepareFailView(String error) {
        // No need to add code here. We'll assume that logout can't fail.
        // Thought question: is this a reasonable assumption?
    }

    @Override
    public void switchToSignupView() {
        viewManagerModel.setState(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToLoginView() {
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
