package use_case.add_friend;

/**
 * Output Data for the Add Friend Use Case.
 */
public class AddFriendOutputData {

    private final String username;

    private final boolean useCaseFailed;

    public AddFriendOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
