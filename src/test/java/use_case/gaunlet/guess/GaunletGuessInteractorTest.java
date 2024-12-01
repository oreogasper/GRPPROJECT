package use_case.gaunlet.guess;

import entity.GaunletGame;
import entity.GaunletGameFactory;
import entity.User;
import entity.UserFactory;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GaunletGuessInteractorTest {

    // A simpler presenter that tracks if correct methods are called
    static class CapturingPresenter implements GaunletGuessOutputBoundary {
        private String lastMethodCalled = null;
        private GaunletGuessOutputData lastOutputData = null;

        @Override
        public void prepareSuccessView(GaunletGuessOutputData response) {
            lastMethodCalled = "prepareSuccessView";
            lastOutputData = response;
        }

        @Override
        public void prepareFailView(String error) {
            lastMethodCalled = "prepareFailView";
        }

        @Override
        public void switchToLoginView() {
            lastMethodCalled = "switchToLoginView";
        }

        public String getLastMethodCalled() {
            return lastMethodCalled;
        }

        public GaunletGuessOutputData getLastOutputData() {
            return lastOutputData;
        }
    }

    // A simpler implementation of GaunletGameFactory
    static class SimpGaunletGameFactory extends GaunletGameFactory {
        private final String coinResult;
        private final int diceResult;
        private final String rpsResult;

        public SimpGaunletGameFactory(String coinResult, int diceResult, String rpsResult) {
            this.coinResult = coinResult;
            this.diceResult = diceResult;
            this.rpsResult = rpsResult;
        }

        @Override
        public GaunletGame create(String coinGuess, int diceGuess, String rpsGuess) {
            return new GaunletGame() {
                @Override
                public String flipCoin() {
                    return coinResult;
                }

                @Override
                public int rollDice() {
                    return diceResult;
                }

                @Override
                public String getRpsOutcome() {
                    return rpsResult;
                }
            };
        }
    }

    // Simple implementation of GaunletGuessUserDataAccessInterface
    static class SimpUserDataAccess implements GaunletGuessUserDataAccessInterface {
        private final User user;
        private final int bet;

        public SimpUserDataAccess(User user, int bet) {
            this.user = user;
            this.bet = bet;
        }

        @Override
        public User get(String username) {
            return user;
        }

        @Override
        public int getBet() {
            return bet;
        }

        @Override
        public void saveNew(User user, JSONObject json) {
            // No-op
        }
    }

    // simple implementation of UserFactory
    static class SimpUserFactory implements UserFactory {
        @Override
        public User create(String name, String password, JSONObject info) {
            return new TestUser(name, password, info);
        }
    }

    @Test
    void testExecute_WinScenario() {
        CapturingPresenter presenter = new CapturingPresenter();
        User user = new TestUser("testUser", "password", new JSONObject().put("balance", 100));
        GaunletGameFactory gameFactory = new SimpGaunletGameFactory("Heads", 3, "Rock");
        GaunletGuessUserDataAccessInterface dataAccess = new SimpUserDataAccess(user, 10);
        UserFactory userFactory = new SimpUserFactory();
        GaunletGuessInteractor interactor = new GaunletGuessInteractor(presenter, gameFactory, dataAccess, userFactory);

        // User input ex
        GaunletGuessInputData inputData = new GaunletGuessInputData("testUser",
                "Heads", "3", "Rock");
        int balanceBefore = user.getBalance();
        interactor.execute(inputData);

        // Mock win scenario and check if correctly done
        assertEquals("prepareSuccessView", presenter.getLastMethodCalled());
        GaunletGuessOutputData outputData = presenter.getLastOutputData();
        assertNotNull(outputData);
        assertEquals("Heads", outputData.getCoinFlip());
        assertEquals("3", outputData.getDice());
        assertEquals("Rock", outputData.getRps());
        int balanceAfter = user.getBalance();
        assertTrue(balanceAfter > balanceBefore, "User should have won and received a bonus.");
    }

    @Test
    void testExecute_LossScenario() {
        CapturingPresenter presenter = new CapturingPresenter();
        User user = new TestUser("testUser", "password", new JSONObject().put("balance", 100));
        GaunletGameFactory gameFactory = new SimpGaunletGameFactory("Tails", 5, "Scissors");
        GaunletGuessUserDataAccessInterface dataAccess = new SimpUserDataAccess(user, 10);
        UserFactory userFactory = new SimpUserFactory();
        GaunletGuessInteractor interactor = new GaunletGuessInteractor(presenter, gameFactory, dataAccess, userFactory);

        // Mock wrong user input
        GaunletGuessInputData inputData = new GaunletGuessInputData("testUser",
                "Heads", "4", "Rock");
        int balanceBefore = user.getBalance();
        assertEquals(100, balanceBefore);
        interactor.execute(inputData);
        user.updateBalance(-10);

        // Test if lose scenario is correctly implemented
        assertEquals("prepareSuccessView", presenter.getLastMethodCalled());
        GaunletGuessOutputData outputData = presenter.getLastOutputData();
        assertNotNull(outputData);
        assertEquals("Heads", outputData.getCoinFlip());
        assertEquals("4", outputData.getDice());
        assertEquals("Rock", outputData.getRps());
        assertFalse(outputData.isWon());
        assertFalse(outputData.isUseCaseFailed());
    }

    @Test
    void testSwitchToLoginView() {
        CapturingPresenter presenter = new CapturingPresenter();
        GaunletGuessInteractor interactor = new GaunletGuessInteractor(presenter, null, null, null);

        interactor.switchToLoginView();

        assertEquals("switchToLoginView", presenter.getLastMethodCalled());
    }
}
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

