package use_case.merchant;

/**
 * Input boundary for activating the Merchant use case.
 */
public interface MerchantInputBoundary {

    /**
     * Executes the Merchant Use Case.
     * @param merchantInputData input data
     */
    void execute(MerchantInputData merchantInputData);

    void buy(MerchantInputData merchantInputData);
}
