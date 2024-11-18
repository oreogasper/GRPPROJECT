package interface_adapter.shop;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the shop main menu View.
 */
public class ShopViewModel extends ViewModel<ShopState> {

    public static final String TITLE_LABEL = "Welcome to the Shop!";
    public static final String WHEEL_BUTTON_LABEL = "Spin the Wheel";
    public static final String BUTTON_BUTTON_LABEL = "Farm for Tokens";
    public static final String BACK_BUTTON_LABEL = "Return to Menu";

    public ShopViewModel() {
        super("shop menu");
        setState(new ShopState());
    }

}
