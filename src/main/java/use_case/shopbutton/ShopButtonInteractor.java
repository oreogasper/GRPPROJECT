package use_case.shopbutton;

import entity.User;
import entity.UserFactory;
import org.json.JSONObject;

/**
 * The presenter class for ShopButton.
 */
public class ShopButtonInteractor implements ShopButtonInputBoundary {
    private final ShopButtonOutputBoundary userPresenter;
    private final ShopButtonUserDataAccessInterface userDataAccessObject;
    private final UserFactory userFactory;

    public ShopButtonInteractor(ShopButtonOutputBoundary shopButtonOutputBoundary,
                                ShopButtonUserDataAccessInterface shopButtonUserDataAccessInterface,
                                UserFactory userFactory) {
        this.userPresenter = shopButtonOutputBoundary;
        this.userDataAccessObject = shopButtonUserDataAccessInterface;
        this.userFactory = userFactory;
    }

    @Override
    public void switchToShopView() {
        userPresenter.switchToShopView();
    }

    @Override
    public void buttonClick(int currentClicks) {
        userPresenter.addClick();
        final int clickRequirement = 10;
        if ((currentClicks + 1) % clickRequirement == 0) {
            userPresenter.addToken();
        }
        userPresenter.prepareSuccessView();
    }

    @Override
    public void saveData(ShopButtonInputData shopButtonInputData, Integer newBalance) {
        final User infoUser = userDataAccessObject.get(shopButtonInputData.getUsername());
        final JSONObject json = infoUser.getInfo();
        json.put("balance", newBalance);

        // Uncomment to initialize lastspin for an existing user when using shopbutton
        /* final long lastspin = 0;
        json.put("lastSpin", lastspin);*/

        final User updatedUser = userFactory.create(infoUser.getName(), infoUser.getPassword(), json);
        userDataAccessObject.saveNew(updatedUser, json);
    }

}
