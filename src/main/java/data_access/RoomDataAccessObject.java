package data_access;

import entity.AbstractRoom;
import entity.Floor;
import use_case.room_default.RoomDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing room data. This implementation does
 * NOT persist data between runs of the program.
 */
public class RoomDataAccessObject implements RoomDataAccessInterface {
    private Floor floor = new Floor();
    private int currentRoomIndex;
    private int floorIndex = 1;

    @Override
    public Floor getFloor() {
        return floor;
    }

    @Override
    public Floor makeNewFloor() {
        floor = new Floor();
        floorIndex += 1;
        return floor;
    }

    @Override
    public int getCurrentRoomIndex() {
        return currentRoomIndex;
    }

    @Override
    public void setCurrentRoomIndex(int currentRoomIndex) {
        this.currentRoomIndex = currentRoomIndex;
    }

    @Override
    public AbstractRoom getCurrentRoom() {
        return floor.getRoomList().get(currentRoomIndex);
    }
}
