package use_case.cancel;

/**
 * The output boundary for the Cancel Use Case.
 */
public interface CancelOutputBoundary {
    /**
     * Prepares the success view for the Cancel Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(CancelOutputData outputData);

    /**
     * Prepares the failure view for the Cancel Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
