package interface_adapter.shop;

import interface_adapter.ViewManagerModel;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.shop.button.ShopButtonViewModel;
import interface_adapter.shop.wheel.ShopWheelViewModel;
import use_case.shop.ShopOutputBoundary;

/**
 * The Presenter for the Shop main menu Use Case.
 */
public class ShopPresenter implements ShopOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final ShopWheelViewModel shopWheelViewModel;
    private final MenuViewModel menuViewModel;
    private final ShopButtonViewModel shopButtonViewModel;

    public ShopPresenter(ViewManagerModel viewManagerModel,
                             ShopWheelViewModel shopWheelViewModel,
                             MenuViewModel menuViewModel, ShopButtonViewModel shopButtonViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.shopWheelViewModel = shopWheelViewModel;
        this.menuViewModel = menuViewModel;
        this.shopButtonViewModel = shopButtonViewModel;
    }

    @Override
    public void prepareSuccessView() {
    }

    @Override
    public void prepareFailView(String error) {
    }

    @Override
    public void switchToMenuView() {
        viewManagerModel.setState(menuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToShopWheelView() {
        viewManagerModel.setState(shopWheelViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToShopButtonView() {
        viewManagerModel.setState(shopButtonViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
