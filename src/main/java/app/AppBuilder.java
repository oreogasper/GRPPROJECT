package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordPresenter;
import interface_adapter.change_password.LoggedInViewModel;
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
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.statistics.StatisticsController;
import interface_adapter.statistics.StatisticsPresenter;
import interface_adapter.statistics.StatisticsViewModel;
import interface_adapter.welcome.WelcomeController;
import interface_adapter.welcome.WelcomePresenter;
import interface_adapter.welcome.WelcomeViewModel;
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
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.menu.MenuInputBoundary;
import use_case.menu.MenuInteractor;
import use_case.menu.MenuOutputBoundary;
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
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // thought question: is the hard dependency below a problem?
    private final InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();
    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private LoggedInViewModel loggedInViewModel;
    private LoggedInView loggedInView;
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

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
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
     * Adds the LoggedIn View to the application.
     * @return this builder
     */
    public AppBuilder addLoggedInView() {
        loggedInViewModel = new LoggedInViewModel();
        loggedInView = new LoggedInView(loggedInViewModel);
        cardPanel.add(loggedInView, loggedInView.getViewName());
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
     * Adds the Welcome Use Case to the application.
     * @return this builder
     */
    public AppBuilder addWelcomeUseCase() {
        final WelcomeOutputBoundary welcomeOutputBoundary = new WelcomePresenter(viewManagerModel,
                loggedInViewModel, loginViewModel, signupViewModel);
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
                signupViewModel, gaunletGuessViewModel, gameMenuViewModel);
        final GaunletGuessInputBoundary userGaunletGuessInteractor = new GaunletGuessInteractor(
                gaunletGuessOutputBoundary);

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
        final GaunletBetInputBoundary userGaunletBetInteractor = new GaunletBetInteractor(gaunletBetOutputBoundary);

        final GaunletBetController gaunletBetcontroller = new GaunletBetController(userGaunletBetInteractor);
        gaunletBetView.setGaunletBetController(gaunletBetcontroller);
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel, welcomeViewModel);
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
                loggedInViewModel, loginViewModel, welcomeViewModel, menuViewModel);
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
                loginViewModel, statisticsViewModel, gameMenuViewModel);
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
                loginViewModel, menuViewModel, gaunletBetViewModel);
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
                new ChangePasswordPresenter(loggedInViewModel);

        final ChangePasswordInputBoundary changePasswordInteractor =
                new ChangePasswordInteractor(userDataAccessObject, changePasswordOutputBoundary, userFactory);

        final ChangePasswordController changePasswordController =
                new ChangePasswordController(changePasswordInteractor);
        loggedInView.setChangePasswordController(changePasswordController);
        return this;
    }

    /**
     * Adds the Logout Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel, welcomeViewModel);

        final LogoutInputBoundary logoutInteractor =
                new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(logoutInteractor);
        loggedInView.setLogoutController(logoutController);
        return this;
    }

    /**
     * Adds the Statistics Use Case to the application.
     * @return this builder
     */
    public AppBuilder addStatisticsUseCase() {
        final StatisticsOutputBoundary statisticsOutputBoundary = new StatisticsPresenter(viewManagerModel,
                statisticsViewModel, loginViewModel, welcomeViewModel);
        final StatisticsInputBoundary userStatisticsInteractor = new StatisticsInteractor(
                statisticsOutputBoundary);

        final StatisticsController statisticsController = new StatisticsController(userStatisticsInteractor);
        statsView.setStatisticsController(statisticsController);
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
