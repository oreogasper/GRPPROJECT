package interface_adapter.shop.wheel;

import entity.User;

/**
 * The State information representing the logged-in user.
 */
public class ShopWheelState {
    private User user;
    private boolean wasSpun;
    private final int waitRequirement = 600;

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

    /**
     * Returns whether the wheel was just spun.
     * @return the boolean.
     */
    public boolean getWasSpun() {
        return wasSpun;
    }

    /**
     * Sets whether the wheel was recently spun.
     * @param wasSpun is the boolean to set.
     */
    public void setWasSpun(boolean wasSpun) {
        this.wasSpun = wasSpun;
    }

    public int getWaitRequirement() {
        return waitRequirement;
    }

}
