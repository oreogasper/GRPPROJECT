package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.DBCardDeckDataAccessObject;
import data_access.DBUserDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_friend.AddFriendController;
import interface_adapter.add_friend.AddFriendPresenter;
import interface_adapter.blackjack.bet.BlackjackBetController;
import interface_adapter.blackjack.bet.BlackjackBetPresenter;
import interface_adapter.blackjack.bet.BlackjackBetViewModel;
import interface_adapter.blackjack.game.BlackjackGameController;
import interface_adapter.blackjack.game.BlackjackGamePresenter;
import interface_adapter.blackjack.game.BlackjackGameViewModel;
import interface_adapter.blackjack.game.hit.BlackjackHitController;
import interface_adapter.blackjack.game.hit.BlackjackHitPresenter;
import interface_adapter.blackjack.game.stand.BlackjackStandController;
import interface_adapter.blackjack.game.stand.BlackjackStandPresenter;
import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardPresenter;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordPresenter;
import interface_adapter.gamemenu.GameMenuController;
import interface_adapter.gamemenu.GameMenuPresenter;
import interface_adapter.gamemenu.GameMenuViewModel;
import interface_adapter.gaunlet.bet.GaunletBetController;
import interface_adapter.gaunlet.bet.GaunletBetPresenter;
import interface_adapter.gaunlet.bet.GaunletBetViewModel;
import interface_adapter.gaunlet.guess.GaunletGuessController;
import interface_adapter.gaunlet.guess.GaunletGuessPresenter;
import interface_adapter.gaunlet.guess.GaunletGuessViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.menu.MenuController;
import interface_adapter.menu.MenuPresenter;
import interface_adapter.menu.MenuViewModel;
import interface_adapter.remove_friend.RemoveFriendController;
import interface_adapter.remove_friend.RemoveFriendPresenter;
import interface_adapter.shop.ShopController;
import interface_adapter.shop.ShopPresenter;
import interface_adapter.shop.ShopViewModel;
import interface_adapter.shop.button.ShopButtonController;
import interface_adapter.shop.button.ShopButtonPresenter;
import interface_adapter.shop.button.ShopButtonViewModel;
import interface_adapter.shop.wheel.ShopWheelController;
import interface_adapter.shop.wheel.ShopWheelPresenter;
import interface_adapter.shop.wheel.ShopWheelViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.statistics.StatisticsController;
import interface_adapter.statistics.StatisticsPresenter;
import interface_adapter.statistics.StatisticsViewModel;
import interface_adapter.und_ovr.bet.OverUnderBetController;
import interface_adapter.und_ovr.bet.OverUnderBetPresenter;
import interface_adapter.und_ovr.bet.OverUnderBetViewModel;
import interface_adapter.und_ovr.play.OverUnderPlayController;
import interface_adapter.und_ovr.play.OverUnderPlayPresenter;
import interface_adapter.und_ovr.play.OverUnderPlayState;
import interface_adapter.und_ovr.play.OverUnderPlayViewModel;
import interface_adapter.welcome.WelcomeController;
import interface_adapter.welcome.WelcomePresenter;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.Over_Under.bet.OverUnderBetInputBoundary;
import use_case.Over_Under.bet.OverUnderBetInteractor;
import use_case.Over_Under.bet.OverUnderBetOutputBoundary;
import use_case.Over_Under.play.OverUnderPlayInputBoundary;
import use_case.Over_Under.play.OverUnderPlayInteractor;
import use_case.Over_Under.play.OverUnderPlayOutputBoundary;
import use_case.add_friend.AddFriendInputBoundary;
import use_case.add_friend.AddFriendInteractor;
import use_case.add_friend.AddFriendOutputBoundary;
import use_case.blackjack.bet.BlackjackBetInputBoundary;
import use_case.blackjack.bet.BlackjackBetInteractor;
import use_case.blackjack.bet.BlackjackBetOutputBoundary;
import use_case.blackjack.game.BlackjackGameInputBoundary;
import use_case.blackjack.game.BlackjackGameInteractor;
import use_case.blackjack.game.BlackjackGameOutputBoundary;
import use_case.blackjack.hit.BlackjackHitDataAccessInterface;
import use_case.blackjack.hit.BlackjackHitInputBoundary;
import use_case.blackjack.hit.BlackjackHitInteractor;
import use_case.blackjack.hit.BlackjackHitOutputBoundary;
import use_case.blackjack.stand.BlackjackStandInputBoundary;
import use_case.blackjack.stand.BlackjackStandInteractor;
import use_case.blackjack.stand.BlackjackStandOutputBoundary;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInteractor;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.gamemenu.GameMenuInputBoundary;
import use_case.gamemenu.GameMenuInteractor;
import use_case.gamemenu.GameMenuOutputBoundary;
import use_case.gaunlet.bet.GaunletBetInputBoundary;
import use_case.gaunlet.bet.GaunletBetInteractor;
import use_case.gaunlet.bet.GaunletBetOutputBoundary;
import use_case.gaunlet.guess.GaunletGuessInputBoundary;
import use_case.gaunlet.guess.GaunletGuessInteractor;
import use_case.gaunlet.guess.GaunletGuessOutputBoundary;
import use_case.leaderboard.LeaderboardInputBoundary;
import use_case.leaderboard.LeaderboardInteractor;
import use_case.leaderboard.LeaderboardOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.menu.MenuInputBoundary;
import use_case.menu.MenuInteractor;
import use_case.menu.MenuOutputBoundary;
import use_case.remove_friend.RemoveFriendInputBoundary;
import use_case.remove_friend.RemoveFriendInteractor;
import use_case.remove_friend.RemoveFriendOutputBoundary;
import use_case.shop.ShopInputBoundary;
import use_case.shop.ShopInteractor;
import use_case.shop.ShopOutputBoundary;
import use_case.shopbutton.ShopButtonInputBoundary;
import use_case.shopbutton.ShopButtonInteractor;
import use_case.shopbutton.ShopButtonOutputBoundary;
import use_case.shopwheel.ShopWheelInputBoundary;
import use_case.shopwheel.ShopWheelInteractor;
import use_case.shopwheel.ShopWheelOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.statistics.StatisticsInputBoundary;
import use_case.statistics.StatisticsInteractor;
import use_case.statistics.StatisticsOutputBoundary;
import use_case.welcome.WelcomeInputBoundary;
import use_case.welcome.WelcomeInteractor;
import use_case.welcome.WelcomeOutputBoundary;
import view.*;

/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */
// Checkstyle note: you can ignore the "Class Data Abstraction Coupling"
//                  and the "Class Fan-Out Complexity" issues for this lab; we encourage
//                  your team to think about ways to refactor the code to resolve these
//                  if your team decides to work with this as your starter code
//                  for your final project this term.
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    // thought question: is the hard dependency below a problem?
    private final UserFactory userFactory = new CommonUserFactory();
    private final CardFactory cardFactory = new CardFactory();
    private final BlackjackGameFactory blackjackGameFactory = new BlackjackGameFactory();
    private final BlackjackGame blackjackGame = blackjackGameFactory.create();
    private final GaunletGameFactory gaunletgame = new GaunletGameFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // thought question: is the hard dependency below a problem?
    private final DBUserDataAccessObject userDataAccessObject;
    private final DBCardDeckDataAccessObject cardDeckDataAccessObject;
    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;

    private LeaderboardViewModel leaderboardViewModel;
    private LeaderboardView leaderboardView;
    private LoginView loginView;
    private GameMenuViewModel gameMenuViewModel;
    private GameMenuView gameMenuView;
    private WelcomeView welcomeView;
    private WelcomeViewModel welcomeViewModel;
    private StatsView statsView;
    private StatisticsViewModel statisticsViewModel;
    private MenuView menuView;
    private MenuViewModel menuViewModel;
    private GaunletBetView gaunletBetView;
    private GaunletBetViewModel gaunletBetViewModel;
    private GaunletGuessView gaunletGuessView;
    private GaunletGuessViewModel gaunletGuessViewModel;
    private BlackjackBetViewModel blackjackBetViewModel;
    private BlackjackBetView blackjackBetView;
    private BlackjackGameViewModel blackjackGameViewModel;
    private BlackjackGameView blackjackGameView;
    private ShopMainView shopMainView;
    private ShopViewModel shopMainViewModel;
    private ShopButtonView shopButtonView;
    private ShopButtonViewModel shopButtonViewModel;
    private ShopWheelView shopWheelView;
    private ShopWheelViewModel shopWheelViewModel;
    private OverUnderPlayViewModel overUnderPlayViewModel;
    private OverUnderBetView overUnderBetView;
    private OverUnderPlayView overUnderPlayView;
    private final OverUnderGameFactory overUnderGame = new OverUnderGameFactory();
    private final OverUnderBetViewModel overUnderBetViewModel = new OverUnderBetViewModel();


    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
        userDataAccessObject = new DBUserDataAccessObject(userFactory);
        cardDeckDataAccessObject = new DBCardDeckDataAccessObject(cardFactory);
    }

    public AppBuilder addOverUnderBetView() {
        OverUnderBetView overUnderBetView = new OverUnderBetView(overUnderBetViewModel);  // Use the shared viewModel
        this.overUnderBetView = overUnderBetView;
        cardPanel.add(overUnderBetView, overUnderBetView.getViewName()); // Attach to the UI
        return this; // Allow method chaining
    }

    public AppBuilder addOverUnderPlayView() {
        OverUnderPlayViewModel overUnderPlayViewModel = new OverUnderPlayViewModel();
        OverUnderPlayView overUnderPlayView = new OverUnderPlayView(overUnderPlayViewModel);
        OverUnderPlayState overUnderPlayState = new OverUnderPlayState();
        cardPanel.add(overUnderPlayView, overUnderPlayView.getName());
        return this;
    }

    public AppBuilder addOverUnderBetUseCase() {
        final OverUnderBetOutputBoundary overUnderBetOutputBoundary = new OverUnderBetPresenter(
                viewManagerModel, gameMenuViewModel, overUnderBetViewModel, overUnderPlayViewModel);
        final OverUnderBetInputBoundary overUnderBetInputBoundary = new OverUnderBetInteractor(
                userDataAccessObject, overUnderBetOutputBoundary, userFactory);
        final OverUnderBetController overUnderBetController = new OverUnderBetController(overUnderBetInputBoundary);
        overUnderBetView.setOverUnderBetController(overUnderBetController);
        return this;
    }

    public AppBuilder addOverUnderPlayUseCase() {
        OverUnderPlayViewModel overUnderPlayViewModel = new OverUnderPlayViewModel();
        OverUnderPlayView overUnderPlayView = new OverUnderPlayView(overUnderPlayViewModel);
        final OverUnderPlayOutputBoundary overUnderPlayOutputBoundary = new OverUnderPlayPresenter(
                viewManagerModel, menuViewModel, overUnderPlayViewModel, gameMenuViewModel);
        final OverUnderPlayInputBoundary overUnderPlayInputBoundary = new OverUnderPlayInteractor(
                cardDeckDataAccessObject, overUnderPlayOutputBoundary, userFactory, overUnderGame, userDataAccessObject);
        final OverUnderPlayController overUnderPlayController = new OverUnderPlayController(overUnderPlayInputBoundary);
        overUnderPlayView.setOverUnderPlayController(overUnderPlayController);
        return this;
    }


    /**
     * Adds the Welcome View to the application.
     * @return this builder
     */
    public AppBuilder addWelcomeView() {
        welcomeViewModel = new WelcomeViewModel();
        welcomeView = new WelcomeView(welcomeViewModel);
        cardPanel.add(welcomeView, welcomeView.getViewName());
        return this;
    }

    /**
     * Adds the Gaunlet guess View to the application.
     * @return this builder
     */
    public AppBuilder addGaunletGuessView() {
        gaunletGuessViewModel = new GaunletGuessViewModel();
        gaunletGuessView = new GaunletGuessView(gaunletGuessViewModel);
        cardPanel.add(gaunletGuessView, gaunletGuessView.getViewName());
        return this;
    }

    /**
     * Adds the Gaunlet Bet View to the application.
     * @return this builder
     */
    public AppBuilder addGaunletBetView() {
        gaunletBetViewModel = new GaunletBetViewModel();
        gaunletBetView = new GaunletBetView(gaunletBetViewModel);
        cardPanel.add(gaunletBetView, gaunletBetView.getViewName());
        return this;
    }

    /**
     * Adds the Game Menu View to the application.
     * @return this builder
     */
    public AppBuilder addGameMenuView() {
        gameMenuViewModel = new GameMenuViewModel();
        gameMenuView = new GameMenuView(gameMenuViewModel);
        cardPanel.add(gameMenuView, gameMenuView.getViewName());
        return this;
    }

    /**
     * Adds the Signup View to the application.
     * @return this builder
     */
    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    /**
     * Adds the Login View to the application.
     * @return this builder
     */
    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the Menu View to the application.
     * @return this builder
     */
    public AppBuilder addMenuView() {
        menuViewModel = new MenuViewModel();
        menuView = new MenuView(menuViewModel);
        cardPanel.add(menuView, menuView.getViewName());
        return this;
    }

    /**
     * Adds the Leaderboard View to the application.
     * @return this builder
     */
    public AppBuilder addLeaderboardView() {
        leaderboardViewModel = new LeaderboardViewModel();
        leaderboardView = new LeaderboardView(leaderboardViewModel);
        cardPanel.add(leaderboardView, leaderboardView.getViewName());
        return this;
    }

    /**
     * Adds the Statistics View to the application.
     * @return this builder
     */
    public AppBuilder addStatisticsView() {
        statisticsViewModel = new StatisticsViewModel();
        statsView = new StatsView(statisticsViewModel);
        cardPanel.add(statsView, statsView.getViewName());
        return this;
    }

    /**
     * Adds the Blackjack Bet View to the application.
     * @return this builder
     */
    public AppBuilder addBlackjackBetView() {
        blackjackBetViewModel = new BlackjackBetViewModel();
        blackjackBetView = new BlackjackBetView(blackjackBetViewModel);
        cardPanel.add(blackjackBetView, blackjackBetView.getViewName());
        return this;
    }

    /**
     * Adds the shop main menu view to the application.
     * @return this builder
     */
    public AppBuilder addShopMainView() {
        shopMainViewModel = new ShopViewModel();
        shopMainView = new ShopMainView(shopMainViewModel);
        cardPanel.add(shopMainView, shopMainView.getViewName());
        return this;
    }

    /**
     * Adds the Blackjack Game View to the application.
     * @return this builder
     */
    public AppBuilder addBlackjackGameView() {
        blackjackGameViewModel = new BlackjackGameViewModel();
        blackjackGameView = new BlackjackGameView(blackjackGameViewModel);
        cardPanel.add(blackjackGameView, blackjackGameView.getViewName());
        return this;
    }

    /**
     * Adds the shop button view to the application.
     * @return this builder
     */
    public AppBuilder addShopButtonView() {
        shopButtonViewModel = new ShopButtonViewModel();
        shopButtonView = new ShopButtonView(shopButtonViewModel);
        cardPanel.add(shopButtonView, shopButtonView.getViewName());
        return this;
    }

    /**
     * Adds the shop wheel view to the application.
     * @return this builder
     */
    public AppBuilder addShopWheelView() {
        shopWheelViewModel = new ShopWheelViewModel();
        shopWheelView = new ShopWheelView(shopWheelViewModel);
        cardPanel.add(shopWheelView, shopWheelView.getViewName());
        return this;
    }

    /**
     * Adds the Welcome Use Case to the application.
     * @return this builder
     */
    public AppBuilder addWelcomeUseCase() {
        final WelcomeOutputBoundary welcomeOutputBoundary = new WelcomePresenter(viewManagerModel,
                loginViewModel, signupViewModel);
        final WelcomeInputBoundary userWelcomeInteractor = new WelcomeInteractor(
                welcomeOutputBoundary);

        final WelcomeController welcomecontroller = new WelcomeController(userWelcomeInteractor);
        welcomeView.setWelcomeController(welcomecontroller);
        return this;
    }

    /**
     * Adds the Gaunlet Guess Use Case to the application.
     * @return this builder
     */
    public AppBuilder addGaunletGuessUseCase() {
        final GaunletGuessOutputBoundary gaunletGuessOutputBoundary = new GaunletGuessPresenter(viewManagerModel,
                menuViewModel, gaunletGuessViewModel, gameMenuViewModel);
        final GaunletGuessInputBoundary userGaunletGuessInteractor = new GaunletGuessInteractor(
                gaunletGuessOutputBoundary, gaunletgame, userDataAccessObject, userFactory);

        final GaunletGuessController gaunletGuesscontroller = new GaunletGuessController(userGaunletGuessInteractor);
        gaunletGuessView.setGaunletGuessController(gaunletGuesscontroller);
        return this;
    }

    /**
     * Adds the Gaunlet Bet Use Case to the application.
     * @return this builder
     */
    public AppBuilder addGaunletBetUseCase() {
        final GaunletBetOutputBoundary gaunletBetOutputBoundary = new GaunletBetPresenter(
                viewManagerModel, gameMenuViewModel, gaunletBetViewModel, gaunletGuessViewModel);
        final GaunletBetInputBoundary userGaunletBetInteractor = new GaunletBetInteractor(
                userDataAccessObject, gaunletBetOutputBoundary, userFactory);

        final GaunletBetController gaunletBetcontroller = new GaunletBetController(userGaunletBetInteractor);
        gaunletBetView.setGaunletBetController(gaunletBetcontroller);
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(userDataAccessObject, viewManagerModel,
                signupViewModel, menuViewModel, welcomeViewModel);
        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        final SignupController controller = new SignupController(userSignupInteractor);
        signupView.setSignupController(controller);
        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                loginViewModel, welcomeViewModel, menuViewModel, userDataAccessObject);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }

    /**
     * Adds the Menu Use Case to the application.
     * @return this builder
     */
    public AppBuilder addMenuUseCase() {
        final MenuOutputBoundary menuOutputBoundary = new MenuPresenter(viewManagerModel,
                statisticsViewModel, gameMenuViewModel, shopMainViewModel, menuViewModel);
        final MenuInputBoundary userMenuInteractor = new MenuInteractor(
                menuOutputBoundary);

        final MenuController menucontroller = new MenuController(userMenuInteractor);
        menuView.setMenuController(menucontroller);
        return this;
    }

    /**
     * Adds the Game Menu Use Case to the application.
     * @return this builder
     */
    public AppBuilder addGameMenuUseCase() {
        final GameMenuOutputBoundary gameMenuOutputBoundary = new GameMenuPresenter(viewManagerModel,
                loginViewModel, menuViewModel, gaunletBetViewModel, blackjackBetViewModel,
                gameMenuViewModel, overUnderBetViewModel);
        final GameMenuInputBoundary userGameMenuInteractor = new GameMenuInteractor(
                gameMenuOutputBoundary);

        final GameMenuController gameMenucontroller = new GameMenuController(userGameMenuInteractor);
        gameMenuView.setGameMenuController(gameMenucontroller);
        return this;
    }

    /**
     * Adds the Change Password Use Case to the application.
     * @return this builder
     */
    public AppBuilder addChangePasswordUseCase() {
        final ChangePasswordOutputBoundary changePasswordOutputBoundary =
                new ChangePasswordPresenter(statisticsViewModel);

        final ChangePasswordInputBoundary changePasswordInteractor =
                new ChangePasswordInteractor(userDataAccessObject, changePasswordOutputBoundary, userFactory);

        final ChangePasswordController changePasswordController =
                new ChangePasswordController(changePasswordInteractor);
        statsView.setChangePasswordController(changePasswordController);
        return this;
    }

    /**
     * Adds the Logout Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
                statisticsViewModel, loginViewModel, shopButtonViewModel, welcomeViewModel);

        final LogoutInputBoundary logoutInteractor =
                new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(logoutInteractor);
        statsView.setLogoutController(logoutController);
        return this;
    }

    /**
     * Adds the Statistics Use Case to the application.
     * @return this builder
     */
    public AppBuilder addStatisticsUseCase() {
        final StatisticsOutputBoundary statisticsOutputBoundary = new StatisticsPresenter(viewManagerModel,
                statisticsViewModel, leaderboardViewModel, menuViewModel);
        final StatisticsInputBoundary userStatisticsInteractor = new StatisticsInteractor(
                statisticsOutputBoundary);

        final StatisticsController statisticsController = new StatisticsController(userStatisticsInteractor);
        statsView.setStatisticsController(statisticsController);
        return this;
    }

    /**
     * Adds the Leaderboard Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLeaderboardUseCase() {
        final LeaderboardOutputBoundary leaderboardOutputBoundary = new LeaderboardPresenter(viewManagerModel,
                statisticsViewModel, leaderboardViewModel);
        final LeaderboardInputBoundary userLeaderboardInteractor = new LeaderboardInteractor(
                leaderboardOutputBoundary);

        final LeaderboardController leaderboardController = new LeaderboardController(userLeaderboardInteractor);
        leaderboardView.setLeaderboardController(leaderboardController);
        return this;
    }

    /**
     * Adds the Add Friend Use Case to the application.
     * @return this builder
     */
    public AppBuilder addAddFriendUseCase() {
        final AddFriendOutputBoundary addFriendOutputBoundary =
                new AddFriendPresenter(leaderboardViewModel);

        final AddFriendInputBoundary addFriendInteractor =
                new AddFriendInteractor(userDataAccessObject, addFriendOutputBoundary, userFactory);

        final AddFriendController addFriendController =
                new AddFriendController(addFriendInteractor);
        leaderboardView.setAddFriendController(addFriendController);
        return this;
    }

    /**
     * Adds the Remove Friend Use Case to the application.
     * @return this builder
     */
    public AppBuilder addRemoveFriendUseCase() {
        final RemoveFriendOutputBoundary removeFriendOutputBoundary =
                new RemoveFriendPresenter(leaderboardViewModel);

        final RemoveFriendInputBoundary removeFriendInteractor =
                new RemoveFriendInteractor(userDataAccessObject, removeFriendOutputBoundary, userFactory);

        final RemoveFriendController removeFriendController =
                new RemoveFriendController(removeFriendInteractor);
        leaderboardView.setRemoveFriendController(removeFriendController);
        return this;
    }

    /**
     * Adds the Blackjack Bet Use Case to the application.
     * @return this builder
     */
    public AppBuilder addBlackjackBetUseCase() {
        final BlackjackBetOutputBoundary blackjackBetOutputBoundary = new BlackjackBetPresenter(
                gameMenuViewModel, blackjackBetViewModel, blackjackGameViewModel, viewManagerModel);
        final BlackjackBetInputBoundary blackjackBetInteractor = new BlackjackBetInteractor(blackjackBetOutputBoundary,
                userDataAccessObject, blackjackGame,
                userFactory);

        final BlackjackGameOutputBoundary blackjackGameOutputBoundary = new BlackjackGamePresenter(
                signupViewModel, blackjackGameViewModel, gameMenuViewModel, viewManagerModel, menuViewModel,
                blackjackBetViewModel
        );

        final BlackjackGameInputBoundary blackjackGameInteractor = new BlackjackGameInteractor(
                blackjackGameOutputBoundary, userDataAccessObject, cardDeckDataAccessObject, null,
                null, userFactory, blackjackGame
        );

        final BlackjackBetController blackjackBetController = new BlackjackBetController(blackjackBetInteractor);
        final BlackjackGameController blackjackGameController = new BlackjackGameController(blackjackGameInteractor);
        blackjackBetView.setBlackjackBetController(blackjackBetController);
        blackjackBetView.setBlackjackGameController(blackjackGameController);
        return this;
    }

    /**
     * Adds the shop main menu use case to the application.
     * @return this builder
     */
    public AppBuilder addShopUseCase() {
        final ShopOutputBoundary shopOutputBoundary = new ShopPresenter(viewManagerModel,
                shopWheelViewModel, menuViewModel, shopButtonViewModel, shopMainViewModel);
        final ShopInputBoundary userShopInteractor = new ShopInteractor(shopOutputBoundary);

        final ShopController shopController = new ShopController(userShopInteractor);
        shopMainView.setShopController(shopController);
        return this;
    }

    /**
     * Adds the Blackjack Game Use Case to the application.
     * @return this builder
     */
    public AppBuilder addBlackjackGameUseCase() {

        final BlackjackHitOutputBoundary blackjackHitOutputBoundary = new BlackjackHitPresenter(
                signupViewModel, blackjackGameViewModel, gameMenuViewModel, viewManagerModel);
        final BlackjackHitInputBoundary blackjackHitInputBoundary = new BlackjackHitInteractor(
                blackjackHitOutputBoundary, cardDeckDataAccessObject, blackjackGame);

        final BlackjackStandOutputBoundary blackjackStandOutputBoundary = new BlackjackStandPresenter(
                signupViewModel, blackjackGameViewModel, gameMenuViewModel, viewManagerModel,
                blackjackBetViewModel);
        BlackjackStandInputBoundary blackjackStandInputBoundary = new BlackjackStandInteractor(
                blackjackStandOutputBoundary, blackjackGame, cardDeckDataAccessObject
        );

        final BlackjackHitController hitController = new BlackjackHitController(blackjackHitInputBoundary);
        final BlackjackStandController standController = new BlackjackStandController(blackjackStandInputBoundary);

        final BlackjackGameOutputBoundary blackjackGameOutputBoundary = new BlackjackGamePresenter(
                signupViewModel, blackjackGameViewModel, gameMenuViewModel, viewManagerModel, menuViewModel,
                blackjackBetViewModel
        );

        final BlackjackGameInputBoundary blackjackGameInteractor = new BlackjackGameInteractor(
                blackjackGameOutputBoundary, userDataAccessObject, cardDeckDataAccessObject, hitController,
                standController, userFactory, blackjackGame
        );

        final BlackjackGameController blackjackGameController = new BlackjackGameController(blackjackGameInteractor);

        blackjackGameView.setBlackjackGameController(blackjackGameController);
        return this;
    }

    /**
     * Adds the shop button use case to the application.
     * @return this builder
     */
    public AppBuilder addShopButtonUseCase() {
        final ShopButtonOutputBoundary shopButtonOutputBoundary = new ShopButtonPresenter(viewManagerModel,
                shopMainViewModel, shopButtonViewModel);
        final ShopButtonInputBoundary userShopButtonInteractor = new ShopButtonInteractor(shopButtonOutputBoundary,
                userDataAccessObject, userFactory);

        final ShopButtonController shopButtonController = new ShopButtonController(userShopButtonInteractor);
        shopButtonView.setShopButtonController(shopButtonController);
        return this;
    }

    /**
     * Adds the shop wheel use case to the application.
     * @return this builder
     */
    public AppBuilder addShopWheelUseCase() {
        final ShopWheelOutputBoundary shopWheelOutputBoundary = new ShopWheelPresenter(viewManagerModel,
                shopMainViewModel, shopWheelViewModel);
        final ShopWheelInputBoundary userShopWheelInteractor = new ShopWheelInteractor(shopWheelOutputBoundary,
                userDataAccessObject, userFactory);

        final ShopWheelController shopWheelController = new ShopWheelController(userShopWheelInteractor);
        shopWheelView.setShopWheelController(shopWheelController);
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the WelcomeView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("LoseYourMoney.com");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(welcomeView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
