package interface_adapter.room_default;

import interface_adapter.ViewManagerModel;
import interface_adapter.fall_for_trap.FallForTrapState;
import interface_adapter.fall_for_trap.FallForTrapViewModel;
import interface_adapter.open_inventory.OpenInventoryState;
import interface_adapter.open_inventory.OpenInventoryViewModel;
import interface_adapter.talk_to_npc.TalkToNpcState;
import interface_adapter.talk_to_npc.TalkToNpcViewModel;
import use_case.room_default.NpcRoomOutputData;
import use_case.room_default.RoomOutputBoundary;
import use_case.room_default.RoomOutputData;
import use_case.room_default.TrapRoomOutputData;

/**
 * Presenter for the Room Default Use Case.
 */
public class RoomDefaultPresenter implements RoomOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final RoomDefaultViewModel roomDefaultViewModel;
    private final TalkToNpcViewModel talkToNpcViewModel;
    private final FallForTrapViewModel fallForTrapViewModel;
    private final OpenInventoryViewModel openInventoryViewModel;

    /**
     * Constructor for RoomDefaultPresenter.
     * @param viewManagerModel the model managing view states.
     * @param roomDefaultViewModel the view model for the default room state.
     * @param talkToNpcViewModel the view model for NPC interactions.
     * @param fallForTrapViewModel the view model for traps.
     * @param openInventoryViewModel the view model for the inventory.
     */
    public RoomDefaultPresenter(ViewManagerModel viewManagerModel,
                                RoomDefaultViewModel roomDefaultViewModel,
                                TalkToNpcViewModel talkToNpcViewModel,
                                FallForTrapViewModel fallForTrapViewModel,
                                OpenInventoryViewModel openInventoryViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.roomDefaultViewModel = roomDefaultViewModel;
        this.talkToNpcViewModel = talkToNpcViewModel;
        this.fallForTrapViewModel = fallForTrapViewModel;
        this.openInventoryViewModel = openInventoryViewModel;
    }

    @Override
    public void prepareSuccessView(RoomOutputData outputData) {
        final String roomType = outputData.getRoomType();
        switch (roomType) {
            case "ItemRoom":
                viewManagerModel.setState("pick up item");
                break;

            case "TrapRoom":
                if (outputData instanceof TrapRoomOutputData) {
                    final TrapRoomOutputData trapRoomOutputData = (TrapRoomOutputData) outputData;
                    final FallForTrapState fallForTrapState = fallForTrapViewModel.getState();

                    fallForTrapState.setName(trapRoomOutputData.getTrapName());
                    fallForTrapState.setDamage(trapRoomOutputData.getDamage());
                    fallForTrapState.setHealth(trapRoomOutputData.getHealth());

                    this.fallForTrapViewModel.setState(fallForTrapState);
                    this.fallForTrapViewModel.firePropertyChanged();

                    this.viewManagerModel.setState(fallForTrapViewModel.getViewName());
                    this.viewManagerModel.firePropertyChanged();
                }
                else {
                    throw new IllegalArgumentException("Invalid RoomOutputData for TrapRoom.");
                }
                break;

            case "MonsterRoom":
                viewManagerModel.setState("fight monster");
                break;

            case "NpcRoom":
                if (outputData instanceof NpcRoomOutputData) {
                    final NpcRoomOutputData npcRoomOutputData = (NpcRoomOutputData) outputData;
                    final TalkToNpcState talkToNpcState = talkToNpcViewModel.getState();

                    talkToNpcState.setName(npcRoomOutputData.getName());
                    talkToNpcState.setDescription(npcRoomOutputData.getDescription());
                    talkToNpcState.setDialogue(npcRoomOutputData.getDialogue());
                    talkToNpcState.setCurrentDialogueIndex(npcRoomOutputData.getCurrentDialogueIndex());
                    talkToNpcState.setNextDialogue(npcRoomOutputData.hasNextDialogue());
                    talkToNpcState.setMerchant(npcRoomOutputData.isMerchant());

                    this.talkToNpcViewModel.setState(talkToNpcState);
                    this.talkToNpcViewModel.firePropertyChanged();

                    this.viewManagerModel.setState(talkToNpcViewModel.getViewName());
                    this.viewManagerModel.firePropertyChanged();
                }
                else {
                    throw new IllegalArgumentException("Invalid RoomOutputData for NpcRoom.");
                }
                break;

            default:
                viewManagerModel.setState("room view");
        }
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareNextRoomView(RoomOutputData outputData) {
        final RoomDefaultState roomDefaultState = this.roomDefaultViewModel.getState();
        roomDefaultState.setInteractAvailable(true);

        roomDefaultState.setRoomDescription(outputData.getRoomDescription());
        roomDefaultState.setRoomType(outputData.getRoomType());

        this.roomDefaultViewModel.setState(roomDefaultState);
        this.roomDefaultViewModel.firePropertyChanged();

        this.viewManagerModel.setState(roomDefaultViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        viewManagerModel.setState("room view");
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareInventoryView() {
        final OpenInventoryState openInventoryState = openInventoryViewModel.getState();
        this.openInventoryViewModel.setState(openInventoryState);
        this.openInventoryViewModel.firePropertyChanged();

        viewManagerModel.setState(openInventoryViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
