package interface_adapter.menu;

import data_access.DBUserDataAccessObject;
import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.gamemenu.GameMenuState;
import interface_adapter.gamemenu.GameMenuViewModel;
import interface_adapter.shop.ShopState;
import interface_adapter.shop.ShopViewModel;
import interface_adapter.statistics.StatisticsState;
import interface_adapter.statistics.StatisticsViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.menu.MenuOutputBoundary;

/**
 * The Presenter for the Welcome Use Case.
 */
public class MenuPresenter implements MenuOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final StatisticsViewModel statisticsViewModel;
    private final GameMenuViewModel gameMenuViewModel;
    private final ShopViewModel shopViewModel;
    private final MenuViewModel menuViewModel;

    public MenuPresenter(ViewManagerModel viewManagerModel,
                         StatisticsViewModel statisticsViewModel,
                         GameMenuViewModel gameMenuViewModel,
                         ShopViewModel shopViewModel,
                         MenuViewModel menuViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.statisticsViewModel = statisticsViewModel;
        this.gameMenuViewModel = gameMenuViewModel;
        this.shopViewModel = shopViewModel;
        this.menuViewModel = menuViewModel;
    }

    @Override
    public void prepareSuccessView() {
    }

    @Override
    public void prepareFailView(String error) {
    }

    @Override
    public void switchToGameMenuView() {

        final GameMenuState gameMenuState = gameMenuViewModel.getState();
        gameMenuState.setUser(menuViewModel.getState().getUser());
        this.gameMenuViewModel.setState(gameMenuState);
        this.gameMenuViewModel.firePropertyChanged();

        viewManagerModel.setState(gameMenuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToStatisticsView() {

        final StatisticsState statisticsState = statisticsViewModel.getState();
        statisticsState.setUser(menuViewModel.getState().getUser());
        this.statisticsViewModel.setState(statisticsState);
        this.statisticsViewModel.firePropertyChanged();

        viewManagerModel.setState(statisticsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToShopView() {

        final ShopState shopState = shopViewModel.getState();
        shopState.setUser(menuViewModel.getState().getUser());
        this.shopViewModel.setState(shopState);
        this.shopViewModel.firePropertyChanged();

        viewManagerModel.setState(shopViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
