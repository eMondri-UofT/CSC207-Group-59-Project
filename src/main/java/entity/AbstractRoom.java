package entity;

/**
 * Represents an abstract room in the game.
 * Specific room types extend this class to define their unique behaviors.
 */
public abstract class AbstractRoom {

    private final int roomNumber;

    public AbstractRoom(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getDescription() {
        return "description";
    }

    public int getRoomType() {
        return -1;
    }

    /**
     * Returns the room Number of the room.
     *
     * @return the number of room in the floor.
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * Returns the item in the room.
     * Default implementation returns null, as not all rooms contain items.
     *
     * @return the item in the room, or null if none
     */
    public Item getItem() {
        return null;
    }
}
