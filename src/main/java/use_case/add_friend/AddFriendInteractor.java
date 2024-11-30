package use_case.add_friend;

import entity.User;
import entity.UserFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

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
        }
        else {

            final User userr = userDataAccessObject.get(addFriendInputData.getUsername());
            final User friend = userDataAccessObject.get(addFriendInputData.getFriend());
            final JSONObject json = userr.getInfo();

            final JSONArray list = json.getJSONArray("friends");

            // New element to add at the beginning
            final User newFriend = friend;

            // Create a new JSONArray
            final JSONArray newArray = new JSONArray();
            // System.out.println("Printing elements of the JSONArray:");

            // Put element at beginning of newArray
            newArray.put(newFriend);
            for (int i = 0; i < list.length(); i++) {
                // Add the rest of the previous friends
                newArray.put(list.get(i));
            }

            // System.out.println(newArray.get(0));
            json.put("friends", newArray);

            final User user = userFactory.create(userr.getName(), userr.getPassword(), json);
            userDataAccessObject.saveNew(user, json);
            userDataAccessObject.addFriend(user);

            final AddFriendOutputData addFriendOutputData = new AddFriendOutputData(friend,
                    false);

            userPresenter.prepareSuccessView(addFriendOutputData);
        }
    }
}
