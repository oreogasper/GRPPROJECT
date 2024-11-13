package use_case.signup;

/**
 * Output Data for the Signup Use Case.
 */
public class SignupOutputData {

    private final String username;
    private final String password;

    private final boolean useCaseFailed;

    public SignupOutputData(String username, String password, boolean useCaseFailed) {
        this.username = username;
        this.password = password;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public String getPassword() {
        return password;
    }
}
