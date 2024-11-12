package use_case.cancel;


/**
 * Output Data for the Logout Use Case.
 */
public class CancelOutputData {

    private boolean useCaseFailed;

    public CancelOutputData(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
