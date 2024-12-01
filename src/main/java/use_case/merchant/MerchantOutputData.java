package use_case.merchant;

import java.util.Objects;

/**
 * Output Data for Merchant Use Case.
 */
public class MerchantOutputData {
    private final String[] merchantItems;

    public MerchantOutputData(String[] merchantItems) {
        this.merchantItems = merchantItems;
    }

    public String[] getMerchantItems() {
        return merchantItems;
    }

    /**
     * Fetch a specific item by name from the merchant's items.
     *
     * @param itemName The name of the item to retrieve.
     * @return The item name if found; otherwise, null.
     */
    public String getSpecificItem(String itemName) {
        for (String item : merchantItems) {
            if (item.equals(itemName)) {
                return item;
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
        String item = getSpecificItem(itemName);
        // Value if false
        return Objects.requireNonNullElse(item, "Item not found"); // Value if true
    }

}
