package use_case.room_default;

import data_access.PlayerDataAccessObject;
import entity.AbstractRoom;
import entity.Floor;
import entity.TrapRoom;
import org.junit.jupiter.api.Test;

import data_access.NpcDataAccessObject;
import data_access.RoomDataAccessObject;
import data_access.TrapDataAccessObject;
import use_case.character_creation.CharacterCreationDataAccessInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class RoomInteractorTest {

    @Test
    void successNpcRoomTest() {
        final RoomDataAccessInterface roomRepository = new RoomDataAccessObject();
        final NpcRoomDataAccessInterface npcRepository = new NpcDataAccessObject();
        final TrapRoomDataAccessInterface trapRepository = new TrapDataAccessObject();
        final CharacterCreationDataAccessInterface playerRepository = new PlayerDataAccessObject();

        while (!roomRepository.getCurrentRoom().getClass().getSimpleName().equals("NpcRoom")) {
            roomRepository.setCurrentRoomIndex(roomRepository.getCurrentRoomIndex() + 1);
            if (roomRepository.getCurrentRoomIndex() >= roomRepository.getFloor().getRoomList().size() - 1) {
                final Floor newFloor = roomRepository.makeNewFloor();
                roomRepository.setFloor(newFloor);
                roomRepository.setCurrentRoomIndex(0);
            }
        }

        final RoomOutputBoundary successPresenter = new RoomOutputBoundary() {
            @Override
            public void prepareSuccessView(RoomOutputData outputData) {
                if (outputData instanceof NpcRoomOutputData) {
                    final NpcRoomOutputData npcRoomOutputData = (NpcRoomOutputData) outputData;
                    assertEquals(roomRepository.getCurrentRoom().getClass().getSimpleName(), npcRoomOutputData.getRoomType());
                    assertEquals(roomRepository.getCurrentRoom().getDescription(), npcRoomOutputData.getRoomDescription());
                    assertEquals(npcRepository.getCurrentNpc().getName(), npcRoomOutputData.getName());
                    assertEquals(npcRepository.getCurrentNpc().getDescription(), npcRoomOutputData.getDescription());
                    assertEquals(npcRepository.getCurrentNpc().getDialogue(), npcRoomOutputData.getDialogue());
                    assertEquals(npcRepository.getCurrentNpc().getCurrentDialogueIndex(), npcRoomOutputData.getCurrentDialogueIndex());
                    assertEquals(npcRepository.getCurrentNpc().hasNextDialogue(), npcRoomOutputData.hasNextDialogue());
                    assertEquals(npcRepository.getCurrentNpc().isMerchant(), npcRoomOutputData.isMerchant());
                    }
            }

            @Override
            public void prepareNextRoomView(RoomOutputData outputData) {
                assertEquals(roomRepository.getCurrentRoom().getClass().getSimpleName(), outputData.getRoomType());
                assertEquals(roomRepository.getCurrentRoom().getDescription(), outputData.getRoomDescription());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void prepareInventoryView() {
                // This is expected
            }
        };

        final RoomInputBoundary interactor = new RoomInteractor(successPresenter, roomRepository,
                npcRepository, trapRepository, playerRepository);
        interactor.interactWithRoom();
        interactor.goToNextRoom();
        interactor.returnToMainMenu();
    }

    @Test
    void successTrapRoomTest() {
        final RoomDataAccessInterface roomRepository = new RoomDataAccessObject();
        final NpcDataAccessObject npcRepository = new NpcDataAccessObject();
        final TrapRoomDataAccessInterface trapRepository = new TrapDataAccessObject();
        final CharacterCreationDataAccessInterface playerRepository = new PlayerDataAccessObject();

        while (!roomRepository.getCurrentRoom().getClass().getSimpleName().equals("TrapRoom")) {
            roomRepository.setCurrentRoomIndex(roomRepository.getCurrentRoomIndex() + 1);
            if (roomRepository.getCurrentRoomIndex() >= roomRepository.getFloor().getRoomList().size() - 1) {
                final Floor newFloor = roomRepository.makeNewFloor();
                roomRepository.setFloor(newFloor);
                roomRepository.setCurrentRoomIndex(0);
            }
        }

        final RoomOutputBoundary successPresenter = new RoomOutputBoundary() {
            @Override
            public void prepareSuccessView(RoomOutputData outputData) {
                if (outputData instanceof TrapRoomOutputData) {
                    final TrapRoomOutputData trapRoomOutputData = (TrapRoomOutputData) outputData;
                    assertEquals(roomRepository.getCurrentRoom().getClass().getSimpleName(), trapRoomOutputData.getRoomType());
                    assertEquals(roomRepository.getCurrentRoom().getDescription(), trapRoomOutputData.getRoomDescription());
                    assertEquals(trapRepository.getCurrentTrapName(), trapRoomOutputData.getTrapName());
                    assertEquals(trapRepository.getCurrentTrap().getDamage(), trapRoomOutputData.getDamage());
                    assertEquals(playerRepository.getInstance().getHealth(), trapRoomOutputData.getHealth());
                }
            }

            @Override
            public void prepareNextRoomView(RoomOutputData outputData) {
                assertEquals(roomRepository.getCurrentRoom().getClass().getSimpleName(), outputData.getRoomType());
                assertEquals(roomRepository.getCurrentRoom().getDescription(), outputData.getRoomDescription());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void prepareInventoryView() {
                // This is expected
            }
        };

        final RoomInputBoundary interactor = new RoomInteractor(successPresenter, roomRepository,
                npcRepository, trapRepository, playerRepository);
        interactor.interactWithRoom();
        interactor.goToNextRoom();
        interactor.goToNextRoom();
        interactor.goToNextRoom();
        interactor.goToNextRoom();
        interactor.goToNextRoom();
        interactor.goToNextRoom();
        interactor.goToNextRoom();
        interactor.goToNextRoom();
        interactor.returnToMainMenu();
    }
}
