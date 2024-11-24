package interface_adapter.shop;

import entity.User;

/**
 * The State information representing the logged-in user.
 */
public class ShopState {
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
