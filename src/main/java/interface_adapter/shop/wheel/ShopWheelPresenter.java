package interface_adapter.shop.wheel;

import interface_adapter.ViewManagerModel;
import interface_adapter.shop.ShopViewModel;
import use_case.shopwheel.ShopWheelOutputBoundary;

/**
 * The Presenter for the Shop wheel Use Case.
 */
public class ShopWheelPresenter implements ShopWheelOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final ShopViewModel shopViewModel;

    public ShopWheelPresenter(ViewManagerModel viewManagerModel,
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
    public void wheelSpin() {
        viewManagerModel.firePropertyChanged();
    }

}
