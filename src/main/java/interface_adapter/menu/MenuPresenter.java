package interface_adapter.menu;

import interface_adapter.ViewManagerModel;
import interface_adapter.gamemenu.GameMenuViewModel;
import interface_adapter.shop.ShopViewModel;
import interface_adapter.statistics.StatisticsViewModel;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.menu.MenuOutputBoundary;
import view.GameMenuView;

/**
 * The Presenter for the Welcome Use Case.
 */
public class MenuPresenter implements MenuOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final WelcomeViewModel welcomeViewModel;
    private final StatisticsViewModel statisticsViewModel;
    private final GameMenuViewModel gameMenuViewModel;
    private final ShopViewModel shopViewModel;

    public MenuPresenter(ViewManagerModel viewManagerModel,
                         WelcomeViewModel welcomeViewModel,
                         StatisticsViewModel statisticsViewModel,
                         GameMenuViewModel gameMenuViewModel,
                         ShopViewModel shopViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.welcomeViewModel = welcomeViewModel;
        this.statisticsViewModel = statisticsViewModel;
        this.gameMenuViewModel = gameMenuViewModel;
        this.shopViewModel = shopViewModel;
    }

    @Override
    public void prepareSuccessView() {
    }

    @Override
    public void prepareFailView(String error) {
    }

    @Override
    public void switchToWelcomeView() {
        viewManagerModel.setState(welcomeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToGameMenuView() {
        viewManagerModel.setState(gameMenuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToStatisticsView() {
        viewManagerModel.setState(statisticsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToShopView() {
        viewManagerModel.setState(shopViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
