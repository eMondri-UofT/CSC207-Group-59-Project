package entity;

/**
 * MonsterRoom entity.
 */
public class MonsterRoom extends AbstractRoom {

    private Monster monster;

    /**
     * Creates a MonsterRoom.
     * @param roomNumber the room number
     * @param monster the monster in the MonsterRoom
     */
    public MonsterRoom(int roomNumber, Monster monster) {
        super(roomNumber);
        this.monster = monster;
    }    // Possible need to add room number to manage a room

    @Override
    public int getRoomType() {
        return 0;
    }

    /**
     * Returns the monster in the MonsterRoom.
     * @return the monster in MonsterRoom
     */
    public Monster getMonster() {
        return monster;
    }

    /**
     * Allows us to set the monster in the MonsterRoom.
     * @param monster the monster we want to set inside the MonsterRoom
     */
    public void setMonster(Monster monster) {
        this.monster = monster;
    }
}
