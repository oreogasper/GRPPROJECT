package use_case.add_friend;

/**
 * The Add Friend Use Case.
 */
public interface AddFriendInputBoundary {

    /**
     * Execute the Add Friend Use Case.
     * @param addFriendInputData the input data for this use case
     */
    void execute(AddFriendInputData addFriendInputData);

}
