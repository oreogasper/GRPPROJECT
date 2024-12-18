package use_case.signup;

import entity.User;
import entity.UserFactory;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The Signup Interactor.
 */
public class SignupInteractor implements SignupInputBoundary {
    private static final int STARTING_BALANCE = 25;
    private final SignupUserDataAccessInterface userDataAccessObject;
    private final SignupOutputBoundary userPresenter;
    private final UserFactory userFactory;

    public SignupInteractor(SignupUserDataAccessInterface signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists!");
        }
        else if (signupInputData.getPassword().contains(" ")) {
            userPresenter.prepareFailView("Password can't contain spaces!");
        }
        else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match!");
        }
        else if (signupInputData.getUsername().isEmpty()) {
            userPresenter.prepareFailView("Username can't be empty!");
        }
        else if (signupInputData.getPassword().isEmpty()) {
            userPresenter.prepareFailView("Password can't be empty!");
        }
        else {
            final JSONObject extra = new JSONObject();
            extra.put("games", 0);
            extra.put("wins", 0);
            extra.put("losses", 0);
            extra.put("balance", STARTING_BALANCE);

            final ArrayList<User> list = new ArrayList<>();
            extra.put("friends", list);

            final long lastspin = 0;
            extra.put("lastSpin", lastspin);

            final User user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword(), extra);
            userDataAccessObject.save(user);
            userDataAccessObject.saveNew(user, extra);

            final SignupOutputData signupOutputData = new SignupOutputData(user.getName(), user.getPassword(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }

    @Override
    public void switchToWelcomeView() {
        userPresenter.switchToWelcomeView();
    }

    @Override
    public void switchToMenuView() {
        userPresenter.switchToMenuView();
    }

}
