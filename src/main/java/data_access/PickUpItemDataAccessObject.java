package data_access;

import entity.Item;
import entity.ItemRoom;
import entity.Player;
import use_case.pickup_item.PickUpItemDataAccessInterface;

/**
 * In-memory implementation of the DAO for Pick Up Item Use Case.
 */
public class PickUpItemDataAccessObject implements PickUpItemDataAccessInterface {
    @Override
    public Item getItem() {
        if (!(Player.getInstance().getCurrentRoom() instanceof ItemRoom)) {
            throw new IllegalStateException("Current room is not an ItemRoom.");
        }
        return Player.getInstance().getCurrentRoom().getItem();
    }

    @Override
    public void addToInventory(Item item) {
        Player.getInstance().getInventory().addItem(item.getName(), item);
    }
}
