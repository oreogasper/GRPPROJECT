package interface_adapter.shop;

import entity.User;

/**
 * The State information representing the logged-in user.
 */
public class ShopState {
    private User user;
    private int balance;
    private String username;

    public ShopState(ShopState copy) {
        username = copy.username;
        balance = copy.balance;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public ShopState() {

    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
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

}
