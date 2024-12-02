package use_case.blackjack.game;

import entity.AbstractCard;
import entity.BlackjackGame;
import entity.User;
import entity.UserFactory;
import interface_adapter.blackjack.game.hit.BlackjackHitController;
import interface_adapter.blackjack.game.stand.BlackjackStandController;
import org.json.JSONObject;
import use_case.blackjack.hit.BlackjackHitInputData;
import use_case.blackjack.stand.BlackjackStandInputData;

import java.util.ArrayList;
import java.util.List;

/**
 * The Blackjack Game Use Case Interactor.
 */
public class BlackjackGameInteractor implements BlackjackGameInputBoundary{

    private final BlackjackGameOutputBoundary outputBoundary;
    private final BlackjackGameUserDataAccessInterface dataAccessInterface;
    private final BlackjackGameCardDataAccessInterface cardAccessInterface;
    private final BlackjackHitController hitController;
    private final BlackjackStandController standController;
    private final UserFactory userFactory;
    private final BlackjackGame blackjackGame;

    public BlackjackGameInteractor(BlackjackGameOutputBoundary outputBoundary,
                                   BlackjackGameUserDataAccessInterface dataAccessInterface,
                                   BlackjackGameCardDataAccessInterface cardAccessInterface,
                                   BlackjackHitController hitController,
                                   BlackjackStandController standController, UserFactory userFactory, BlackjackGame blackjackGame) {
        this.outputBoundary = outputBoundary;
        this.dataAccessInterface = dataAccessInterface;
        this.cardAccessInterface = cardAccessInterface;
        this.hitController = hitController;
        this.standController = standController;
        this.userFactory = userFactory;
        this.blackjackGame = blackjackGame;
    }

    @Override
    public void execute(BlackjackGameInputData blackjackGameInputData) {
        if (blackjackGameInputData.getUseCaseName().equals("Hit")) {
            hitController.execute();
        } else if (blackjackGameInputData.getUseCaseName().equals("Stand")) {
            standController.execute();
        } else if (blackjackGameInputData.getUseCaseName().equals("Start")) {
            startGame(blackjackGameInputData);
        } else if (blackjackGameInputData.getUseCaseName().equals("Stop")) {
            endGame(blackjackGameInputData);
        } else if (blackjackGameInputData.getUseCaseName().equals("Play Again")) {
            playAgain();
        }
    }

    private void startGame(BlackjackGameInputData blackjackGameInputData) {
        initializeBlackjackGame();
        final int dealerHiddenScore = blackjackGame.getDealerCards().get(0).getRank();

        final BlackjackGameOutputData outputData = new BlackjackGameOutputData("Start", 0,
                blackjackGame.getPlayerCardImages(), blackjackGame.getDealerCardImages(),
                blackjackGame.getPlayerScore(), blackjackGame.getDealerScore(), dealerHiddenScore);

        outputBoundary.prepareSuccessView(outputData);
    }

    private void initializeBlackjackGame() {
        if (!cardAccessInterface.hasDeck()) {
            cardAccessInterface.createNewDeck();
        }
        cardAccessInterface.shuffleDeck(cardAccessInterface.getDeckID());

        List<AbstractCard> playerCards = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            playerCards.add(cardAccessInterface.drawCard(cardAccessInterface.getDeckID()));
        }

        List<AbstractCard> dealerCards = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            dealerCards.add(cardAccessInterface.drawCard(cardAccessInterface.getDeckID()));
        }

        for (AbstractCard card : playerCards) {
            blackjackGame.addPlayerCard(card);
        }

        for (AbstractCard card : dealerCards) {
            blackjackGame.addDealerCard(card);
        }
    }



    private void playAgain() {
        BlackjackGameOutputData outputData = new BlackjackGameOutputData("Play Again", 0,
                null, null, 0, 0, 0);
        outputBoundary.prepareSuccessView(outputData);
    }

    private void endGame(BlackjackGameInputData blackjackGameInputData) {
        cardAccessInterface.shuffleDeck(cardAccessInterface.getDeckID());
        blackjackGame.resetGame();

        final String turnState = blackjackGameInputData.getGameState();
        final int bet = blackjackGameInputData.getBet();
        final int amountWon = calculateAmountWon(turnState, bet);

        final String username = blackjackGameInputData.getUser().getName();

        updateUserStats(turnState, username, bet);

        BlackjackGameOutputData outputData = new BlackjackGameOutputData("Stop", amountWon,
                null, null, 0, 0, 0);
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
