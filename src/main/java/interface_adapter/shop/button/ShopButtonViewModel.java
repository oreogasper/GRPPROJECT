package interface_adapter.shop.button;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the shop button screen View.
 */
public class ShopButtonViewModel extends ViewModel<ShopButtonState> {

    public static final String TITLE_LABEL = "CLICK or leave!";
    public static final String CLICK_BUTTON_LABEL = "Click me for money";
    public static final String SHOP_BUTTON_LABEL = "Return to shop menu";

    public ShopButtonViewModel() {
        super("shop button");
        setState(new ShopButtonState());
    }

}
