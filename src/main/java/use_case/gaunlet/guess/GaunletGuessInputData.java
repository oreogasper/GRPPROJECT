package use_case.gaunlet.guess;

/**
 * The Input Data for the Gaunlet Guess Use Case.
 */
public class GaunletGuessInputData {
    private final String username;
    private final String coinFlip;
    private final String dice;
    private final String rps;

    public GaunletGuessInputData(String username, String coinFlip, String dice, String rps) {
        this.username = username;
        this.coinFlip = coinFlip;
        this.dice = dice;
        this.rps = rps;
    }

    String getUsername() {
        return username;
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
