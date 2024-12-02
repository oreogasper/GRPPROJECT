package use_case.blackjack.game;

import entity.User;

import java.util.List;

/**
 * Input Data for the Blackjack Game Use Case.
 */
public class BlackjackGameInputData {
    private String useCaseName;
    private int bet;
    private User user;

    public BlackjackGameInputData(String useCaseName, int bet, User user) {
        this.useCaseName = useCaseName;
        this.bet = bet;
        this.user = user;

    }


    public String getUseCaseName() {
        return useCaseName;
    }

    public int getBet() {
        return bet;
    }

    public User getUser() {
        return user;
    }
}
