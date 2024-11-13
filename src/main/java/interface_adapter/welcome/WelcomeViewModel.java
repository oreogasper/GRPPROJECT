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
    public static final String STATS_BUTTON_LABEL = "MY STATISTICS";
    public static final String BLACKJACK_BUTTON_LABEL = "PLAY BLACKJACK";
    public static final String GAUNTLET_BUTTON_LABEL = "PLAY GAUNTLET";
    public static final String OVERUNDER_BUTTON_LABEL = "PLAY OVER/UNDER";
    public static final String SHOP_BUTTON_LABEL = "SHOP";

    public WelcomeViewModel() {
        super("welcome");
        // setState(new WelcomeState());
    }

}
