package use_case.room_default;

/**
 * Represents the output data for a trap room.
 * This includes information about the trap present in the room, such as its name and the damage it can deal.
 */
public class TrapRoomOutputData extends RoomOutputData {
    private final String trapName;
    private final int damage;
    private final int health;

    TrapRoomOutputData(String roomDescription, String trapName, int damage, int health) {
        super(roomDescription, "TrapRoom");
        this.trapName = trapName;
        this.damage = damage;
        this.health = health;
    }

    public String getTrapName() {
        return trapName;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }
}
