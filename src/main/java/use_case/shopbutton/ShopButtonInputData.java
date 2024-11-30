package use_case.shopbutton;

/**
 * The Input Data for the ShopButton Use Case.
 */

public class ShopButtonInputData {
    private final String username;
    private final Integer balance;

    public ShopButtonInputData(String username, Integer balance) {
        this.username = username;
        this.balance = balance;
    }

    String getUsername() {
        return username;
    }

    public Integer getBalance() {
        return balance;
    }

}
