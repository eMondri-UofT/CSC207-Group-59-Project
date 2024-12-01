package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Floor {
    private final ArrayList<AbstractRoom> abstractRoomList;
    private final Random random;

    /**
     * Constructor for the Floor class, each of the parameters in the room constructors
     * are random and will be instantiated when we start pulling from the API
     * Randomly populate the Rooms on the Floor with the various kinds of Room
     *
     */

    public Floor() {
        this.abstractRoomList = new ArrayList<>();
        this.random = new Random();

        for (int i = 0; i < RoomSize.ROOM_SIZE; i++) {
            int roomType = random.nextInt(4); // Randomly choose room type: 0 to 3

            if (roomType == 0) {
                abstractRoomList.add(new MonsterRoom(i, new Monster()));
            } else if (roomType == 1) {
                Item item = Item.generateRandomItem();
                if (item == null) {
                    throw new IllegalStateException("Failed to generate an item for ItemRoom");
                }
                abstractRoomList.add(new ItemRoom(i, item));
            } else if (roomType == 2) {
                abstractRoomList.add(new TrapRoom(i));
            } else if (roomType == 3) {
                abstractRoomList.add(new NpcRoom(i));
            } else {
                throw new IllegalStateException("Unexpected room type: " + roomType);
            }
        }
    }

    /** Returns the RoomList of the Floor
     *
     * @return the RoomList of this Floor
     */
    public List<AbstractRoom> getRoomList() {
        return abstractRoomList;
    }

}

