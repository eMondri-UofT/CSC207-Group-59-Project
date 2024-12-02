package interface_adapter.talk_to_npc;

import interface_adapter.ViewManagerModel;
import interface_adapter.merchant.MerchantState;
import interface_adapter.merchant.MerchantViewModel;
import interface_adapter.room_default.RoomDefaultState;
import interface_adapter.room_default.RoomDefaultViewModel;
import use_case.talk_to_npc.TalkToNpcOutputBoundary;
import use_case.talk_to_npc.TalkToNpcOutputData;

/**
 * The Presenter for the Talk To Npc Use Case.
 */
public class TalkToNpcPresenter implements TalkToNpcOutputBoundary {

    private final TalkToNpcViewModel talkToNpcViewModel;
    private final RoomDefaultViewModel roomDefaultViewModel;
    private final MerchantViewModel merchantViewModel;
    private final ViewManagerModel viewManagerModel;

    public TalkToNpcPresenter(ViewManagerModel viewManagerModel,
                                TalkToNpcViewModel talkToNpcViewModel,
                                MerchantViewModel merchantViewModel,
                                RoomDefaultViewModel roomDefaultViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.talkToNpcViewModel = talkToNpcViewModel;
        this.merchantViewModel = merchantViewModel;
        this.roomDefaultViewModel = roomDefaultViewModel;
    }

    @Override
    public void moveToNextDialogue(TalkToNpcOutputData response) {
        final TalkToNpcState talkToNpcState = this.talkToNpcViewModel.getState();

        talkToNpcState.setCurrentDialogueIndex(response.getCurrentDialogueIndex());

        this.talkToNpcViewModel.setState(talkToNpcState);
        this.talkToNpcViewModel.firePropertyChanged();

        this.viewManagerModel.setState(talkToNpcViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToMerchantView() {
        final RoomDefaultState roomDefaultState = roomDefaultViewModel.getState();
        roomDefaultState.setInteractAvailable(false);

        final MerchantState merchantState = merchantViewModel.getState();
        this.merchantViewModel.setState(merchantState);
        this.merchantViewModel.firePropertyChanged();

        this.viewManagerModel.setState(merchantViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void exitInteraction() {
        final RoomDefaultState roomDefaultState = roomDefaultViewModel.getState();
        roomDefaultState.setInteractAvailable(false);

        this.roomDefaultViewModel.setState(roomDefaultState);
        this.roomDefaultViewModel.firePropertyChanged();

        this.viewManagerModel.setState(roomDefaultViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
