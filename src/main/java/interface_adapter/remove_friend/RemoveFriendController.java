package interface_adapter.remove_friend;

import org.json.JSONObject;
import use_case.remove_friend.RemoveFriendInputBoundary;
import use_case.remove_friend.RemoveFriendInputData;

/**
 * Controller for the Remove Friend Use Case.
 */
public class RemoveFriendController {
    private final RemoveFriendInputBoundary userRemoveFriendUseCaseInteractor;

    public RemoveFriendController(RemoveFriendInputBoundary userRemoveFriendUseCaseInteractor) {
        this.userRemoveFriendUseCaseInteractor = userRemoveFriendUseCaseInteractor;
    }

    /**
     * Executes the Remove Friend Use Case.
     * @param username the user whose friends to change
     * @param info the user's info
     */
    public void execute(String username, JSONObject info) {
        final RemoveFriendInputData removeFriendInputData = new RemoveFriendInputData(username, info);

        userRemoveFriendUseCaseInteractor.execute(removeFriendInputData);
    }
}
