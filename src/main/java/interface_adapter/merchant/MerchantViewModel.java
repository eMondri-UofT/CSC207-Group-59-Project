package interface_adapter.merchant;

import data_access.NpcDataAccessObject;
import entity.Item;
import interface_adapter.ViewModel;

import java.util.List;
import java.util.Map;


/**
 * The View Model for the Merchant View.
 */
public class MerchantViewModel extends ViewModel<MerchantState> {

    public MerchantViewModel(NpcDataAccessObject npcDataAccessObject) {
        super("merchant");
        setState(new MerchantState(npcDataAccessObject));
    }

    /**
     * Updates the available items for the merchant.
     *
     * @param items A map of item categories to their respective items.
     */
    public void updateItems(Map<String, List<Item>> items) {
        MerchantState state = getState();
        state.setItems(items);
        setState(state);
    }

    /**
     * Sets an error message in the state.
     *
     * @param error The error message to display.
     */
    public void setError(String error) {
        MerchantState state = getState();
        state.setError(error);
        setState(state);
    }
}
