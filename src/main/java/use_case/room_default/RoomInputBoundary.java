package use_case.room_default;

/**
 * Input Boundary for the Room Default Use Case.
 */
public interface RoomInputBoundary {

    /**
     * Executes the room interaction use case.
     * @param inputData the input data containing room details
     */
    void interactWithRoom(RoomInputData inputData);

    /**
     * Proceeds to the next room in the sequence.
     */
    void goToNextRoom();

    /**
     * Returns to the main menu from the current room.
     */
    void returnToMainMenu();
}
