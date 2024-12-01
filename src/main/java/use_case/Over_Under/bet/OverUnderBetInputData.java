package use_case.Over_Under.bet;

public class OverUnderInputData {
    private final boolean isHigher;

    public OverUnderInputData(boolean isHigher) {
        this.isHigher = isHigher;
    }

    public boolean isHigher() {
        return isHigher;
    }
}
