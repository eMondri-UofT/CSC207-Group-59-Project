package entity;

/**
 * Represents a room that contains an NPC.
 */
public class NpcRoom extends AbstractRoom {

    private static final int ROOM_TYPE_NPC = 3;

    private Npc npc;

    /**
     * Creates an NpcRoom with the specified room number.
     * @param roomNumber the unique number of the room.
     */
    public NpcRoom(int roomNumber) {
        super(roomNumber);
    }

    public Npc getNpc() {
        return npc;
    }

    public void setNpc(Npc npc) {
        this.npc = npc;
    }

    @Override
    public int getRoomType() {
        return ROOM_TYPE_NPC;
    }

    @Override
    public String getDescription() {
        return "Room " + getRoomNumber() + ": Someone is in this room.";
    }
}
