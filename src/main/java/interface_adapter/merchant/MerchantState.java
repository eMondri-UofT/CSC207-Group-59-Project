package interface_adapter.merchant;

import data_access.NpcDataAccessObject;
import entity.Item;
import entity.Merchant;
import entity.Player;

import java.util.List;
import java.util.Map;

/**
 * The state for the Merchant View Model.
 */
public class MerchantState {
    private Map<String, List<Item>> items;
    private String error;
    private final NpcDataAccessObject npcDataAccessObject;

    public void setItems(Map<String, List<Item>> items) {this.items = items;}

    public Map<String, List<Item>> getItems() {return items;}

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    /**
     * Constructor for MerchantState.
     *
     * @param npcDataAccessObject The data access object for NPCs.
     */
    public MerchantState(NpcDataAccessObject npcDataAccessObject) {
        this.npcDataAccessObject = npcDataAccessObject;
    }

    public void buy(String itemName) {
        // Step 1: Get the current NPC and check if it is a merchant
        Merchant merchant = (Merchant) npcDataAccessObject.getCurrentNpc();
        if (merchant == null || !merchant.isMerchant()) {
            throw new IllegalArgumentException("The current NPC is not a merchant.");
        }

        // Step 2: Attempt to buy the item from the merchant
        Player player = Player.getInstance();
        try {
            merchant.sellItem(itemName, player);
            System.out.println("Successfully purchased: " + itemName);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Purchase failed: " + e.getMessage());
        }
    }


}
