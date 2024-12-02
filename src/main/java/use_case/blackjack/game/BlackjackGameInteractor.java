package use_case.blackjack.game;

import entity.User;
import entity.UserFactory;
import interface_adapter.blackjack.game.hit.BlackjackHitController;
import interface_adapter.blackjack.game.stand.BlackjackStandController;
import org.json.JSONObject;
import use_case.blackjack.hit.BlackjackHitInputData;
import use_case.blackjack.stand.BlackjackStandInputData;

/**
 * The Blackjack Game Use Case Interactor.
 */
public class BlackjackGameInteractor implements BlackjackGameInputBoundary{

    private final BlackjackGameOutputBoundary outputBoundary;
    private final BlackjackGameUserDataAccessInterface dataAccessInterface;
    private final BlackjackHitController hitController;
    private final BlackjackStandController standController;
    private final UserFactory userFactory;

    public BlackjackGameInteractor(BlackjackGameOutputBoundary outputBoundary,
                                   BlackjackGameUserDataAccessInterface dataAccessInterface,
                                   BlackjackHitController hitController,
                                   BlackjackStandController standController, UserFactory userFactory) {
        this.outputBoundary = outputBoundary;
        this.dataAccessInterface = dataAccessInterface;
        this.hitController = hitController;
        this.standController = standController;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(BlackjackGameInputData blackjackGameInputData) {
        if (blackjackGameInputData.getUseCaseName().equals("Hit")) {
            hitController.execute();
        } else if (blackjackGameInputData.getUseCaseName().equals("Stand")) {
            standController.execute();
        } else if (blackjackGameInputData.getUseCaseName().equals("Start")) {

        } else if (blackjackGameInputData.getUseCaseName().equals("Stop")) {
            endGame(blackjackGameInputData);
        }
    }

    private void endGame(BlackjackGameInputData blackjackGameInputData) {
        final String turnState = blackjackGameInputData.getGameState();
        final int bet = blackjackGameInputData.getBet();
        final int amountWon = calculateAmountWon(turnState, bet);

        final String username = blackjackGameInputData.getUser().getName();

        updateUserStats(turnState, username, bet);

        BlackjackGameOutputData outputData = new BlackjackGameOutputData("Stop", amountWon);
        outputBoundary.prepareSuccessView(outputData);
    }

    private void updateUserStats(String turnState, String username, int bet) {
        final User user = dataAccessInterface.get(username);
        final JSONObject json = user.getInfo();

        if (turnState.equals("Win")) {
            json.put("balance", bet * 2 + user.getBalance());
            json.put("wins", user.getWins() + 1);
            json.put("games", user.getGames() + 1);

            final User updatedUser = userFactory.create(user.getName(), user.getPassword(), json);
            dataAccessInterface.saveNew(updatedUser, json);
        } else if (turnState.equals("Lose")) {
            json.put("balance", user.getBalance());
            json.put("loses", user.getLosses() + 1);
            json.put("games", user.getGames() + 1);

            final User updatedUser = userFactory.create(user.getName(), user.getPassword(), json);
            dataAccessInterface.saveNew(updatedUser, json);
        } else {
            json.put("balance", user.getBalance() + bet);
            json.put("games", user.getGames() + 1);

            final User updatedUser = userFactory.create(user.getName(), user.getPassword(), json);
            dataAccessInterface.saveNew(updatedUser, json);
        }

    }

    private int calculateAmountWon(String turnState, int bet) {
        if (turnState.equals("Win")) {
            return bet;

        } else if (turnState.equals("Lose")) {
            return -bet;
        } else {
            return 0;
        }
    }

    @Override
    public void switchToLoginView() {
        outputBoundary.switchToGameMenuView();
    }
}
