package use_case.character_creation;

import entity.AbstractRoom;
import use_case.room_default.RoomDataAccessInterface;

/**
 * The Character Creation Interactor.
 */
public class CharacterCreationInteractor implements CharacterCreationInputBoundary {
    private CharacterCreationDataAccessInterface characterDataAccessObject;
    private RoomDataAccessInterface roomDataAccessObject;
    private CharacterCreationOutputBoundary characterCreationPresenter;

    public CharacterCreationInteractor(CharacterCreationDataAccessInterface characterDataAccessInterface,
                                   CharacterCreationOutputBoundary characterCreationOutputBoundary,
                                       RoomDataAccessInterface roomDataAccessInterface) {
        this.characterDataAccessObject = characterDataAccessInterface;
        this.roomDataAccessObject = roomDataAccessInterface;
        this.characterCreationPresenter = characterCreationOutputBoundary;
    }

    @Override
    public void execute(CharacterCreationInputData characterCreationInputData) {
        characterDataAccessObject.setPclass(characterCreationInputData.getPclass());
        characterDataAccessObject.setPrace(characterCreationInputData.getPrace());

        String pClassDescription = characterDataAccessObject.getPClassDescription();
        String pRaceDescription = characterDataAccessObject.getPRaceDescription();

        characterCreationPresenter.getDescription(pClassDescription, pRaceDescription);
    }

    @Override
    public void execute() {
        if (characterDataAccessObject.getPclass() == null || characterDataAccessObject.getPrace() == null) {
            characterCreationPresenter.prepareFailView("Please Select Both a Class and a Race!");
        }
        else {
            roomDataAccessObject.setCurrentRoomIndex(0);
            final AbstractRoom room = roomDataAccessObject.getFloor().getRoomList().get(0);
            CharacterCreationOutputData characterCreationOutputData = new CharacterCreationOutputData(
                    room.getDescription(), room.getClass().getSimpleName()
            );
            characterCreationPresenter.prepareSuccessView(characterCreationOutputData);
        }
    }
}

