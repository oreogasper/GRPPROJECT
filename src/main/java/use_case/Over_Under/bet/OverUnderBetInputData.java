package use_case.Over_Under.bet;

/**
 * Input data for overUnder bet use case.
 */
public class OverUnderBetInputData {
    private final String userName;
    private final int betAmt;

    public OverUnderBetInputData(String userName, int betAmt) {
        this.userName = userName;
        this.betAmt = betAmt;
    }

    /**
     * Returns user's bet amount in overUnder bet use case.
     */
    public int getBetAmt() {
        return betAmt;
    }

    /**
     * Returns username in overUnder bet use case.
     */
    public String getUserName() {
        return userName;
    }
}
