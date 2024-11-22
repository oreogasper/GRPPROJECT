package use_case.gaunlet.guess;

/**
 * Output Data for the Gaunlet guess Use Case.
 */
public class GaunletGuessOutputData {

    private final String coinFlip;
    private final String dice;
    private final String rps;
    private final boolean isWin;
    private final boolean useCaseFailed;

    public GaunletGuessOutputData(String coinFlip, String dice, String rps, boolean win, boolean useCaseFailed) {
        this.coinFlip = coinFlip;
        this.dice = dice;
        this.rps = rps;
        this.isWin = win;
        this.useCaseFailed = useCaseFailed;
    }

    public String getCoinFlip() {
        return coinFlip;
    }

    public String getDice() {
        return dice;
    }

    public String getRps() {
        return rps;
    }

    public boolean isWon() {
        return isWin;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
