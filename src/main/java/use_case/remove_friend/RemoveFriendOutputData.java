package use_case.remove_friend;

import entity.User;
/**
 * Output Data for the Remove Friend Use Case.
 */
public class RemoveFriendOutputData {
    private User user;
    private final boolean useCaseFailed;

    public RemoveFriendOutputData(User user, boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
        this.user = user;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
    public User getYOUser() {
        return user;
    }
}

