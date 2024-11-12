package use_case.cancel;


/**
 * The Logout Interactor.
 */
public class CancelInteractor implements CancelInputBoundary {
    private final CancelOutputBoundary logoutPresenter;

    public CancelInteractor(CancelOutputBoundary logoutOutputBoundary) {
        this.logoutPresenter = logoutOutputBoundary;
    }

    @Override
    public void execute() {
        // * instantiate the `CancelOutputData`, which DOESNT need to contain the username
        final CancelOutputData logoutData = new CancelOutputData(false);

        // * tell the presenter to prepare a success view.
        logoutPresenter.prepareSuccessView(logoutData);
    }
}
