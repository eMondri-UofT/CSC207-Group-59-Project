package entity;

import java.util.List;
import java.util.Map;

/**
 * The representation of a Merchant NPC.
 */
public class Merchant extends Npc {
    private Map<String, List<Item>> itemsForSale; // Item name and price

    /**
     * Constructor for a Merchant.
     *
     * @param name        The name of the merchant.
     * @param description The description of the merchant.
     * @param dialogue    The dialogue of the merchant.
     * @param itemsForSale The items the merchant sells (name and price).
     */
    public Merchant(String name, String description, List<String> dialogue, Map<String,
            List<Item>> itemsForSale, boolean isMerchant) {
        super(name, description, dialogue, isMerchant);
        this.itemsForSale = itemsForSale;
    }

    /**
     * Get the items for sale.
     *
     * @return A map of item names and their prices.
     */
    public Map<String, List<Item>> getItemsForSale() {
        return itemsForSale;
    }

    public void setItemsForSale(Map<String, List<Item>> itemsForSale) {
        this.itemsForSale = itemsForSale;
    }

    @Override
    public boolean isMerchant() {
        return true; // Merchant-specific behavior
    }

    /**
     * Checks if the merchant has a specific item for sale.
     *
     * @param itemName The name of the item to check.
     * @return True if the item is available for sale, false otherwise.
     */
    public boolean hasItem(String itemName) {
        for (List<Item> itemList : itemsForSale.values()) {
            for (Item item : itemList) {
                if (item.getName().equals(itemName)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Sells an item to a player, deducting the price from the player's gold.
     *
     * @param itemName The name of the item to sell.
     * @param player   The player buying the item.
     * @return The item price if the sale is successful.
     * @throws IllegalArgumentException if the item is not available or the player has insufficient gold.
     */
    public int sellItem(String itemName, Player player) {
        // Search for the item in the categories
        Item itemToSell = null;
        for (List<Item> itemList : itemsForSale.values()) {
            for (Item item : itemList) {
                if (item.getName().equals(itemName)) {
                    itemToSell = item;
                    break;
                }
            }
            if (itemToSell != null) break; // Exit the outer loop if item is found
        }

        // If the item is not found, throw an exception
        if (itemToSell == null) {
            throw new IllegalArgumentException("Item not available: " + itemName);
        }

        int price = itemToSell.getPrice(); // Retrieve the price from the item

        // Check if the player has enough gold
        if (!player.subtractGold(price)) {
            throw new IllegalArgumentException("Player does not have enough gold to buy " + itemName);
        }

        // Add the item to the player's inventory
        Player.getInventory().addItem(itemName, itemToSell);

        // Remove the item from the merchant's inventory
        for (List<Item> itemList : itemsForSale.values()) {
            if (itemList.remove(itemToSell)) break; // Remove the item and exit loop
        }

        return price;
    }
}
