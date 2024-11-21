package interface_adapter.shop.button;

import entity.User;

/**
 * The State information representing the logged-in user.
 */
public class ShopButtonState {
    private User user;
    private int clicksMade;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    /**
     * Adds a click to the currentClicks count.
     */
    public void addClick() {
        clicksMade++;
    }

    public int getClicksMade() {
        return clicksMade;
    }

}
