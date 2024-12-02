package data_access;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import entity.User;
import entity.UserFactory;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import use_case.Over_Under.bet.OverUnderBetDataAccessInterface;
import use_case.Over_Under.play.OverUnderUserPlayDataAccessInterface;
import use_case.add_friend.AddFriendUserDataAccessInterface;
import use_case.blackjack.bet.BlackjackBetUserDataAccessInterface;
import use_case.blackjack.game.BlackjackGameInputBoundary;
import use_case.blackjack.game.BlackjackGameUserDataAccessInterface;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.gaunlet.bet.GaunletBetDataAccessInterface;
import use_case.gaunlet.guess.GaunletGuessUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.shopbutton.ShopButtonUserDataAccessInterface;
import use_case.shopwheel.ShopWheelUserDataAccessInterface;
import use_case.remove_friend.RemoveFriendUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * The DAO for user data.
 */
public class DBUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        GaunletBetDataAccessInterface,
        GaunletGuessUserDataAccessInterface,
        AddFriendUserDataAccessInterface,
        ShopWheelUserDataAccessInterface,
        ShopButtonUserDataAccessInterface,
        RemoveFriendUserDataAccessInterface,
        BlackjackBetUserDataAccessInterface,
        BlackjackGameUserDataAccessInterface {
        OverUnderUserPlayDataAccessInterface,
        OverUnderBetDataAccessInterface {

    private static final int SUCCESS_CODE = 200;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String MESSAGE = "message";
    private final UserFactory userFactory;

    // TODO:
    private String currentUsername;
    private int currentBet;

    public DBUserDataAccessObject(UserFactory userFactory) {
        this.userFactory = userFactory;
        // No need to do anything to reinitialize a user list! The data is the cloud that may be miles away.
        currentBet = 0;
    }

    // TODO:
    @Override
    public void addFriend(User user) {

    }

    /**
     * Returns the user from the list of users given a username.
     * @param username is the user's name key.
     * @return the user associated with the given key.
     */
    @Override
    public User get(String username) {
        // Make an API call to get the user object.
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/user?username=%s", username))
                .addHeader("Content-Type", CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                final JSONObject userJSONObject = responseBody.getJSONObject("user");
                final String name = userJSONObject.getString(USERNAME);
                final String password = userJSONObject.getString(PASSWORD);
                final JSONObject data = userJSONObject.getJSONObject("info");
                // System.out.println("Get method in DB: " + name + ", " + password + ", " + data);

                return userFactory.create(name, password, data);
            }
            else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void setCurrentUsername(String name) {
        // this isn't implemented for the lab
        this.currentUsername = name;
    }

    @Override
    public boolean existsByName(String username) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/checkIfUserExists?username=%s", username))
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            return responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE;
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void setBet(int bet) {
        this.currentBet = bet;
        System.out.println("DB setbet bet: " + bet);
    }

    @Override
    public void save(User user) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        // POST METHOD
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put(USERNAME, user.getName());
        requestBody.put(PASSWORD, user.getPassword());
        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/user")
                .method("POST", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                // success!
            }
            else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public int getBet() {
        return currentBet;
    }

    @Override
    public void saveNew(User user, JSONObject info) {
        // SAVE
        final OkHttpClient client1 = new OkHttpClient().newBuilder()
                .build();

        // POST METHOD
        final MediaType mediaType1 = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody1 = new JSONObject();
        requestBody1.put(USERNAME, user.getName());
        requestBody1.put(PASSWORD, user.getPassword());
        requestBody1.put("info", info);
        final RequestBody body1 = RequestBody.create(requestBody1.toString(), mediaType1);
        final Request request1 = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/modifyUserInfo")
                .method("PUT", body1)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response1 = client1.newCall(request1).execute();

            final JSONObject responseBody1 = new JSONObject(response1.body().string());

            if (responseBody1.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                // success
            }
            else {
                throw new RuntimeException(responseBody1.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void changePassword(User user) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        // POST METHOD
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put(USERNAME, user.getName());
        requestBody.put(PASSWORD, user.getPassword());
        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/user")
                .method("PUT", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                // success!
            }
            else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String getCurrentUsername() {
        return this.currentUsername;
    }
}
