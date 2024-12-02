package use_case.merchant;

import entity.Merchant;
import entity.Item;
import use_case.room_default.NpcRoomDataAccessInterface;

import java.util.List;
import java.util.Map;

/**
 * Extended DAO interface for managing Merchant data.
 */
public interface MerchantDataAccessInterface extends NpcRoomDataAccessInterface {

    /**
     * Retrieves the items the current Merchant has for sale.
     *
     * @return a map of item categories to lists of items.
     */
    Map<String, List<Item>> getItemsForSale();

    /**
     * Updates the items the current Merchant has for sale.
     *
     * @param updatedItems a map of item categories to lists of updated items.
     */
    void updateItemsForSale(Map<String, List<Item>> updatedItems);
}
