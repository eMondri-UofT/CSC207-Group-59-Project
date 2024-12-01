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
    private String roomType = "";

    /**
     * Gets the description of the current room.
     * @return the room description
     */
    public String getRoomDescription() {
        return roomDescription;
    }

    /**
     * Sets the description of the current room.
     * @param roomDescription the new room description
     */
    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    /**
     * Gets the error message associated with the room state.
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets an error message in the room state.
     * @param errorMessage the error message to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Gets the current room number.
     * @return the room number
     */
    public int getRoomNumber() {
        final int mosnterRoom = 0;
        final int itemRoom = 1;
        final int trapRoom = 2;
        final int npcRoom = 3;
        final int result;
        if ("MonsterRoom".equals(roomType)) {
            result = mosnterRoom;
        }
        else if ("ItemRoom".equals(roomType)) {
            result = itemRoom;
        }
        else if ("TrapRoom".equals(roomType)) {
            result = trapRoom;
        }
        else {
            result = npcRoom;
        }
        return result;
    }

    /**
     * Gets the type of the room (e.g., "Monster", "Treasure", "Trap").
     * @return the room type
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * Sets the type of the room.
     * @param roomType the new room type
     */
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
}
