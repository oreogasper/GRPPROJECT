package use_case.cancel;

/**
 * The Interactor for the Cancel use case.
 */
public class CancelInteractor implements CancelInputBoundary {
    private final CancelOutputBoundary cancelPresenter;

    public CancelInteractor(CancelOutputBoundary cancelOutputBoundary) {
        this.cancelPresenter = cancelOutputBoundary;
    }

    @Override
    public void execute() {
        // * instantiate the `CancelOutputData`, which DOESNT need to contain the username
        final CancelOutputData logoutData = new CancelOutputData(false);

        // * tell the presenter to prepare a success view.
        cancelPresenter.prepareSuccessView(logoutData);
    }
}
