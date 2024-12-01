package interface_adapter.room_default;

/**
 * The state for the Room Default View.
 * Represents the current state of the room, including its description, content, errors, and room number.
 */
public class RoomDefaultState {

    private String roomDescription = "No description available.";
    private String roomContent = "No content available.";
    private String errorMessage = "";
    private int roomNumber = -1;
    private String roomType = "";  // e.g., "MonsterRoom", "ItemRoom", "TrapRoom"

    /**
     * Gets the description of the current room.
     *
     * @return the room description
     */
    public String getRoomDescription() {
        return roomDescription;
    }

    /**
     * Sets the description of the current room.
     *
     * @param roomDescription the new room description
     */
    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    /**
     * Gets the error message associated with the room state.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets an error message in the room state.
     *
     * @param errorMessage the error message to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Gets the current room number.
     *
     * @return the room number
     */
    public int getRoomNumber() {
        if (roomType == "MonsterRoom") {
            return 0;
        } else if (roomType == "ItemRoom") {
            return 1;
        } else if (roomType == "TrapRoom") {
            return 2;
        } else {
            return 3;
        }
    }

    /**
     * Gets the type of the room (e.g., "Monster", "Treasure", "Trap").
     *
     * @return the room type
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * Sets the type of the room.
     *
     * @param roomType the new room type
     */
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

//    /**
//     * Resets the state for transitioning to a new room, clearing content and error messages.
//     */
//    public void resetState() {
//        this.roomDescription = "No description available.";
//        this.roomContent = "No content available.";
//        this.errorMessage = "";
//        this.roomType = "";
//    }
}
