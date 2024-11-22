package interface_adapter.shop.wheel;

import entity.User;

/**
 * The State information representing the logged-in user.
 */
public class ShopWheelState {
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    /**
     * Gives the token prize amount to the user.
     * @param prize is the amount to update the balance.
     */
    public void givePrize(int prize) {
        user.updateBalance(prize);
    }

}
