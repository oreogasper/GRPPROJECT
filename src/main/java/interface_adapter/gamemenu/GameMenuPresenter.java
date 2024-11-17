package interface_adapter.gamemenu;

import interface_adapter.ViewManagerModel;
import interface_adapter.gaunlet.bet.GaunletBetViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.menu.MenuViewModel;
import use_case.gamemenu.GameMenuOutputBoundary;


/**
 * The Presenter for the Welcome Use Case.
 */
public class GameMenuPresenter implements GameMenuOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;
    private final MenuViewModel menuViewModel;
    private final GaunletBetViewModel gaunletBetViewModel;

    public GameMenuPresenter(ViewManagerModel viewManagerModel,
                         LoginViewModel loginViewModel,
                         MenuViewModel menuViewModel, GaunletBetViewModel gaunletBetViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.menuViewModel = menuViewModel;
        this.gaunletBetViewModel = gaunletBetViewModel;
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
    public void switchToLoginView() {
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToGaunletView() {
        viewManagerModel.setState(gaunletBetViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToBlackjackView() {

    }

    @Override
    public void switchToOverUnderView() {

    }
}
