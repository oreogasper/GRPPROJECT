package interface_adapter.login;

import interface_adapter.ViewModel;

/**
 * The View Model for the Login View.
 */
public class LoginViewModel extends ViewModel<LoginState> {
    public static final String LOGIN_LABEL = "LOGIN";
    public static final String USERNAME_LABEL = "USERNAME: ";
    public static final String PASSWORD_LABEL = "PASSWORD: ";
    public static final String CANCEL_LABEL = "CANCEL";

    public LoginViewModel() {
        super("log in");
        setState(new LoginState());
    }

}
