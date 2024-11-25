package use_case.add_friend;

import entity.User;
import entity.UserFactory;

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
        final User user = userFactory.create(addFriendInputData.getUsername(),
                addFriendInputData.getPassword(),
                addFriendInputData.getInfo());
        userDataAccessObject.addFriend(user);

        final AddFriendOutputData addFriendOutputData = new AddFriendOutputData(user.getName(),
                                                                                  false);

        userPresenter.prepareSuccessView(addFriendOutputData);
    }
}
