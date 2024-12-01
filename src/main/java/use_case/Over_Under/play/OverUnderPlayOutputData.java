package use_case.Over_Under.play;

public class OverUnderPlayOutputData {


    private boolean guessResult;
    private String error;

    public OverUnderPlayOutputData(boolean guessResult, String error) {
        this.guessResult = guessResult;
        this.error = error;
    }

    public boolean getGuessResult() {
        return this.guessResult;
    }

    public String getError(){
        return this.error;
    }
}
