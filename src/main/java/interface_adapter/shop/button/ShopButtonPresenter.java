package interface_adapter.shop.button;

import interface_adapter.ViewManagerModel;
import interface_adapter.shop.ShopViewModel;
import use_case.shopbutton.ShopButtonOutputBoundary;

/**
 * The Presenter for the Shop button Use Case.
 */
public class ShopButtonPresenter implements ShopButtonOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final ShopViewModel shopViewModel;

    public ShopButtonPresenter(ViewManagerModel viewManagerModel,
                         ShopViewModel shopViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.shopViewModel = shopViewModel;
    }

    @Override
    public void prepareSuccessView() {
    }

    @Override
    public void prepareFailView(String error) {
    }

    @Override
    public void switchToShopView() {
        viewManagerModel.setState(shopViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void buttonClick() {
        viewManagerModel.firePropertyChanged();
    }

}
