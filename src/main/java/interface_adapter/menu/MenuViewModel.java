package interface_adapter.menu;

import interface_adapter.ViewModel;
import interface_adapter.menu.MenuState;

/**
 * The ViewModel for the Menu View.
 */
public class MenuViewModel extends ViewModel<MenuState> {

    public static final String TITLE_LABEL = "MENU";
    public static final String STATS_BUTTON_LABEL = "MY STATISTICS";
    public static final String GAMBLE_BUTTON_LABEL = "GAMBLE";
    public static final String SHOP_BUTTON_LABEL = "SHOP";

    public MenuViewModel() {
        super("menu");
        // setState(new WelcomeState());
    }

}
