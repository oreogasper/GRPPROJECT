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

}
