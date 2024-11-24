package app;

import entity.PlayingDeck;
import use_case.Over_Under.OverUnderGamePresenter;
import use_case.Over_Under.OverUnderInputBoundary;
import use_case.Over_Under.OverUnderInteractor;
import use_case.Over_Under.OverUnderOutputBoundary;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * The Main class of our application.
 */
public class Main {
    // Make playingDeck static
    static PlayingDeck playingDeck = new PlayingDeck();
    private static OverUnderOutputBoundary overUnderOutputBoundary = new OverUnderGamePresenter(view);
    static OverUnderInputBoundary inputBoundary = new OverUnderInteractor(playingDeck, deckSize, overUnderOutputBoundary);

    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addOverUnderUseCase()
                .addLoginView()
                .addSignupView()
                .addLoggedInView()
                .addWelcomeView()
                .addStatisticsView()
                .addMenuView()
                .addGaunletBetView()
                .addGameMenuView()
                .addGaunletGuessView()
                .addGameMenuUseCase()
                .addWelcomeUseCase()
                .addSignupUseCase()
                .addGaunletBetUseCase()
                .addLoginUseCase()
                .addStatisticsUseCase()
                .addChangePasswordUseCase()
                .addLogoutUseCase()
                .addMenuUseCase()
                .addGaunletGuessUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}