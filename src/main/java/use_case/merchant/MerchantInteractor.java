package use_case.merchant;

import data_access.NpcDataAccessObject;
import entity.Item;

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
        // not implemented yet
    }

    @Override
    public void buy(MerchantInputData merchantInputData) {

    }
}
