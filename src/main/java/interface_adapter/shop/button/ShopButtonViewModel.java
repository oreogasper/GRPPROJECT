package interface_adapter.shop.button;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the shop button screen View.
 */
public class ShopButtonViewModel extends ViewModel<ShopButtonState> {

    public static final String TITLE_LABEL = "Click it up!";
    public static final String CLICK_BUTTON_LABEL = "$";
    public static final String SHOP_BUTTON_LABEL = "Return to Shop";
    public static final double DIVIDER = 10.0;

    public ShopButtonViewModel() {
        super("shop button");
        setState(new ShopButtonState());
    }

}
