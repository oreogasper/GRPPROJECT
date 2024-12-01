package data_access;

import entity.Card;
import entity.CardFactory;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * DAO for interacting with the Deck of Cards API for the Over/Under game.
 */
public class OverUnderCardDataAccess {
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String SUCCESS_CODE_LABEL = "success";
    private static final String API_BASE_URL = "https://www.deckofcardsapi.com/api/deck";

    private final CardFactory cardFactory;
    private String currentDeckId;

    public OverUnderCardDataAccess(CardFactory cardFactory) {
        this.cardFactory = cardFactory;
    }

    /**
     * Creates a new shuffled deck.
     */
    public void createNewDeck() {
        final OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(API_BASE_URL + "/new/shuffle/")
                .addHeader("Content-Type", CONTENT_TYPE_JSON)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Failed to create a new deck. API call unsuccessful.");
            }

            final JSONObject responseBody = new JSONObject(response.body().string());
            if (responseBody.getBoolean(SUCCESS_CODE_LABEL)) {
                currentDeckId = responseBody.getString("deck_id");
            } else {
                throw new RuntimeException("Failed to create a new deck. Response invalid.");
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException("Error during deck creation: " + e.getMessage(), e);
        }
    }

    /**
     * Draws a single card from the current deck.
     *
     * @return The drawn card as a Card entity.
     */
    public Card drawCard() {
        if (currentDeckId == null) {
            throw new IllegalStateException("Deck has not been initialized. Please create a deck first.");
        }

        final OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(API_BASE_URL + "/" + currentDeckId + "/draw/?count=1")
                .addHeader("Content-Type", CONTENT_TYPE_JSON)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Failed to draw a card. API call unsuccessful.");
            }

            final JSONObject responseBody = new JSONObject(response.body().string());
            if (responseBody.getBoolean(SUCCESS_CODE_LABEL)) {
                final JSONArray cards = responseBody.getJSONArray("cards");
                final JSONObject cardJSON = cards.getJSONObject(0);

                // Parse card details
                final String stringValue = cardJSON.getString("value");
                final int value = parseCardValue(stringValue);
                final String suit = cardJSON.getString("suit");

                return cardFactory.create(value, suit, stringValue);
            } else {
                throw new RuntimeException("Failed to draw a card. Response invalid.");
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException("Error during card drawing: " + e.getMessage(), e);
        }
    }

    /**
     * Parses the card's value as an integer based on game rules.
     *
     * @param stringValue The string value of the card (e.g., "KING", "2").
     * @return The integer value of the card.
     */
    private int parseCardValue(String stringValue) {
        return switch (stringValue) {
            case "ACE" -> 1;
            case "KING" -> 13;
            case "QUEEN" -> 12;
            case "JACK" -> 11;
            default -> Integer.parseInt(stringValue);
        };
    }

    /**
     * Shuffles the current deck.
     */
    public void shuffleDeck() {
        if (currentDeckId == null) {
            throw new IllegalStateException("Deck has not been initialized. Please create a deck first.");
        }

        final OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(API_BASE_URL + "/" + currentDeckId + "/shuffle/")
                .addHeader("Content-Type", CONTENT_TYPE_JSON)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Failed to shuffle the deck. API call unsuccessful.");
            }

            final JSONObject responseBody = new JSONObject(response.body().string());
            if (!responseBody.getBoolean(SUCCESS_CODE_LABEL)) {
                throw new RuntimeException("Deck shuffling failed.");
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException("Error during deck shuffling: " + e.getMessage(), e);
        }
    }
}
