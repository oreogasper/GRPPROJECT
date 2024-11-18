package use_case.shopwheel;

/**
 * The Shop wheel Interactor.
 */
public class ShopWheelInteractor implements ShopWheelInputBoundary {
    private final ShopWheelOutputBoundary userPresenter;

    public ShopWheelInteractor(ShopWheelOutputBoundary shopWheelOutputBoundary) {
        this.userPresenter = shopWheelOutputBoundary;
    }

    @Override
    public void switchToShopView() {
        userPresenter.switchToShopView();
    }

    @Override
    public void wheelSpin() {
        userPresenter.wheelSpin();
    }

}
