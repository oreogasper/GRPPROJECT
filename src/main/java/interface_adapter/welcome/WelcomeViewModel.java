package interface_adapter.welcome;

import interface_adapter.ViewModel;
import interface_adapter.signup.SignupState;

/**
 * The ViewModel for the Welcome View.
 */
public class WelcomeViewModel extends ViewModel<SignupState> {

    public static final String TITLE_LABEL = "WELCOME!";
    public static final String SIGNUP_BUTTON_LABEL = "SIGN UP";
    public static final String TO_LOGIN_BUTTON_LABEL = "LOGIN";

    public WelcomeViewModel() {
        super("welcome");
        // setState(new WelcomeState());
    }

}
