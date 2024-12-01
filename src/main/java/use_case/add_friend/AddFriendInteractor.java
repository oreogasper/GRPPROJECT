package use_case.add_friend;

import entity.User;
import entity.UserFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * The Add Friend Interactor.
 */
public class AddFriendInteractor implements AddFriendInputBoundary {
    private final AddFriendUserDataAccessInterface userDataAccessObject;
    private final AddFriendOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public AddFriendInteractor(AddFriendUserDataAccessInterface addFriendDataAccessInterface,
                               AddFriendOutputBoundary addFriendOutputBoundary,
                                    UserFactory userFactory) {
        this.userDataAccessObject = addFriendDataAccessInterface;
        this.userPresenter = addFriendOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(AddFriendInputData addFriendInputData) {
        if (!userDataAccessObject.existsByName(addFriendInputData.getFriend())) {
            userPresenter.prepareFailView("User does NOT exist!");
        } else {
            final User userr = userDataAccessObject.get(addFriendInputData.getUsername());
            final User friend = userDataAccessObject.get(addFriendInputData.getFriend());
            final JSONObject json = userr.getInfo();

            // Ensure the "friends" list exists
            JSONArray list = json.optJSONArray("friends");
            if (list == null) {
                list = new JSONArray();
                json.put("friends", list);
            }

            // Create a JSONObject for the new friend
            JSONObject newFriend = new JSONObject();
            newFriend.put("username", friend.getName());
            newFriend.put("password", friend.getPassword());
            newFriend.put("info", friend.getInfo());

            // Create a new JSONArray and filter out invalid entries
            JSONArray newArray = new JSONArray();
            newArray.put(newFriend); // Add the new friend

            Set<String> addedUsernames = new HashSet<>();

            for (int i = 0; i < list.length(); i++) {
                Object existingFriend = list.get(i);

                // Only add valid JSONObject friends, excluding the current user
                if (existingFriend instanceof JSONObject) {
                    JSONObject existingFriendObj = (JSONObject) existingFriend;
                    String friendUsername = existingFriendObj.getString("username");
                    addedUsernames.add(friendUsername);

                    if (!existingFriendObj.getString("username").equals(userr.getName())) {
                        newArray.put(existingFriendObj);
                    }
                }
            }

            String newFriendUsername = newFriend.getString("username");
            if (addedUsernames.contains(newFriendUsername)) {
                userPresenter.prepareFailView("Friend already exists in the list.");
                return;
            }

            // Update the user's info
            json.put("friends", newArray);

            final User updatedUser = userFactory.create(userr.getName(), userr.getPassword(), json);
            userDataAccessObject.saveNew(updatedUser, json);

            final AddFriendOutputData addFriendOutputData = new AddFriendOutputData(friend, false);
            userPresenter.prepareSuccessView(addFriendOutputData);
        }
    }
}
