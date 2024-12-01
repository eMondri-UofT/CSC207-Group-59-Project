package use_case.room_default;

/**
 * Output Boundary for the Room Default Use Case.
 */
public interface RoomOutputBoundary {

    /**
     * Prepares the success view for the Room Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(RoomOutputData outputData);

    /**
     * Prepares the view for transitioning to the next room.
     * @param outputData the output data containing details of the next room.
     */
    void prepareNextRoomView(RoomOutputData outputData);

    /**
     * Prepares the failure view for the Room Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Prepares the view for returning to the main menu.
     */
    void prepareMainMenuView();
}
