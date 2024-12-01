package use_case.Over_Under.play;

public class OverUnderPlayInputData {
    private final String username;
    private final boolean isHigher;

    public OverUnderPlayInputData(String username, boolean isHigher) {
        this.username = username;
        this.isHigher = isHigher;
    }

    public boolean getIsHigher() {
        return this.isHigher;
    }

    public String getUserName() {
        return this.username;
    }
}
