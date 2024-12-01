package use_case.character_creation;

import entity.Room;
import entity.Floor;
import use_case.room_default.RoomOutputData;

import java.util.List;

/**
 * The Character Creation Interactor.
 */
public class CharacterCreationInteractor implements CharacterCreationInputBoundary {
    private CharacterCreationDataAccessInterface characterDataAccessObject;
    private CharacterCreationOutputBoundary characterCreationPresenter;
    private Floor floor;

    public CharacterCreationInteractor(CharacterCreationDataAccessInterface characterDataAccessInterface,
                                   CharacterCreationOutputBoundary characterCreationOutputBoundary, Floor floor) {
        this.characterDataAccessObject = characterDataAccessInterface;
        this.characterCreationPresenter = characterCreationOutputBoundary;
        this.floor = floor;
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
            List<Room> rooms = floor.getRoomList();
            Room room = rooms.get(0);
            CharacterCreationOutputData outputData = new CharacterCreationOutputData(room.getDescription(), room.getClass().getSimpleName());
            characterCreationPresenter.prepareSuccessView(outputData);
        }
    }
}

