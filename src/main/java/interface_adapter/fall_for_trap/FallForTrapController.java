package interface_adapter.fall_for_trap;

import use_case.fall_for_trap.FallForTrapInputBoundary;

/**
 * The controller for the Fall For Trap Use Case.
 */
public class FallForTrapController {

    private final FallForTrapInputBoundary fallForTrapUseCaseInteractor;

    public FallForTrapController(FallForTrapInputBoundary fallForTrapUseCaseInteractor) {
        this.fallForTrapUseCaseInteractor = fallForTrapUseCaseInteractor;
    }

    /**
     * Ends the interaction with the trap use case.
     */
    public void exitInteraction() {
        fallForTrapUseCaseInteractor.exitInteraction();
    }
}
