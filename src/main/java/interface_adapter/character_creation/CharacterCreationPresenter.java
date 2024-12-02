package interface_adapter.character_creation;

import interface_adapter.ViewManagerModel;
import interface_adapter.room_default.RoomDefaultState;
import interface_adapter.room_default.RoomDefaultViewModel;
import use_case.character_creation.CharacterCreationOutputBoundary;
import use_case.character_creation.CharacterCreationOutputData;

/**
 * The Presenter for the Open Inventory Use Case.
 */
public class CharacterCreationPresenter implements CharacterCreationOutputBoundary {
    private CharacterCreationViewModel characterCreationViewModel;
    private ViewManagerModel viewManagerModel;
    private RoomDefaultViewModel roomDefaultViewModel;

    public CharacterCreationPresenter(ViewManagerModel viewManagerModel,
                          CharacterCreationViewModel characterCreationViewModel,
                          RoomDefaultViewModel roomDefaultViewModel) {
        this.characterCreationViewModel = characterCreationViewModel;
        this.viewManagerModel = viewManagerModel;
        this.roomDefaultViewModel = roomDefaultViewModel;
    }

    @Override
    public void prepareSuccessView(CharacterCreationOutputData response) {
        final RoomDefaultState roomDefaultState = this.roomDefaultViewModel.getState();

        roomDefaultState.setRoomDescription(response.getRoomDescription());
        roomDefaultState.setRoomType(response.getRoomType());
        this.roomDefaultViewModel.setState(roomDefaultState);
        this.roomDefaultViewModel.firePropertyChanged();

        this.viewManagerModel.setState(roomDefaultViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final CharacterCreationState characterCreationState = characterCreationViewModel.getState();
        characterCreationState.setError(error);
        this.characterCreationViewModel.setState(characterCreationState);
        this.characterCreationViewModel.firePropertyChanged();
    }

    @Override
    public void getDescription(String pClassDescription, String pRaceDescription) {
        final CharacterCreationState characterCreationState = characterCreationViewModel.getState();
        characterCreationState.setPClassDescription(pClassDescription);
        characterCreationState.setPRaceDescription(pRaceDescription);
        this.characterCreationViewModel.setState(characterCreationState);
        this.characterCreationViewModel.firePropertyChanged();
    }
}
