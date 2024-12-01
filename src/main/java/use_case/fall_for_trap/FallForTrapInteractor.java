package use_case.fall_for_trap;

/**
 * The Fall For Trap Interactor.
 */
public class FallForTrapInteractor implements FallForTrapInputBoundary {
    private FallForTrapDataAccessInterface fallForTrapDataAccessObject;
    private FallForTrapOutputBoundary fallForTrapPresenter;

    public FallForTrapInteractor(FallForTrapDataAccessInterface fallForTrapDataAccessInterface,
                                 FallForTrapOutputBoundary fallForTrapOutputBoundary) {
        this.fallForTrapDataAccessObject = fallForTrapDataAccessInterface;
        this.fallForTrapPresenter = fallForTrapOutputBoundary;
    }

    @Override
    public void exitInteraction() {
        fallForTrapPresenter.exitInteraction();
    }
}
