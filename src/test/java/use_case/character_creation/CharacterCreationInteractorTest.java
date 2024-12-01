package use_case.character_creation;

import data_access.PlayerDataAccessObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignupInteractorTest {

    @Test
    void successfulAPITest() {
        CharacterCreationInputData inputData = new CharacterCreationInputData("barbarian", "dwarf");
        CharacterCreationDataAccessInterface playerRepository = new PlayerDataAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        CharacterCreationOutputBoundary successPresenter = new CharacterCreationOutputBoundary() {
            @Override
            public void prepareSuccessView(CharacterCreationOutputData outputData) {
                fail("Failure with a 'success' call is unexpected");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Failure with a 'fail' call is unexpected.");
            }

            @Override
            public void getDescription(String pClassDescription, String pRaceDescription) {
                // This checks that both descriptions are as expected for inputs of "barbarian" and "dwarf" match expected
                assertEquals("Damage: 12, Armor: 3", pClassDescription);
                assertEquals("Damage: 0, Armor: 2", pRaceDescription);
            }
        };

        CharacterCreationInputBoundary interactor = new CharacterCreationInteractor(playerRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void failureNoSelectedClassTest() {
        CharacterCreationDataAccessInterface playerRepository = new PlayerDataAccessObject();
        playerRepository.setPclass(null);

        // This creates a successPresenter that tests whether the test case is as we expect.
        CharacterCreationOutputBoundary successPresenter = new CharacterCreationOutputBoundary() {
            @Override
            public void prepareSuccessView(CharacterCreationOutputData outputData) {
                fail("Failure with a 'success' call is unexpected");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                // This checks that the (only) error message gets sent
                assertEquals("Please Select Both a Class and a Race!", errorMessage);
            }

            @Override
            public void getDescription(String pClassDescription, String pRaceDescription) {
                fail("A getDescription() call is unexpected");
            }
        };

        CharacterCreationInputBoundary interactor = new CharacterCreationInteractor(playerRepository, successPresenter);
        interactor.execute();
    }
}