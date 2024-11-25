package use_case.add_friend;

import entity.User;

import java.util.ArrayList;

/**
 * Output Data for the Add Friend Use Case.
 */
public class AddFriendOutputData {
    private User friend;

    private final boolean useCaseFailed;

    public AddFriendOutputData(User friend, boolean useCaseFailed) {
        this.friend = friend;
        this.useCaseFailed = useCaseFailed;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public User getFriend() {
        return friend;
    }
}
