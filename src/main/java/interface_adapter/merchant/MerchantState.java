package interface_adapter.merchant;

import entity.Item;
import entity.Player;

import java.util.List;
import java.util.Map;

/**
 * The state for the Merchant View Model.
 */
public class MerchantState {
    private Map<String, List<Item>> items;

    public void setItems(Map<String, List<Item>> items) {this.items = items;}

    public Map<String, List<Item>> getItems() {return items;}

    public void buy(String itemName) {
        Player player = Player.getInstance(); // Access the player instance

        // Search for the item across all categories
        for (List<Item> categoryItems : items.values()) {
            for (Item item : categoryItems) {
                if (item.getName().equals(itemName)) {
                    // Check if the player has enough gold
                    if (!player.subtractGold(item.getPrice())) {
                        throw new IllegalArgumentException("Not enough gold to buy " + itemName);
                    }

                    // Remove the item from the merchant's inventory
                    categoryItems.remove(item);

                    // Add the item to the player's inventory
                    Player.getInventory().addItem(itemName, item);

                    System.out.println("Successfully purchased: " + itemName);
                    return; // Exit the method after successful purchase
                }
            }
        }

        // If the item is not found in any category
        throw new IllegalArgumentException("Item not found: " + itemName);
    }

}
