package interface_adapter.shop.button;

import interface_adapter.ViewManagerModel;
import interface_adapter.shop.ShopState;
import interface_adapter.shop.ShopViewModel;
import use_case.shopbutton.ShopButtonOutputBoundary;

/**
 * The Presenter for the Shop button Use Case.
 */
public class ShopButtonPresenter implements ShopButtonOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final ShopViewModel shopViewModel;
    private final ShopButtonViewModel shopButtonViewModel;

    public ShopButtonPresenter(ViewManagerModel viewManagerModel,
                               ShopViewModel shopViewModel,
                               ShopButtonViewModel shopButtonViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.shopViewModel = shopViewModel;
        this.shopButtonViewModel = shopButtonViewModel;
    }

    @Override
    public void prepareSuccessView() {
        shopButtonViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        // Handle failure scenarios (if necessary)
    }

    @Override
    public void switchToShopView() {
        final ShopState shopState = shopViewModel.getState();
        shopState.setUser(shopButtonViewModel.getState().getUser());
        this.shopViewModel.setState(shopState);
        this.shopViewModel.firePropertyChanged();

        viewManagerModel.setState(shopViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void addClick() {
        this.shopButtonViewModel.getState().addClick();
        this.shopButtonViewModel.firePropertyChanged();
    }

    @Override
    public void addToken() {
        this.shopButtonViewModel.getState().getUser().updateBalance(1);
        this.shopButtonViewModel.firePropertyChanged();
    }
}
