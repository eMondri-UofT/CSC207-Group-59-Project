package use_case.fall_for_trap;

import org.junit.jupiter.api.Test;

import data_access.TrapDataAccessObject;

public class FallForTrapInteractorTest {

    @Test
    void successTest() {
        final FallForTrapDataAccessInterface trapRepository = new TrapDataAccessObject();

        final FallForTrapOutputBoundary successPresenter = new FallForTrapOutputBoundary() {
            @Override
            public void exitInteraction() {
                // This is expected
            }
        };

        final FallForTrapInputBoundary interactor = new FallForTrapInteractor(trapRepository, successPresenter);
        interactor.exitInteraction();
    }
}
