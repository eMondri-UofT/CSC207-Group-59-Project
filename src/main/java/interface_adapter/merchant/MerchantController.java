package interface_adapter.merchant;

import entity.Item;
import use_case.merchant.MerchantInputBoundary;
import use_case.merchant.MerchantInputData;

import java.util.List;
import java.util.Map;

/**
 * The controller for the Merchant use case.
 */
public class MerchantController {

    private final MerchantInputBoundary merchantUseCaseInteractor;

    public MerchantController(MerchantInputBoundary merchantUseCaseInteractor) {
        this.merchantUseCaseInteractor = merchantUseCaseInteractor;
    }

    public void execute(Map<String, List<Item>> items) {
        final MerchantInputData merchantInputData = new MerchantInputData(items);
        merchantUseCaseInteractor.execute(merchantInputData);
    }

    public void buy(String itemName) {
        MerchantInputData inputData = new MerchantInputData(itemName);
        merchantUseCaseInteractor.buy(inputData);
    }
}
