package data_access;

import entity.AbstractCard;
import entity.CardFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class DBCardDeckDataAccessObjectTest {

    @Test
    void createNewDeckTestNoExceptions() {
        final CardFactory cardFactory = new CardFactory();
        final DBCardDeckDataAccessObject cardDeckDataAccessObject = new DBCardDeckDataAccessObject(cardFactory);

        cardDeckDataAccessObject.createNewDeck();

    }

    @Test
    void createNewDeckTestDeckId() {
        final CardFactory cardFactory = new CardFactory();
        final DBCardDeckDataAccessObject cardDeckDataAccessObject = new DBCardDeckDataAccessObject(cardFactory);

        cardDeckDataAccessObject.createNewDeck();
        assertNotNull(cardDeckDataAccessObject.getDeckID());
    }

    @Test
    void drawCardTest() {
        final CardFactory cardFactory = new CardFactory();
        final DBCardDeckDataAccessObject cardDeckDataAccessObject = new DBCardDeckDataAccessObject(cardFactory);

        cardDeckDataAccessObject.createNewDeck();
        AbstractCard card = cardDeckDataAccessObject.drawCard(cardDeckDataAccessObject.getDeckID());
        assertNotNull(card.getName());
        assertNotNull(card.getImage());

    }
}
