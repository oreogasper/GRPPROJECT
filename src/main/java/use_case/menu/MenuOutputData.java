package use_case.menu;

/**
 * Output Data for the Menu Use Case.
 */
public class MenuOutputData {

    private boolean useCaseFailed;

    public MenuOutputData(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
