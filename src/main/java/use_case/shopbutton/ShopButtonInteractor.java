package use_case.shopbutton;

/**
 * The Shop button Interactor.
 */
public class ShopButtonInteractor implements ShopButtonInputBoundary {
    private final ShopButtonOutputBoundary userPresenter;

    public ShopButtonInteractor(ShopButtonOutputBoundary shopButtonOutputBoundary) {
        this.userPresenter = shopButtonOutputBoundary;
    }

    @Override
    public void switchToShopView() {
        userPresenter.switchToShopView();
    }

    @Override
    public void buttonClick() {
        userPresenter.buttonClick();
    }

}
