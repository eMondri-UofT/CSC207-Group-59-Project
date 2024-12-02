package interface_adapter.merchant;

import interface_adapter.ViewManagerModel;
import interface_adapter.room_default.RoomDefaultState;
import interface_adapter.room_default.RoomDefaultViewModel;
import use_case.merchant.MerchantOutputBoundary;
import use_case.merchant.MerchantOutputData;

/**
 * Presenter for Merchant Use Case.
 */
public class MerchantPresenter implements MerchantOutputBoundary {
    private final MerchantViewModel merchantViewModel;
    private final RoomDefaultViewModel roomDefaultViewModel;
    private final ViewManagerModel viewManagerModel;

    public MerchantPresenter(MerchantViewModel merchantViewModel,
                             RoomDefaultViewModel roomDefaultViewModel,
                             ViewManagerModel viewManagerModel) {
        this.merchantViewModel = merchantViewModel;
        this.roomDefaultViewModel = roomDefaultViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareSuccessView(MerchantOutputData merchantOutputData) {
        // Update the merchant's inventory in the view model
        merchantViewModel.updateItems(merchantOutputData.getMerchantItems()); // Adjust for the actual data structure

        // Update the RoomDefault state to reflect the merchant interaction
        final RoomDefaultState roomDefaultState = roomDefaultViewModel.getState();
        this.roomDefaultViewModel.setState(roomDefaultState);
        this.roomDefaultViewModel.firePropertyChanged();

        // Set the current view to the room default view
        this.viewManagerModel.setState(roomDefaultViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    public void prepareBuyView(MerchantOutputData merchantOutputData, String itemName) {
        // Check if the purchase was successful
        String purchasedItem = merchantOutputData.getSpecificItemName(itemName);
        if (purchasedItem == null) {
            // If the item wasn't found, set an error
            merchantViewModel.setError("Item not found or purchase failed.");
        } else {
            // Update the merchant's inventory and reflect the purchase
            merchantViewModel.updateItems(merchantOutputData.getMerchantItems()); // Adjust for the actual data structure
            System.out.println("Successfully purchased: " + purchasedItem);
        }

        // Update the merchant view state to reflect the changes
        merchantViewModel.firePropertyChanged();

        // Switch back to the merchant view after updating
        this.viewManagerModel.setState(merchantViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
