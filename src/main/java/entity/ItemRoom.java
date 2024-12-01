package entity;

public class ItemRoom extends AbstractRoom {
    private final Item item;

    public ItemRoom(int roomNumber, Item item) {
        super(roomNumber);
        this.item = item;
    }

    @Override
    public String getDescription() {
        return "Room " + getRoomNumber() + ": This room contains an item [" + item.getName() + "]";
    }

    @Override
    public int getRoomType() {
        return 1; // Item Room
    }

    public Item getItem() {
        return item;
    }
}

