package interface_adapter.gamemenu;

import interface_adapter.ViewManagerModel;
import interface_adapter.blackjack.bet.BlackjackBetState;
import interface_adapter.blackjack.bet.BlackjackBetViewModel;
import interface_adapter.gaunlet.bet.GaunletBetState;
import interface_adapter.gaunlet.bet.GaunletBetViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.menu.MenuState;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.statistics.StatisticsState;
import interface_adapter.und_ovr.OverUnderViewModel;
import use_case.gamemenu.GameMenuOutputBoundary;

/**
 * The Presenter for the Welcome Use Case.
 */
public class GameMenuPresenter implements GameMenuOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;
    private final MenuViewModel menuViewModel;
    private final GaunletBetViewModel gaunletBetViewModel;
    private final BlackjackBetViewModel blackjackBetViewModel;
    private final GameMenuViewModel gameMenuViewModel;
    private final OverUnderViewModel overUnderViewModel;

    public GameMenuPresenter(ViewManagerModel viewManagerModel,
                             LoginViewModel loginViewModel,
                             MenuViewModel menuViewModel,
                             GaunletBetViewModel gaunletBetViewModel,
                             BlackjackBetViewModel blackjackBetViewModel,
                             GameMenuViewModel gameMenuViewModel,
                             OverUnderViewModel overUnderViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        this.menuViewModel = menuViewModel;
        this.gaunletBetViewModel = gaunletBetViewModel;
        this.blackjackBetViewModel = blackjackBetViewModel;
        this.gameMenuViewModel = gameMenuViewModel;
        this.overUnderViewModel = overUnderViewModel;
    }

    @Override
    public void prepareSuccessView() {
    }

    @Override
    public void prepareFailView(String error) {
    }

    @Override
    public void switchToMenuView() {

        final MenuState menuState = menuViewModel.getState();
        menuState.setUser(gameMenuViewModel.getState().getUser());
        this.menuViewModel.setState(menuState);
        this.menuViewModel.firePropertyChanged();

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

        final GaunletBetState gaunletBetState = gaunletBetViewModel.getState();
        gaunletBetState.setUser(gameMenuViewModel.getState().getUser());
        this.gaunletBetViewModel.setState(gaunletBetState);
        this.gaunletBetViewModel.firePropertyChanged();
        viewManagerModel.setState(gaunletBetViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void switchToBlackjackView() {

        final BlackjackBetState blackjackBetState = blackjackBetViewModel.getState();
        blackjackBetState.setUser(gameMenuViewModel.getState().getUser());
        this.blackjackBetViewModel.setState(blackjackBetState);
        this.blackjackBetViewModel.firePropertyChanged();

        viewManagerModel.setState(blackjackBetViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToOverUnderView() {

    }
}