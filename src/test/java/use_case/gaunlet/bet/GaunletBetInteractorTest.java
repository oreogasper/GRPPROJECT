package use_case.gaunlet.bet;

import entity.CommonUser;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GaunletBetInteractorTest {

    // CapturingPresenter to track method calls
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

    // Stub implementation of GaunletBetDataAccessInterface
    static class StubGaunletBetDataAccess implements GaunletBetDataAccessInterface {

        private User storedUser;

        @Override
        public User get(String username) {
            // Return a mock user object
            JSONObject info = new JSONObject();
            info.put("balance", 100);
            return new ConcreteUser(username, "password", info);
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

    // Mock UserFactory
    static class MockUserFactory implements UserFactory {
        @Override
        public User create(String name, String password, JSONObject info) {
            return new ConcreteUser(name, password, info);
        }
    }

    @Test
    void successTest() {
        // Input data for the interactor
        GaunletBetInputData inputData = new GaunletBetInputData("valk", 11);

        // Dependencies
        StubGaunletBetDataAccess dataAccess = new StubGaunletBetDataAccess();
        MockUserFactory userFactory = new MockUserFactory();
        CapturingPresenter presenter = new CapturingPresenter();

        // Interactor instance
        GaunletBetInteractor interactor = new GaunletBetInteractor(dataAccess, presenter, userFactory);

        // Execute the use case
        interactor.execute(inputData, 11);

        // Assertions
        assertEquals("prepareSuccessView", presenter.getLastMethodCalled());
        assertNotNull(presenter.getOutputData());
        assertEquals(11, presenter.getOutputData().getBet());
        assertFalse(presenter.getOutputData().isUseCaseFailed());
        assertEquals(89, dataAccess.getStoredUser().getBalance());
    }

    @Test
    void switchToGaunletGuessViewTest() {
        // Set up CapturingPresenter
        CapturingPresenter presenter = new CapturingPresenter();

        // Interactor instance with minimal dependencies
        GaunletBetInteractor interactor = new GaunletBetInteractor(null, presenter, null);

        // Call the method
        interactor.switchToGaunletGuessView();

        // Assertions
        assertEquals("switchToGaunletGuessView", presenter.getLastMethodCalled());
    }

    @Test
    void invalidBetTest() {
        // Input data with invalid bet amount
        GaunletBetInputData inputData = new GaunletBetInputData("valk", 5); // Min bet is 10

        // Dependencies
        StubGaunletBetDataAccess dataAccess = new StubGaunletBetDataAccess();
        MockUserFactory userFactory = new MockUserFactory();
        CapturingPresenter presenter = new CapturingPresenter();

        // Interactor instance
        GaunletBetInteractor interactor = new GaunletBetInteractor(dataAccess, presenter, userFactory);

        // Execute the use case
        interactor.execute(inputData, 5);

        // Assertions
        assertEquals("prepareFailView", presenter.getLastMethodCalled());
        assertEquals("Invalid bet amount. Please bet a value between 10 tokens and your current balance.",
                presenter.getErrorMessage());
    }
    @Test
    void switchToGameMenuViewTest() {
        // Set up CapturingPresenter
        CapturingPresenter presenter = new CapturingPresenter();

        // Interactor instance with minimal dependencies
        GaunletBetInteractor interactor = new GaunletBetInteractor(null, presenter, null);

        // Call the method
        interactor.switchToGameMenuView();

        // Assertions
        assertEquals("switchToGameMenuView", presenter.getLastMethodCalled());
    }
}

// ConcreteUser class for testing
class ConcreteUser implements User {
    private final String name;
    private final String password;
    private final JSONObject info;

    public ConcreteUser(String name, String password, JSONObject info) {
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
