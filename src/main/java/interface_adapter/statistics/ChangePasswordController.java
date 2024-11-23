package interface_adapter.statistics;

import org.json.JSONObject;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInputData;

/**
 * Controller for the Change Password Use Case.
 */
public class ChangePasswordController {
    private final ChangePasswordInputBoundary userChangePasswordUseCaseInteractor;

    public ChangePasswordController(ChangePasswordInputBoundary userChangePasswordUseCaseInteractor) {
        this.userChangePasswordUseCaseInteractor = userChangePasswordUseCaseInteractor;
    }

    /**
     * Executes the Change Password Use Case.
     * @param password the new password
     * @param username the user whose password to change
     * @param info the user's info
     */
    public void execute(String password, String username, JSONObject info) {
        final ChangePasswordInputData changePasswordInputData = new ChangePasswordInputData(username, password, info);

        userChangePasswordUseCaseInteractor.execute(changePasswordInputData);
    }
}
