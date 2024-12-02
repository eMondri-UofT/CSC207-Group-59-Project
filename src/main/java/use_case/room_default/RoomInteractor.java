package use_case.room_default;

import java.util.List;

import entity.*;
import use_case.character_creation.CharacterCreationDataAccessInterface;

/**
 * The Room Default Interactor.
 */
public class RoomInteractor implements RoomInputBoundary {
    private final RoomOutputBoundary roomPresenter;
    private final RoomDataAccessInterface roomDataAccessObject;
    private final NpcRoomDataAccessInterface npcRoomDataAccessObject;
    private final TrapRoomDataAccessInterface trapRoomDataAccessObject;
    private final CharacterCreationDataAccessInterface characterCreationDataAccessObject;

    public RoomInteractor(RoomOutputBoundary roomPresenter, RoomDataAccessInterface roomDataAccessInterface,
                          NpcRoomDataAccessInterface npcRoomDataAccessInterface,
                          TrapRoomDataAccessInterface trapRoomDataAccessInterface,
                          CharacterCreationDataAccessInterface characterCreationDataAccessInterface) {
        this.roomPresenter = roomPresenter;
        this.roomDataAccessObject = roomDataAccessInterface;
        this.npcRoomDataAccessObject = npcRoomDataAccessInterface;
        this.trapRoomDataAccessObject = trapRoomDataAccessInterface;
        this.characterCreationDataAccessObject = characterCreationDataAccessInterface;
    }

    @Override
    public void interactWithRoom() {
        final AbstractRoom abstractRoom = roomDataAccessObject.getCurrentRoom();

        if (abstractRoom == null) {
            roomPresenter.prepareFailView("Room not found.");
        }
        else if (abstractRoom instanceof NpcRoom) {
            final NpcRoom npcRoom = (NpcRoom) abstractRoom;
            npcRoomDataAccessObject.loadNpcs();
            final Npc npc = npcRoomDataAccessObject.generateRandomNpc();

            roomPresenter.prepareSuccessView(new NpcRoomOutputData(
                    npcRoom.getDescription(),
                    npc.getName(),
                    npc.getDescription(),
                    npc.getDialogue(),
                    npc.getCurrentDialogueIndex(),
                    npc.hasNextDialogue(),
                    npc.isMerchant()
            ));
        }
        else if (abstractRoom instanceof TrapRoom) {
            final TrapRoom trapRoom = (TrapRoom) abstractRoom;
            trapRoomDataAccessObject.loadTraps();
            final Trap trap = trapRoomDataAccessObject.generateRandomTrap();
            trapRoomDataAccessObject.setCurrentTrapName(trap.getName());
            trapRoom.setTrap(trap);

            final int health = characterCreationDataAccessObject.getInstance().getHealth();
            characterCreationDataAccessObject.getInstance().setHealth(health - trap.getDamage());

            roomPresenter.prepareSuccessView(new TrapRoomOutputData(
                    trapRoom.getDescription(),
                    trap.getName(),
                    trap.getDamage(),
                    characterCreationDataAccessObject.getInstance().getHealth()
            ));
        }
        else {
            roomPresenter.prepareSuccessView(new RoomOutputData(abstractRoom.getDescription(),
                    abstractRoom.getClass().getSimpleName()));
        }
    }

    @Override
    public void goToNextRoom() {
        final List<AbstractRoom> abstractRooms = roomDataAccessObject.getFloor().getRoomList();

        if (roomDataAccessObject.getCurrentRoomIndex() < abstractRooms.size() - 1) {
            roomDataAccessObject.setCurrentRoomIndex(roomDataAccessObject.getCurrentRoomIndex() + 1);
            final AbstractRoom nextAbstractRoom = abstractRooms.get(roomDataAccessObject.getCurrentRoomIndex());
            roomPresenter.prepareNextRoomView(new RoomOutputData(nextAbstractRoom.getDescription(),
                    nextAbstractRoom.getClass().getSimpleName()));
        }
        else {
            Floor newFloor = roomDataAccessObject.makeNewFloor();
            roomDataAccessObject.setCurrentRoomIndex(0);
            final AbstractRoom newRoom = newFloor.getRoomList().get(0);
            roomPresenter.prepareNextRoomView(new RoomOutputData(newRoom.getDescription(),
                    newRoom.getClass().getSimpleName()));
        }
    }

    @Override
    public void returnToMainMenu() {
        roomPresenter.prepareInventoryView();
    }
}
