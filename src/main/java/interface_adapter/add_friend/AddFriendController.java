package interface_adapter.add_friend;

import org.json.JSONObject;

import use_case.add_friend.AddFriendInputBoundary;
import use_case.add_friend.AddFriendInputData;

/**
 * Controller for the Add Friend Use Case.
 */
public class AddFriendController {
    private final AddFriendInputBoundary userAddFriendUseCaseInteractor;

    public AddFriendController(AddFriendInputBoundary userAddFriendUseCaseInteractor) {
        this.userAddFriendUseCaseInteractor = userAddFriendUseCaseInteractor;
    }

    /**
     * Executes the Add Friend Use Case.
     * @param friend the new friend
     * @param username the user whose password to change
     * @param info the user's info
     */
    public void execute(String friend, String username, JSONObject info) {
        final AddFriendInputData addFriendInputData = new AddFriendInputData(friend, username, info);

        userAddFriendUseCaseInteractor.execute(addFriendInputData);
    }
}
