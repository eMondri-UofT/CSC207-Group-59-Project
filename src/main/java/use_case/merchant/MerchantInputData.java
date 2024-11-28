package use_case.merchant;

import entity.Item;

import java.util.List;
import java.util.Map;

public class MerchantInputData {
    private final Map<String, List<Item>> items;

    /**
     * Constructor for MerchantInputData.
     *
     * @param items A map of item categories to their respective lists of items.
     */
    public MerchantInputData(Map<String, List<Item>> items) {
        this.items = items;
    }

    /**
     * Get the map of item categories to their respective lists of items.
     *
     * @return The items map.
     */
    public Map<String, List<Item>> getItems() {
        return items;
    }

}