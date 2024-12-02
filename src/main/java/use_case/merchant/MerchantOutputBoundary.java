package use_case.merchant;


import entity.Merchant;

/**
 * The Output Boundary for the Merchant Use Case.
 */
public interface MerchantOutputBoundary {
    /**
     * prepares Success View for the Merchant Use Case.
     * @param merchantOutputData output data
     */
    void prepareSuccessView(MerchantOutputData merchantOutputData);

    /**
     * prepares Buy View for the Merchant Use Case.
     */
    void prepareBuyView(MerchantOutputData merchantOutputData, String itemName);
}
