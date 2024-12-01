package use_case.merchant;

/**
 * Output Data for Merchant Use Case.
 */
public class MerchantOutputData {
    private String[] merchantItems;
    private String[] updatedItems;

    public MerchantOutputData(String[] merchantItems, String[] updatedItems) {
        this.merchantItems = merchantItems;
        this.updatedItems = updatedItems;
    }

    public String[] getMerchantItems() {
        return merchantItems;
    }

}
