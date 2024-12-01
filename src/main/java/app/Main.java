package app;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (UnsupportedLookAndFeelException evt) {
            evt.printStackTrace();

        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException evt) {
        evt.printStackTrace();
        }
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                                            .addLoginView()
                                            .addSignupView()
                                            .addWelcomeView()
                                            .addStatisticsView()
                                            .addMenuView()
                                            .addGaunletBetView()
                                            .addBlackjackBetView()
                                            .addBlackjackGameView()
                                            .addGameMenuView()
                                            .addGaunletGuessView()
                                            .addShopMainView()
                                            .addShopButtonView()
                                            .addShopWheelView()
                                            .addLeaderboardView()
                                            .addGameMenuUseCase()
                                            .addWelcomeUseCase()
                                            .addSignupUseCase()
                                            .addGaunletBetUseCase()
                                            .addLoginUseCase()
                                            .addAddFriendUseCase()
                                            .addRemoveFriendUseCase()
                                            .addStatisticsUseCase()
                                            .addChangePasswordUseCase()
                                            .addLogoutUseCase()
                                            .addMenuUseCase()
                                            .addGaunletGuessUseCase()
                                            .addGaunletBetUseCase()
                                            .addBlackjackBetUseCase()
                                            .addBlackjackGameUseCase()
                                            .addShopUseCase()
                                            .addShopButtonUseCase()
                                            .addOverUnderBetView()
                                            .addOverUnderBetUseCase()
                                            .addOverUnderPlayView()
                                            .addOverUnderPlayUseCase()
                                            .addShopWheelUseCase()
                                            .addLeaderboardUseCase()
                                            .build();

        application.pack();
        application.setVisible(true);
    }
}
