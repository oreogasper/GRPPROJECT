package interface_adapter.shop;

import interface_adapter.ViewManagerModel;
import interface_adapter.menu.MenuState;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.shop.button.ShopButtonState;
import interface_adapter.shop.button.ShopButtonViewModel;
import interface_adapter.shop.wheel.ShopWheelState;
import interface_adapter.shop.wheel.ShopWheelViewModel;
import use_case.shop.ShopOutputBoundary;
import use_case.shop.ShopOutputData;

/**
 * The Presenter for the Shop main menu Use Case.
 */
public class ShopPresenter implements ShopOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final ShopWheelViewModel shopWheelViewModel;
    private final MenuViewModel menuViewModel;
    private final ShopButtonViewModel shopButtonViewModel;
    private final ShopViewModel shopViewModel;

    public ShopPresenter(ViewManagerModel viewManagerModel,
                         ShopWheelViewModel shopWheelViewModel,
                         MenuViewModel menuViewModel,
                         ShopButtonViewModel shopButtonViewModel,
                         ShopViewModel shopViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.shopWheelViewModel = shopWheelViewModel;
        this.menuViewModel = menuViewModel;
        this.shopButtonViewModel = shopButtonViewModel;
        this.shopViewModel = shopViewModel;
    }

    @Override
    public void prepareSuccessView(ShopOutputData response) {
        // TODO: nothing so far
    }

    @Override
    public void prepareFailView(String error) {
    }

    @Override
    public void switchToMenuView() {

        final MenuState menuState = menuViewModel.getState();
        menuState.setUser(shopViewModel.getState().getUser());
        this.menuViewModel.setState(menuState);
        this.menuViewModel.firePropertyChanged();

        viewManagerModel.setState(menuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToShopWheelView() {

        final ShopWheelState shopWheelState = shopWheelViewModel.getState();
        shopWheelState.setUser(shopViewModel.getState().getUser());
        this.shopWheelViewModel.setState(shopWheelState);
        this.shopWheelViewModel.firePropertyChanged();

        viewManagerModel.setState(shopWheelViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToShopButtonView() {

        final ShopButtonState shopButtonState = shopButtonViewModel.getState();
        shopButtonState.setUser(shopViewModel.getState().getUser());
        this.shopButtonViewModel.setState(shopButtonState);
        this.shopButtonViewModel.firePropertyChanged();

        viewManagerModel.setState(shopButtonViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
