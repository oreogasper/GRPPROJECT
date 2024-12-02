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

    public BlackjackStandInteractor(BlackjackStandOutputBoundary outputBoundary,
                                    BlackjackGame blackjackGame,
                                    BlackjackStandCardDeckDataAccessInterface cardDeckDataAccessInterface) {
        this.outputBoundary = outputBoundary;
        this.blackjackGame = blackjackGame;
        this.cardDeckDataAccessInterface = cardDeckDataAccessInterface;

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


        BlackjackStandOutputData standOutputData = new BlackjackStandOutputData(turnState,
                false, dealerScores, dealerCards);

        outputBoundary.prepareSuccessView(standOutputData);
    }

    @Override
    public void switchToGameMenuView() {
        outputBoundary.switchToGameMenuView();
    }

    @Override
    public void switchToBetView() {
        outputBoundary.switchToBetView();
    }
}
