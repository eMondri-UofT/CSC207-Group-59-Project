package use_case.room_default;

import org.junit.jupiter.api.Test;

import data_access.NpcDataAccessObject;
import data_access.RoomDataAccessObject;
import data_access.TrapDataAccessObject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class RoomInteractorTest {
//
//    @Test
//    void successTest() {
//        final RoomDataAccessInterface roomRepository = new RoomDataAccessObject();
//        final NpcDataAccessObject npcRepository = new NpcDataAccessObject();
//        final TrapRoomDataAccessInterface trapRepository = new TrapDataAccessObject();
//
//        final RoomOutputBoundary successPresenter = new RoomOutputBoundary() {
//            @Override
//            public void prepareSuccessView(RoomOutputData outputData) {
//                assertEquals(roomRepository.getCurrentRoom().getClass().getSimpleName(), outputData.getRoomType());
//                assertEquals(roomRepository.getCurrentRoom().getDescription(), outputData.getRoomDescription());
//            }
//
//            @Override
//            public void prepareNextRoomView(RoomOutputData outputData) {
//                assertEquals(roomRepository.getCurrentRoom().getClass().getSimpleName(), outputData.getRoomType());
//                assertEquals(roomRepository.getCurrentRoom().getDescription(), outputData.getRoomDescription());
//            }
//
//            @Override
//            public void prepareFailView(String errorMessage) {
//                fail("Use case failure is unexpected.");
//            }
//
//            @Override
//            public void prepareMainMenuView() {
//                // This is expected
//            }
//        };
//
//        final RoomInputBoundary interactor = new RoomInteractor(successPresenter, roomRepository,
//                npcRepository, trapRepository);
//        interactor.interactWithRoom();
//        interactor.goToNextRoom();
//        interactor.returnToMainMenu();
//    }
}
