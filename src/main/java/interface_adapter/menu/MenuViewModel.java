package interface_adapter.menu;

import interface_adapter.ViewModel;
import interface_adapter.menu.MenuState;

/**
 * The ViewModel for the Menu View.
 */
public class MenuViewModel extends ViewModel<MenuState> {

    public static final String TITLE_LABEL = "MENU";
    public static final String STATS_BUTTON_LABEL = "MY STATISTICS";
    public static final String BLACKJACK_BUTTON_LABEL = "PLAY BLACKJACK";
    public static final String GAUNTLET_BUTTON_LABEL = "PLAY GAUNTLET";
    public static final String OVERUNDER_BUTTON_LABEL = "PLAY OVER/UNDER";
    public static final String SHOP_BUTTON_LABEL = "SHOP";

    public MenuViewModel() {
        super("menu");
        // setState(new WelcomeState());
    }

}
