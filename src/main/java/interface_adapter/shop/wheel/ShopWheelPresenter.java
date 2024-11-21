package interface_adapter.shop.wheel;

import interface_adapter.ViewManagerModel;
import interface_adapter.shop.ShopState;
import interface_adapter.shop.ShopViewModel;
import use_case.shopwheel.ShopWheelOutputBoundary;

/**
 * The Presenter for the Shop wheel Use Case.
 */
public class ShopWheelPresenter implements ShopWheelOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final ShopViewModel shopViewModel;
    private final ShopWheelViewModel shopWheelViewModel;

    public ShopWheelPresenter(ViewManagerModel viewManagerModel,
                              ShopViewModel shopViewModel,
                              ShopWheelViewModel shopWheelViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.shopViewModel = shopViewModel;
        this.shopWheelViewModel = shopWheelViewModel;
    }

    @Override
    public void prepareSuccessView() {
    }

    @Override
    public void prepareFailView(String error) {
    }

    @Override
    public void switchToShopView() {

        final ShopState shopState = shopViewModel.getState();
        shopState.setUser(shopWheelViewModel.getState().getUser());
        this.shopViewModel.setState(shopState);
        this.shopViewModel.firePropertyChanged();

        viewManagerModel.setState(shopViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void wheelSpin() {
        viewManagerModel.firePropertyChanged();
    }

}
