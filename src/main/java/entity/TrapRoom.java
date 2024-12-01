package entity;

/**
 * Represents a room containing a trap.
 */
public class TrapRoom extends AbstractRoom {
    private Trap trap;

    /**
     * Creates a TrapRoom with the specified room number.
     * @param roomNumber the number of the room
     */
    public TrapRoom(int roomNumber) {
        super(roomNumber);
    }

    public Trap getTrap() {
        return trap;
    }

    public void setTrap(Trap trap) {
        this.trap = trap;
    }

    @Override
    public int getRoomType() {
        return 2;
    }

    @Override
    public String getDescription() {
        return "Room " + getRoomNumber() + ": The room appears empty and harmless.";
    }
}
