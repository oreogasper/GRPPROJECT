package interface_adapter.shop.wheel;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the shop wheel View.
 */
public class ShopWheelViewModel extends ViewModel<ShopWheelState> {

    public static final String TITLE_LABEL = "SPIN or leave!";
    public static final String SPIN_BUTTON_LABEL = "Spin me for money";
    public static final String SHOP_BUTTON_LABEL = "Return to shop menu";

    public ShopWheelViewModel() {
        super("shop wheel");
        setState(new ShopWheelState());
    }

}
