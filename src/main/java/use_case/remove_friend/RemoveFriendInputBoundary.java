package use_case.remove_friend;
/**
 * The Remove Friend Use Case.
 */
public interface RemoveFriendInputBoundary {
    /**
     * Execute the Remove Friend Use Case.
     * @param removeFriendInputData the input data for this use case
     */
    void execute(RemoveFriendInputData removeFriendInputData);

}
