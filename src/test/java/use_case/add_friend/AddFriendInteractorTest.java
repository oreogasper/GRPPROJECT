package use_case.add_friend;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddFriendInteractorTest {
    @Test
    void successTest() {
        JSONObject info = new JSONObject();
        info.put("friends", new JSONArray()); // Initialize
        info.put("wins", 0);
        info.put("losses", 0);
        info.put("games", 0);
        info.put("balance", 0);
        info.put("lastSpin", 0);

        AddFriendInputData inputData = new AddFriendInputData("John", "Paul", info);
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "password", info);
        userRepository.save(user);
        User friend = factory.create("John", "friendPassword", new JSONObject().put("friends", new JSONArray()));
        userRepository.save(friend);

        AddFriendOutputBoundary successPresenter = new AddFriendOutputBoundary() {
            @Override
            public void prepareSuccessView(AddFriendOutputData outputData) {
                // Ensure the correct friend is passed
                assertEquals("John", outputData.getFriend().getName());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        AddFriendInputBoundary interactor = new AddFriendInteractor(userRepository, successPresenter, factory);

        // Execute the use case
        interactor.execute(inputData);

        // Verify the friend was added to the user's info
        JSONObject updatedInfo = userRepository.get("Paul").getInfo();
        JSONArray updatedFriends = updatedInfo.getJSONArray("friends");

        // Check if "John" exists in the friends list
        boolean friendExists = false;
        for (int i = 0; i < updatedFriends.length(); i++) {
            if (updatedFriends.getJSONObject(i).getString("username").equals("John")) {
                friendExists = true;
                break;
            }
        }
        assertTrue(friendExists, "Friend 'John' should exist in the friends list.");
    }

    @Test
    void failureFriendDoesNotExistTest() {
        JSONObject info = new JSONObject();
        info.put("friends", new JSONArray()); // Initialize
        info.put("wins", 0);
        info.put("losses", 0);
        info.put("games", 0);
        info.put("balance", 0);
        info.put("lastSpin", 0);

        AddFriendInputData inputData = new AddFriendInputData("John", "Paul", info);
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // Add the user to the repository but no friend
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "password", new JSONObject().put("friends", new JSONArray()));
        userRepository.save(user);

        // Create a failure presenter
        AddFriendOutputBoundary failurePresenter = new AddFriendOutputBoundary() {
            @Override
            public void prepareSuccessView(AddFriendOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("User does NOT exist!", error);
            }
        };

        AddFriendInputBoundary interactor = new AddFriendInteractor(userRepository, failurePresenter, factory);

        // Execute the use case
        interactor.execute(inputData);

        // Verify the friends list remains empty
        JSONArray friendsList = userRepository.get("Paul").getInfo().getJSONArray("friends");
        assertEquals(0, friendsList.length());
    }

    @Test
    void successAddMultipleFriendsTest() {
        JSONObject info = new JSONObject();
        info.put("friends", new JSONArray()); // Initialize
        info.put("wins", 0);
        info.put("losses", 0);
        info.put("games", 0);
        info.put("balance", 0);
        info.put("lastSpin", 0);


        AddFriendInputData inputData1 = new AddFriendInputData("John", "Paul", info);
        AddFriendInputData inputData2 = new AddFriendInputData("Doe", "Paul", info);
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "password", info);
        userRepository.save(user);
        User friend1 = factory.create("John", "friendPassword1", new JSONObject().put("friends", new JSONArray()));
        User friend2 = factory.create("Doe", "friendPassword2", new JSONObject().put("friends", new JSONArray()));
        userRepository.save(friend1);
        userRepository.save(friend2);

        // Create a success presenter
        AddFriendOutputBoundary successPresenter = new AddFriendOutputBoundary() {
            @Override
            public void prepareSuccessView(AddFriendOutputData outputData) {
                System.out.println("Added friend: " + outputData.getFriend().getName());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        AddFriendInputBoundary interactor = new AddFriendInteractor(userRepository, successPresenter, factory);

        interactor.execute(inputData1);
        interactor.execute(inputData2);

        // Verify both friends were added to the user's info
        JSONObject updatedInfo = userRepository.get("Paul").getInfo();
        JSONArray updatedFriends = updatedInfo.getJSONArray("friends");

        // Check if "John" and "Doe" exist in the friends list
        boolean johnExists = false;
        boolean doeExists = false;


        for (int i = 0; i < updatedFriends.length(); i++) {
            if (updatedFriends.getJSONObject(i).getString("username").equals("John")) {
                johnExists = true;
            }
            if (updatedFriends.getJSONObject(i).getString("username").equals("Doe")) {
                doeExists = true;
            }
        }

        assertTrue(johnExists, "Friend 'John' should exist in the friends list.");
        assertTrue(doeExists, "Friend 'Doe' should exist in the friends list.");
    }

    @Test
    void failureAddFriendAlreadyExistsTest() {
        JSONObject info = new JSONObject();
        info.put("friends", new JSONArray()); // Initialize
        info.put("wins", 0);
        info.put("losses", 0);
        info.put("games", 0);
        info.put("balance", 0);
        info.put("lastSpin", 0);

        AddFriendInputData inputData = new AddFriendInputData("John", "Paul", info);
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // Add the user and the friend to the repository
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "password", new JSONObject().put("friends", new JSONArray()));
        User friend = factory.create("John", "friendPassword", new JSONObject());
        userRepository.save(user);
        userRepository.save(friend);

        // Add John to the friends list
        user.getInfo().getJSONArray("friends").put(new JSONObject().put("username", "John"));

        // Create a failure presenter
        AddFriendOutputBoundary failurePresenter = new AddFriendOutputBoundary() {
            @Override
            public void prepareSuccessView(AddFriendOutputData outputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Friend already exists in the list.", error);
            }
        };

        AddFriendInputBoundary interactor = new AddFriendInteractor(userRepository, failurePresenter, factory);

        // Execute the use case
        interactor.execute(inputData);
        interactor.execute(inputData);

        // Verify no duplicate friend was added
        JSONArray friendsList = userRepository.get("Paul").getInfo().getJSONArray("friends");
        assertEquals(1, friendsList.length());
        assertEquals("John", ((JSONObject) friendsList.get(0)).getString("username"));
    }
}
