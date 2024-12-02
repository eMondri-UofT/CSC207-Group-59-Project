package use_case.merchant;

import entity.Item;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Output Data for Merchant Use Case.
 */
public class MerchantOutputData {
    private final Map<String, List<Item>> merchantItems; // Category to items mapping

    public MerchantOutputData(Map<String, List<Item>> merchantItems) {
        this.merchantItems = merchantItems;
    }

    /**
     * Gets the merchant's items as a category-to-item mapping.
     *
     * @return The merchant's items.
     */
    public Map<String, List<Item>> getMerchantItems() {
        return merchantItems;
    }

    /**
     * Fetch a specific item by name from the merchant's items.
     *
     * @param itemName The name of the item to retrieve.
     * @return The item name if found; otherwise, null.
     */
    public Item getSpecificItem(String itemName) {
        for (List<Item> items : merchantItems.values()) {
            for (Item item : items) {
                if (item.equals(itemName)) {
                    return item;
                }
            }
        }
        return null; // Item not found
    }

    /**
     * Returns the name of the specific item from the merchant's items.
     *
     * @param itemName The name of the item to retrieve.
     * @return The name of the item if found; otherwise, a message indicating it wasn't found.
     */
    public String getSpecificItemName(String itemName) {
        Item item = getSpecificItem(itemName);
        return Objects.requireNonNullElse(item, "Item not found").toString();
    }
}
