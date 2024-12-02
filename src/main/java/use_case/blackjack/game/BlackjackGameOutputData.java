package use_case.blackjack.game;


import java.util.List;

/**
 * Output Data for the Blackjack Game Use Case.
 */
public class BlackjackGameOutputData {
    private String useCase;
    private int amountWon;

    public BlackjackGameOutputData(String useCase, int amountWon) {
        this.useCase = useCase;
        this.amountWon = amountWon;

    }


    public int getAmountWon() {
        return amountWon;
    }


    public String getUseCase() {
        return useCase;
    }
}
