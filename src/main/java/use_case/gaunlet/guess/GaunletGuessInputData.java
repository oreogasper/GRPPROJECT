package use_case.gaunlet.guess;

/**
 * The Input Data for the Gaunlet Guess Use Case.
 */
public class GaunletGuessInputData {
    private final String coinFlip;
    private final String dice;
    private final String rps;

    public GaunletGuessInputData(String coinFlip, String dice, String rps) {

        this.coinFlip = coinFlip;
        this.dice = dice;
        this.rps = rps;
    }

    String getCoinFlip() {
        return coinFlip;
    }

    String getDice() {
        return dice;
    }

    String getRps() {
        return rps;
    }
}
