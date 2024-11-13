package use_case.statistics;

/**
 * Output Data for the Logout Use Case.
 */
public class StatisticsOutputData {

    private boolean useCaseFailed;

    public StatisticsOutputData(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
