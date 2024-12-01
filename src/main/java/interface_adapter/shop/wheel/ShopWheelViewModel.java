package interface_adapter.shop.wheel;

import interface_adapter.ViewModel;
import view.ShopWheelAnimationPanel;

/**
 * The ViewModel for the shop wheel View.
 */
public class ShopWheelViewModel extends ViewModel<ShopWheelState> {

    // Constants
    public static final String TITLE_LABEL = "Spin it up!";
    public static final String SPIN_LABEL = "Spin the wheel!";
    public static final String SHOP_BUTTON_LABEL = "Return to Shop";

    // Fields
    private ShopWheelAnimationPanel animationPanel;

    /**
     * Constructor to initialize the ShopWheelViewModel.
     */
    public ShopWheelViewModel() {
        super("shop wheel");
        setState(new ShopWheelState());
    }

    // Methods
    /**
     * Gets the ShopWheelAnimationPanel instance.
     * @return the animation panel
     */
    public ShopWheelAnimationPanel getAnimationPanel() {
        return animationPanel;
    }

    /**
     * Sets the ShopWheelAnimationPanel instance.
     * @param animationPanel the animation panel to set
     */
    public void setAnimationPanel(ShopWheelAnimationPanel animationPanel) {
        this.animationPanel = animationPanel;
    }
}
