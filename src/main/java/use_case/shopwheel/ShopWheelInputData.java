package use_case.shopwheel;

/**
 * The Input Data for the ShopWheel Use Case.
 */

public class ShopWheelInputData {
    private final String username;
    private final String password;
    private final Integer balance;
    private final long lastSpin;

    public ShopWheelInputData(String username, String password, Integer balance, long lastSpin) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.lastSpin = lastSpin;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    public Integer getBalance() {
        return balance;
    }

    public long getLastSpin() {return lastSpin;}
}
