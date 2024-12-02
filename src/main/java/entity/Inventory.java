package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The representation of an inventory that holds items.
 */
public class Inventory {

    // items is Map<String, List<Item>> instead of Map<String, Item> in order to allow duplicates.
    private final Map<String, List<Item>> items;
    private Item equippedArmor;
    private Item equippedWeapon;
    private Item equippedBuff;

    public Inventory() {
        items = new HashMap<>();
    }

    /**
     * Returns a map containing all items in the inventory.
     *
     * @return a map containing all items in the inventory
     */
    public Map<String, List<Item>> getItems() {
        return items;
    }

    /**
     * Adds an item to the inventory, allowing duplicate items with the same name.
     *
     * @param name the name of the item to add
     * @param item the item to add
     */
    public void addItem(String name, Item item) {
        List<Item> itemList = items.get(name);

        if (itemList == null) {
            itemList = new ArrayList<>();
            items.put(name, itemList);
        }

        itemList.add(item);
    }

    /**
     * Removes an item from the inventory.
     *
     * @param name the name of the item to be removed
     * @return the item removed, or null if not found.
     */
    public Item removeItem(String name) {
        final List<Item> itemList = items.get(name);
        Item result = null;

        if (itemList != null && !itemList.isEmpty()) {
            result = itemList.remove(0);
        }

        return result;
    }

    /**
     * Checks if the inventory contains at least one item with the given name.
     *
     * @param name the name of the item to check for
     * @return true if the item exists in the inventory, false otherwise
     */
    public boolean hasItem(String name) {
        List<Item> itemList = items.get(name);
        return itemList != null && !itemList.isEmpty();
    }

    /**
     * Returns an item from the inventory.
     *
     * @param name the name of the item
     * @return the item, or null if not found
     */
    public Item getItem(String name) {
        final List<Item> itemList = items.get(name);
        Item result = null;

        if (itemList != null && !itemList.isEmpty()) {
            result = itemList.get(0);
        }

        return result;
    }

    /**
     * Equips an item by type, replacing any currently equipped item of the same type.
     *
     * @param item the item to equip
     * @return true if the item was successfully equipped, false otherwise
     */
    public boolean equipItem(Item item) {
        if (item == null) {
            return false;
        }

        String itemName = item.getName();
        if (!removeItem(itemName).equals(item)) {
            return false; // Ensure the item is removed from inventory
        }

        if (item instanceof Armor) {
            equippedArmor = replaceEquippedItem(equippedArmor, item);
        } else if (item instanceof Weapon) {
            equippedWeapon = replaceEquippedItem(equippedWeapon, item);
        } else if (item instanceof Buff) {
            equippedBuff = replaceEquippedItem(equippedBuff, item);
        } else {
            return false; // Unsupported item type
        }

        return true;
    }

    private Item replaceEquippedItem(Item currentEquipped, Item newItem) {
        if (currentEquipped != null) {
            addItem(currentEquipped.getName(), currentEquipped); // Return current item to inventory
        }
        return newItem;
    }

    /**
     * Getters and Setters for equipped items.
     */
    public Item getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Item weapon) {
        this.equippedWeapon = weapon;
    }

    public Item getEquippedArmor() {
        return equippedArmor;
    }

    public void setEquippedArmor(Item armor) {
        this.equippedArmor = armor;
    }

    public Item getEquippedBuff() {
        return equippedBuff;
    }

    public void setEquippedBuff(Item buff) {
        this.equippedBuff = buff;
    }

    /**
     * Returns a list of all items in the inventory, including duplicates.
     */
    public List<Item> getAllItems() {
        List<Item> allItems = new ArrayList<>();
        for (List<Item> itemList : items.values()) {
            allItems.addAll(itemList);
        }
        return allItems;
    }
}




