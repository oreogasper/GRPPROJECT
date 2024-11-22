package interface_adapter.shop.wheel;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the shop wheel View.
 */
public class ShopWheelViewModel extends ViewModel<ShopWheelState> {

    // Constants
    public static final String TITLE_LABEL = "Spin it up!";
    public static final String SPIN_BUTTON_LABEL = "Spin me";
    public static final String SHOP_BUTTON_LABEL = "Return to Shop";

    // Fields
    private double wheelAngle;

    /**
     * Constructor to initialize the ShopWheelViewModel.
     */
    public ShopWheelViewModel() {
        super("shop wheel");
        setState(new ShopWheelState());
    }

    // Methods
    /**
     * Notifies the screen of the changed angle.
     * @param angle is the new angle.
     */
    public void setWheelAngle(double angle) {
        this.wheelAngle = angle;
        firePropertyChanged();
    }

    public double getWheelAngle() {
        return this.wheelAngle;
    }

}
