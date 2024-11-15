package app;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import javax.swing.JFrame;

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
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
        e.printStackTrace();
        }
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                                            .addLoginView()
                                            .addSignupView()
                                            .addLoggedInView()
                                            .addWelcomeView()
                                            .addStatisticsView()
                                            .addMenuView()
                                            .addGameMenuView()
                                            .addGameMenuUseCase()
                                            .addWelcomeUseCase()
                                            .addSignupUseCase()
                                            .addLoginUseCase()
                                            .addStatisticsUseCase()
                                            .addChangePasswordUseCase()
                                            .addLogoutUseCase()
                                            .addMenuUseCase()
                                            .build();

        application.pack();
        application.setVisible(true);
    }
}
