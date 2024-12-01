package interface_adapter.fall_for_trap;

import interface_adapter.ViewManagerModel;
import interface_adapter.room_default.RoomDefaultState;
import interface_adapter.room_default.RoomDefaultViewModel;
import use_case.fall_for_trap.FallForTrapOutputBoundary;
import use_case.fall_for_trap.FallForTrapOutputData;

/**
 * The Presenter for the Fall For Trap Use Case.
 */
public class FallForTrapPresenter implements FallForTrapOutputBoundary {

    private final FallForTrapViewModel fallForTrapViewModel;
    private final RoomDefaultViewModel roomDefaultViewModel;
    private final ViewManagerModel viewManagerModel;

    public FallForTrapPresenter(ViewManagerModel viewManagerModel,
                                FallForTrapViewModel fallForTrapViewModel,
                                RoomDefaultViewModel roomDefaultViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.fallForTrapViewModel = fallForTrapViewModel;
        this.roomDefaultViewModel = roomDefaultViewModel;
    }

    @Override
    public void exitInteraction() {
        final RoomDefaultState roomDefaultState = roomDefaultViewModel.getState();
        this.roomDefaultViewModel.setState(roomDefaultState);
        this.roomDefaultViewModel.firePropertyChanged();

        this.viewManagerModel.setState(roomDefaultViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
