package use_case.remove_friend;

import entity.User;
import entity.UserFactory;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The Remove Friend Interactor.
 */
public class RemoveFriendInteractor implements RemoveFriendInputBoundary {
    private final RemoveFriendUserDataAccessInterface userDataAccessObject;
    private final RemoveFriendOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public RemoveFriendInteractor(RemoveFriendUserDataAccessInterface removeFriendUserDataAccessInterface,
                               RemoveFriendOutputBoundary removeFriendOutputBoundary,
                               UserFactory userFactory) {
        this.userDataAccessObject = removeFriendUserDataAccessInterface;
        this.userPresenter = removeFriendOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(RemoveFriendInputData removeFriendInputData) {

        final User userr = userDataAccessObject.get(removeFriendInputData.getUsername());

        final JSONObject json = userr.getInfo();
        final JSONArray empty = new JSONArray();
        json.put("friends", empty);

        final User user = userFactory.create(userr.getName(), userr.getPassword(), json);
        userDataAccessObject.saveNew(user, json);

        final RemoveFriendOutputData removeFriendOutputData = new RemoveFriendOutputData(user,
                false);

        userPresenter.prepareSuccessView(removeFriendOutputData);
    }
}
