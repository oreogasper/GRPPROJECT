package use_case.change_password;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChangePasswordInteractorTest {

    @Test
    void successTest() {
        ChangePasswordInputData inputData = new ChangePasswordInputData("newPassword", "Paul", new JSONObject());
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "oldPassword", new JSONObject());
        userRepository.save(user);

        ChangePasswordOutputBoundary successPresenter = new ChangePasswordOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangePasswordOutputData user) {
                assertEquals("Paul", user.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        ChangePasswordInputBoundary interactor = new ChangePasswordInteractor(userRepository, successPresenter, factory);
        interactor.execute(inputData);
    }

    @Test
    void failurePasswordWithSpacesTest() {
        ChangePasswordInputData inputData = new ChangePasswordInputData("new password", "Paul", new JSONObject());
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "oldPassword", new JSONObject());
        userRepository.save(user);

        ChangePasswordOutputBoundary failurePresenter = new ChangePasswordOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangePasswordOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Password can't contain spaces!", error);
            }
        };

        ChangePasswordInputBoundary interactor = new ChangePasswordInteractor(userRepository, failurePresenter, factory);

        interactor.execute(inputData);

        assertEquals("oldPassword", userRepository.get("Paul").getPassword());
    }

    @Test
    void failureEmptyPasswordTest() {
        ChangePasswordInputData inputData = new ChangePasswordInputData("", "Paul", new JSONObject());
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "oldPassword", new JSONObject());
        userRepository.save(user);

        ChangePasswordOutputBoundary failurePresenter = new ChangePasswordOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangePasswordOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Password can't be empty!", error);
            }
        };

        ChangePasswordInputBoundary interactor = new ChangePasswordInteractor(userRepository, failurePresenter, factory);

        interactor.execute(inputData);
        assertEquals("oldPassword", userRepository.get("Paul").getPassword());
    }

    @Test
    void successCreateNewUserWithPasswordTest() {
        ChangePasswordInputData inputData = new ChangePasswordInputData("newPassword", "NewUser", new JSONObject());
        ChangePasswordUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        assertNull(userRepository.get("NewUser"));

        ChangePasswordOutputBoundary successPresenter = new ChangePasswordOutputBoundary() {
            @Override
            public void prepareSuccessView(ChangePasswordOutputData user) {
                assertEquals("NewUser", user.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        UserFactory factory = new CommonUserFactory();
        ChangePasswordInputBoundary interactor = new ChangePasswordInteractor(userRepository, successPresenter, factory);

        interactor.execute(inputData);
        assertEquals("newPassword", userRepository.get("NewUser").getPassword());
    }
}
