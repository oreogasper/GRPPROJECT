package interface_adapter.shop.wheel;

/**
 * The State information representing the logged-in user.
 */
public class ShopWheelState {
    private int balance;
    private String username;
    private int spintime;

    public ShopWheelState(ShopWheelState copy) {
        username = copy.username;
        balance = copy.balance;
        spintime = copy.spintime;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public ShopWheelState() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setSpintime(int spintime) {
        this.spintime = spintime;
    }

    public int getSpintime() {
        return spintime;
    }

}
