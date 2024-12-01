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
        String roomType = response.getRoomType();
        switch (roomType) {
            case "ItemRoom":
                viewManagerModel.setState("pick up item");
                break;
            case "TrapRoom":
                viewManagerModel.setState("fall for trap");
                break;
            case "MonsterRoom":
                viewManagerModel.setState("fight monster");
                break;
            case "MerchantRoom":
                viewManagerModel.setState("merchant");
                break;
            default:
                viewManagerModel.setState("room view");
        }
        viewManagerModel.firePropertyChanged();
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
