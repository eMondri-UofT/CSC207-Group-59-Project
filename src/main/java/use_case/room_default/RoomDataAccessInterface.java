package use_case.room_default;

import entity.AbstractRoom;
import entity.Floor;

/**
 * Data Access Interface for Room Use Case.
 */
public interface RoomDataAccessInterface {

    /**
     * Retrieves the current floor in the system.
     * @return the current floor.
     */
    Floor getFloor();

    /**
     * Creates a new floor
     * @return the new floor.
     */
    Floor makeNewFloor();

    /**
     * Retrieves the index of the current room within the floor.
     * @return the index of the current room.
     */
    int getCurrentRoomIndex();

    /**
     * Sets the index of the current room within the floor.
     * @param currentRoomIndex the new index of the current room.
     */
    void setCurrentRoomIndex(int currentRoomIndex);

    /**
     * Retrieves the current room based on the current room index.
     * @return the current room.
     */
    AbstractRoom getCurrentRoom();
}
