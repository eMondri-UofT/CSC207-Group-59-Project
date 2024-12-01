package interface_adapter.room_default;

import use_case.room_default.RoomInputBoundary;

/**
 * Controller for the Room Default Use Case.
 */
public class RoomDefaultController {
    private final RoomInputBoundary roomUseCaseInteractor;

    /**
     * Constructor for RoomDefaultController.
     *
     * @param roomUseCaseInteractor the use case interactor for room interactions
     */
    public RoomDefaultController(RoomInputBoundary roomUseCaseInteractor) {
        this.roomUseCaseInteractor = roomUseCaseInteractor;
    }

    /**
     * Executes interaction with the room.
     */
    public void interactWithRoom() {
        roomUseCaseInteractor.interactWithRoom();
    }

    /**
     * Proceeds to the next room on the floor.
     */
    public void goToNextRoom() {
        roomUseCaseInteractor.goToNextRoom();
    }

    /**
     * Returns to the main menu (or inventory).
     */
    public void returnToMainMenu() {
        roomUseCaseInteractor.returnToMainMenu();
    }
}
