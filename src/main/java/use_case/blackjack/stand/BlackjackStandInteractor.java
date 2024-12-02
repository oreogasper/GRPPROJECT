package use_case.blackjack.stand;

import entity.AbstractCard;
import entity.BlackjackGame;
import entity.User;
import entity.UserFactory;
import org.json.JSONObject;
import use_case.blackjack.hit.BlackjackHitInputBoundary;
import use_case.blackjack.hit.BlackjackHitInputData;
import use_case.blackjack.hit.BlackjackHitInteractor;

import java.util.ArrayList;
import java.util.List;

/**
 * The Blackjack Stand Use Case Interactor.
 */
public class BlackjackStandInteractor implements BlackjackStandInputBoundary {
    private final BlackjackStandOutputBoundary outputBoundary;
    private final BlackjackGame blackjackGame;
    private final BlackjackStandCardDeckDataAccessInterface cardDeckDataAccessInterface;
    private final BlackjackStandUserDataAccessInterface userDataAccessInterface;
    private final UserFactory userFactory;

    public BlackjackStandInteractor(BlackjackStandOutputBoundary outputBoundary,
                                    BlackjackGame blackjackGame,
                                    BlackjackStandCardDeckDataAccessInterface cardDeckDataAccessInterface,
                                    BlackjackStandUserDataAccessInterface userDataAccessInterface,
                                    UserFactory userFactory) {
        this.outputBoundary = outputBoundary;
        this.blackjackGame = blackjackGame;
        this.cardDeckDataAccessInterface = cardDeckDataAccessInterface;
        this.userDataAccessInterface = userDataAccessInterface;
        this.userFactory = userFactory;

    }

    @Override
    public void execute(BlackjackStandInputData blackjackStandInputData) {
        final String deckId = cardDeckDataAccessInterface.getDeckID();
        List<Integer> dealerScores = new ArrayList<>();
        List<AbstractCard> dealerCards = new ArrayList<>();

        while (blackjackGame.getDealerScore() < 17) {
            final AbstractCard card = cardDeckDataAccessInterface.drawCard(deckId);
            blackjackGame.addDealerCard(card);
            dealerCards.add(card);
            dealerScores.add(blackjackGame.getDealerScore());

        }

        String turnState = null;
        if (blackjackGame.isBust(blackjackGame.getDealerCards())) {
            turnState = "Win";
        } else if (blackjackGame.isBust(blackjackGame.getPlayerCards())) {
            turnState = "Lose";
        } else if (blackjackGame.getPlayerScore() > blackjackGame.getDealerScore()) {
            turnState = "Win";
        } else if (blackjackGame.getPlayerScore() < blackjackGame.getDealerScore()) {
            turnState = "Lose";
        } else {
            turnState = "Draw";
        }

        final int amountWon = calculateAmountWon(turnState, blackjackStandInputData);

        updateUserStats(turnState, blackjackStandInputData);

        BlackjackStandOutputData standOutputData = new BlackjackStandOutputData(turnState,
                false, dealerScores, dealerCards, amountWon);

        outputBoundary.prepareSuccessView(standOutputData);
    }

    private int calculateAmountWon(String turnState, BlackjackStandInputData standInputData) {
        final User user = userDataAccessInterface.get(standInputData.getUsername());
        if (turnState.equals("Win")) {
            return standInputData.getBet();

        } else if (turnState.equals("Lose")) {
            return -standInputData.getBet();
        } else {
            return 0;
        }
    }

    private void updateUserStats(String turnState, BlackjackStandInputData standInputData) {
        final User user = userDataAccessInterface.get(standInputData.getUsername());
        final JSONObject json = user.getInfo();

        if (turnState.equals("Win")) {
            json.put("balance", standInputData.getBet() * 2 + user.getBalance());
            json.put("wins", user.getWins() + 1);
            json.put("games", user.getGames() + 1);

            final User updatedUser = userFactory.create(user.getName(), user.getPassword(), json);
            userDataAccessInterface.saveNew(updatedUser, json);
        } else if (turnState.equals("Lose")) {
            json.put("balance", user.getBalance());
            json.put("loses", user.getLosses() + 1);
            json.put("games", user.getGames() + 1);

            final User updatedUser = userFactory.create(user.getName(), user.getPassword(), json);
            userDataAccessInterface.saveNew(updatedUser, json);
        } else {
            json.put("balance", user.getBalance() + userDataAccessInterface.getBet());
            json.put("games", user.getGames() + 1);

            final User updatedUser = userFactory.create(user.getName(), user.getPassword(), json);
            userDataAccessInterface.saveNew(updatedUser, json);
        }

    }

    @Override
    public void switchToGameMenuView() {
        outputBoundary.switchToGameMenuView();
    }

    @Override
    public void switchToBetView() {
        blackjackGame.resetGame();
        outputBoundary.switchToBetView();
    }
}
