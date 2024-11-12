package interface_adapter.cancel;

import use_case.cancel.CancelInputBoundary;

/**
 * The controller for the Cancel Use Case.
 */
public class CancelController {
    private CancelInputBoundary cancelUseCaseInteractor;

    public CancelController(CancelInputBoundary cancelUseCaseInteractor) {
        this.cancelUseCaseInteractor = cancelUseCaseInteractor;
    }

    /**
     * Executes the Cancel Use Case.
     */
    public void execute() {
        cancelUseCaseInteractor.execute();
    }
}
