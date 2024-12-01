package use_case.remove_friend;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RemoveFriendInteractorTest {

    @Test
    void successRemoveAllFriendsTest() {
        // Initialize user info with friends
        JSONObject info = new JSONObject();
        JSONArray initialFriends = new JSONArray();
        initialFriends.put(new JSONObject().put("username", "John").put("password", "friendPassword1"));
        initialFriends.put(new JSONObject().put("username", "Doe").put("password", "friendPassword2"));
        info.put("friends", initialFriends);
        info.put("wins", 0);
        info.put("losses", 0);
        info.put("games", 0);
        info.put("balance", 0);
        info.put("lastSpin", 0);

        RemoveFriendInputData inputData = new RemoveFriendInputData("Paul", info);
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "password", info);
        userRepository.save(user);

        RemoveFriendOutputBoundary successPresenter = new RemoveFriendOutputBoundary() {
            @Override
            public void prepareSuccessView(RemoveFriendOutputData outputData) {
                // Verify the friends list is empty
                JSONArray friends = outputData.getYOUser().getInfo().getJSONArray("friends");
                assertEquals(0, friends.length(), "Friends list should be empty after removal.");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        RemoveFriendInputBoundary interactor = new RemoveFriendInteractor(userRepository, successPresenter, factory);

        // Execute the use case
        interactor.execute(inputData);

        // Verify the repository state
        JSONObject updatedInfo = userRepository.get("Paul").getInfo();
        JSONArray updatedFriends = updatedInfo.getJSONArray("friends");
        assertEquals(0, updatedFriends.length(), "Friends list should be empty in the repository.");
    }

    @Test
    void noFriendsToRemoveTest() {
        // Initialize user info with no friends
        JSONObject info = new JSONObject();
        info.put("friends", new JSONArray());
        info.put("wins", 0);
        info.put("losses", 0);
        info.put("games", 0);
        info.put("balance", 0);
        info.put("lastSpin", 0);

        RemoveFriendInputData inputData = new RemoveFriendInputData("Paul", info);
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "password", info);
        userRepository.save(user);

        RemoveFriendOutputBoundary successPresenter = new RemoveFriendOutputBoundary() {
            @Override
            public void prepareSuccessView(RemoveFriendOutputData outputData) {
                JSONArray friends = outputData.getYOUser().getInfo().getJSONArray("friends");
                assertEquals(0, friends.length(), "Friends list should remain empty.");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        RemoveFriendInputBoundary interactor = new RemoveFriendInteractor(userRepository, successPresenter, factory);

        // Execute the use case
        interactor.execute(inputData);

        // Verify the repository state
        JSONObject updatedInfo = userRepository.get("Paul").getInfo();
        JSONArray updatedFriends = updatedInfo.getJSONArray("friends");
        assertEquals(0, updatedFriends.length(), "Friends list should remain empty in the repository.");
    }
}