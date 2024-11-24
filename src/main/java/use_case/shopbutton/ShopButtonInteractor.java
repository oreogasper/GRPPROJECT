package use_case.shopbutton;

/**
 * The presenter class for ShopButton.
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
    public void buttonClick(int currentClicks) {
        userPresenter.addClick();
        final int clickRequirement = 10;
        if ((currentClicks + 1) % clickRequirement == 0) {
            userPresenter.addToken();
        }
        userPresenter.prepareSuccessView();
    }
}
