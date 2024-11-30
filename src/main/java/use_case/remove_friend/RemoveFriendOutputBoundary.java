package use_case.remove_friend;

/**
 * The output boundary for the Add Friend Use Case.
 */
public interface RemoveFriendOutputBoundary {
    /**
     * Prepares the success view for the Remove Friend Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(RemoveFriendOutputData outputData);

    /**
     * Prepares the failure view for the Remove Friend Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
