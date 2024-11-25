package use_case.shop;

import org.json.JSONObject;

import entity.User;
import entity.UserFactory;

/**
 * The Shop main menu Interactor.
 */
public class ShopInteractor implements ShopInputBoundary {
    private final ShopOutputBoundary userPresenter;
    private final ShopUserDataAccessInterface userDataAccessObject;
    private final UserFactory userFactory;

    public ShopInteractor(ShopOutputBoundary shopOutputBoundary,
                          ShopUserDataAccessInterface userDataAccessObject,
                          UserFactory userFactory) {
        this.userPresenter = shopOutputBoundary;
        this.userDataAccessObject = userDataAccessObject;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(ShopInputData shopInputData, Integer changedAmount) {

        final User userr = userDataAccessObject.get(shopInputData.getUsername());
        final JSONObject json = userr.getInfo();
        json.put("balance", changedAmount);
        final User user = userFactory.create(userr.getName(), userr.getPassword(), json);
        userDataAccessObject.saveNew(user, json);

        final ShopOutputData shopOutputData = new ShopOutputData(user.getName(), user.getPassword(), json);
        userPresenter.prepareSuccessView(shopOutputData);
    }

    @Override
    public void switchToMenuView() {
        userPresenter.switchToMenuView();
    }

    @Override
    public void switchToShopWheelView() {
        userPresenter.switchToShopWheelView();
    }

    @Override
    public void switchToShopButtonView() {
        userPresenter.switchToShopButtonView();
    }

}
