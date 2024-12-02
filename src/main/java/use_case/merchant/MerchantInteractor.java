package use_case.merchant;

import data_access.NpcDataAccessObject;
import entity.Item;
import entity.Merchant;
import entity.Player;

import java.util.List;
import java.util.Map;

/**
 * The merchant interactor.
 */
public class MerchantInteractor implements MerchantInputBoundary {
    private NpcDataAccessObject npcDataAccessObject;
    private MerchantOutputBoundary merchantOutputBoundary;

    public MerchantInteractor(NpcDataAccessObject npcDataAccessObject,
                              MerchantOutputBoundary merchantOutputBoundary) {
        this.npcDataAccessObject = npcDataAccessObject;
        this.merchantOutputBoundary = merchantOutputBoundary;
    }

    @Override
    public void execute(MerchantInputData merchantInputData) {
        // Step 1: Get the current NPC and check if it's a merchant
        if (!npcDataAccessObject.isMerchant()) {
            throw new IllegalArgumentException("The current NPC is not a merchant.");
        }

        // Step 2: Retrieve the merchant and their items for sale
        Merchant merchant = (Merchant) npcDataAccessObject.getCurrentNpc();
        Map<String, List<Item>> itemsForSale = merchant.getItemsForSale();

        // Step 3: Prepare output data and pass to the presenter
        MerchantOutputData outputData = new MerchantOutputData(itemsForSale);
        merchantOutputBoundary.prepareSuccessView(outputData);
    }


    @Override
    public void buy(MerchantInputData merchantInputData) {
        // Step 1: Get the current NPC and validate it's a merchant
        if (!npcDataAccessObject.isMerchant()) {
            throw new IllegalArgumentException("The current NPC is not a merchant.");
        }

        // Step 2: Retrieve the merchant
        Merchant merchant = (Merchant) npcDataAccessObject.getCurrentNpc();

        // Step 3: Validate the item is for sale
        String itemName = merchantInputData.getItemName();
        if (!merchant.hasItem(itemName)) {
            throw new IllegalArgumentException("The item is not available for sale: " + itemName);
        }

        // Step 4: Perform the transaction
        Player player = Player.getInstance();
        try {
            merchant.sellItem(itemName, player);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Purchase failed: " + e.getMessage());
        }

        // Step 5: Update the view with the updated merchant items
        Map<String, List<Item>> updatedItemsForSale = merchant.getItemsForSale();
        MerchantOutputData outputData = new MerchantOutputData(updatedItemsForSale);
        merchantOutputBoundary.prepareBuyView(outputData, itemName);

    }
}
