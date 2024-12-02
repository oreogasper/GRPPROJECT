package use_case.Over_Under.play;

import java.awt.Image;

public class OverUnderPlayOutputData {

    private final boolean guessResult;
    private final String resultMessage;
    private final Image currentCardImage;
    private final Image nextCardImage;
    private final boolean useCaseFailed;

    // Constructor
    public OverUnderPlayOutputData(boolean guessResult, String resultMessage,
                                   Image currentCardImage, Image nextCardImage,
                                   boolean useCaseFailed) {
        this.guessResult = guessResult;
        this.resultMessage = resultMessage;
        this.currentCardImage = currentCardImage;
        this.nextCardImage = nextCardImage;
        this.useCaseFailed = useCaseFailed;
    }

    // Getters
    public boolean isGuessResult() {
        return guessResult;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public Image getCurrentCardImage() {
        return currentCardImage;
    }

    public Image getNextCardImage() {
        return nextCardImage;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
