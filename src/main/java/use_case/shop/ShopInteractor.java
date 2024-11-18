package use_case.shop;

/**
 * The Shop main menu Interactor.
 */
public class ShopInteractor implements ShopInputBoundary {
    private final ShopOutputBoundary userPresenter;

    public ShopInteractor(ShopOutputBoundary shopOutputBoundary) {
        this.userPresenter = shopOutputBoundary;
    }

    @Override
    public void switchToMenuView() {
        userPresenter.switchToMenuView();
    }

    @Override
    public void switchToShopWheelView() {
        userPresenter.switchToShopWheelView();
    }

    @Override
    public void switchToShopButtonView() {
        userPresenter.switchToShopButtonView();
    }

}
