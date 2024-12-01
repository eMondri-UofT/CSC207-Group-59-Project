package interface_adapter.merchant;

import entity.Item;

import java.util.List;
import java.util.Map;

/**
 * The state for the Merchant View Model.
 */
public class MerchantState {
    private Map<String, List<Item>> items;

    public void setItems(Map<String, List<Item>> items) {this.items = items;}

    public Map<String, List<Item>> getItems() {return items;}

    public void buy() {

    }

}
