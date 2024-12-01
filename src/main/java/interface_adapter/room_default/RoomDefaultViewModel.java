package interface_adapter.room_default;

import interface_adapter.ViewModel;

/**
 * View Model for the Room Default View.
 */
public class RoomDefaultViewModel extends ViewModel<RoomDefaultState> {

    public RoomDefaultViewModel() {
        super("room view");
        setState(new RoomDefaultState());
    }

    /**
     * Updates the room description in the current state.
     * @param description the new description of the room.
     */
    public void updateRoomDescription(String description) {
        final RoomDefaultState currentState = getState();
        currentState.setRoomDescription(description);
        setState(currentState);
    }

    /**
     * Updates the error message in the current state.
     * @param errorMessage the new error message to display.
     */
    public void updateErrorMessage(String errorMessage) {
        final RoomDefaultState currentState = getState();
        currentState.setErrorMessage(errorMessage);
        setState(currentState);
    }

    /**
     * Clears the error message in the current state.
     */
    public void clearErrorMessage() {
        final RoomDefaultState currentState = getState();
        currentState.setErrorMessage("");
        setState(currentState);
    }
}
