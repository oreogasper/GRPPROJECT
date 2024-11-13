package use_case.welcome;

/**
 * Output Data for the Logout Use Case.
 */
public class WelcomeOutputData {

    private boolean useCaseFailed;

    public WelcomeOutputData(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
