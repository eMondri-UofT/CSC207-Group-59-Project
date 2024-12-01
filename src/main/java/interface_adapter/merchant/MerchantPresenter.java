package interface_adapter.merchant;

import entity.Merchant;
import interface_adapter.ViewManagerModel;
import interface_adapter.room_default.RoomDefaultState;
import interface_adapter.room_default.RoomDefaultViewModel;
import use_case.merchant.MerchantOutputBoundary;
import use_case.merchant.MerchantOutputData;
import view.MerchantView;

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
        final RoomDefaultState roomDefaultState = roomDefaultViewModel.getState();
        this.roomDefaultViewModel.setState(roomDefaultState);
        this.roomDefaultViewModel.firePropertyChanged();

        this.viewManagerModel.setState(roomDefaultViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    public void prepareBuyView(MerchantOutputData merchantOutputData) {
        final MerchantState merchantState = merchantViewModel.getState();
        merchantState.buy();
    }
}
