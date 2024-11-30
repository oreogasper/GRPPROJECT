package use_case.gaunlet.bet;

import entity.User;
import entity.UserFactory;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GaunletBetInteractorTest {

    // simple presenter class to track correct method being called
    static class CapturingPresenter implements GaunletBetOutputBoundary {

        private String lastMethodCalled = null;
        private GaunletBetOutputData outputData = null;
        private String errorMessage = null;

        @Override
        public void prepareSuccessView(GaunletBetOutputData response) {
            lastMethodCalled = "prepareSuccessView";
            this.outputData = response;
        }

        @Override
        public void prepareFailView(String error) {
            lastMethodCalled = "prepareFailView";
            this.errorMessage = error;
        }

        @Override
        public void switchToGaunletGuessView() {
            lastMethodCalled = "switchToGaunletGuessView";
        }

        @Override
        public void switchToGameMenuView() {
            lastMethodCalled = "switchToGameMenuView";
        }

        @Override
        public void setUserBet() {
            lastMethodCalled = "setUserBet";
        }

        public String getLastMethodCalled() {
            return lastMethodCalled;
        }

        public GaunletBetOutputData getOutputData() {
            return outputData;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }

    // Simpler implementation of GaunletBetDataAccessInterface
    static class SimpGaunletBetDataAccessInterface implements GaunletBetDataAccessInterface {

        private User storedUser;

        @Override
        public User get(String username) {
            // Return a mock user object
            JSONObject info = new JSONObject();
            info.put("balance", 100);
            return new TestUser(username, "password", info);
        }

        @Override
        public void setBet(int bet) {
        }

        @Override
        public void saveNew(User user, JSONObject json) {
            this.storedUser = user;
        }

        public User getStoredUser() {
            return storedUser;
        }
    }

    // mock of entity UserFactory
    static class MockUserFactory implements UserFactory {
        @Override
        public User create(String name, String password, JSONObject info) {
            return new TestUser(name, password, info);
        }
    }

    @Test
    void successTest() {
        GaunletBetInputData inputData = new GaunletBetInputData("valk", 11);

        SimpGaunletBetDataAccessInterface dataAccess = new SimpGaunletBetDataAccessInterface();
        MockUserFactory userFactory = new MockUserFactory();
        CapturingPresenter presenter = new CapturingPresenter();

        // Creating nteractor instance
        GaunletBetInteractor interactor = new GaunletBetInteractor(dataAccess, presenter, userFactory);

        interactor.execute(inputData, 11);

        // check the results of execute method
        assertEquals("prepareSuccessView", presenter.getLastMethodCalled());
        assertNotNull(presenter.getOutputData());
        assertEquals(11, presenter.getOutputData().getBet());
        assertFalse(presenter.getOutputData().isUseCaseFailed());
        assertEquals(89, dataAccess.getStoredUser().getBalance());
    }

    @Test
    void switchToGaunletGuessViewTest() {
        CapturingPresenter presenter = new CapturingPresenter();

        GaunletBetInteractor interactor = new GaunletBetInteractor(null, presenter, null);
        interactor.switchToGaunletGuessView();

        assertEquals("switchToGaunletGuessView", presenter.getLastMethodCalled());
    }

    @Test
    void invalidBetTest() {
        // Input data with invalid bet amount
        GaunletBetInputData inputData = new GaunletBetInputData("valk", 5); // Min bet is 10

        SimpGaunletBetDataAccessInterface dataAccess = new SimpGaunletBetDataAccessInterface();
        MockUserFactory userFactory = new MockUserFactory();
        CapturingPresenter presenter = new CapturingPresenter();

        GaunletBetInteractor interactor = new GaunletBetInteractor(dataAccess, presenter, userFactory);
        interactor.execute(inputData, 5);

        assertEquals("prepareFailView", presenter.getLastMethodCalled());
        assertEquals("Invalid bet amount. Please bet a value between 10 tokens and your current balance.",
                presenter.getErrorMessage());
    }

    @Test
    void switchToGameMenuViewTest() {
        CapturingPresenter presenter = new CapturingPresenter();

        GaunletBetInteractor interactor = new GaunletBetInteractor(null, presenter, null);
        interactor.switchToGameMenuView();

        assertEquals("switchToGameMenuView", presenter.getLastMethodCalled());
    }
}

// a simple User class for testing
class TestUser implements User {
    private final String name;
    private final String password;
    private final JSONObject info;

    public TestUser(String name, String password, JSONObject info) {
        this.name = name;
        this.password = password;
        this.info = info;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        // No-op
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        // No-op
    }

    @Override
    public JSONObject getInfo() {
        return info;
    }

    @Override
    public void setBet(int bet) {
        info.put("bet", bet);
    }

    @Override
    public int getBet() {
        return info.getInt("bet");
    }

    @Override
    public void setFriend(String friend) {
        // No-op
    }

    @Override
    public int getBalance() {
        return info.getInt("balance");
    }

    @Override
    public void updateBalance(int amount) {
        int balance = info.getInt("balance");
        info.put("balance", balance + amount);
    }

    @Override
    public int getWins() {
        return 0;
    }

    @Override
    public void wonGame() {
        // No-op
    }

    @Override
    public int getLosses() {
        return 0;
    }

    @Override
    public void lostGame() {
        // No-op
    }

    @Override
    public int getGames() {
        return 0;
    }

    @Override
    public long getLastSpin() {
        return 0;
    }

    @Override
    public void setLastSpin(long lastSpin) {
        // No-op
    }
}
