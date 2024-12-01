package use_case.room_default;

import java.util.List;

/**
 * Represents the output data for an NPC room.
 */
public class NpcRoomOutputData extends RoomOutputData {

    private final String name;
    private final String description;
    private final List<String> dialogue;
    private final int currentDialogueIndex;
    private final boolean hasNextDialogue;
    private final boolean isMerchant;

    public NpcRoomOutputData(String roomDescription, String name, String description, List<String> dialogue,
                             int currentDialogueIndex, boolean hasNextDialogue, boolean isMerchant) {
        super(roomDescription, "NpcRoom");
        this.name = name;
        this.description = description;
        this.dialogue = dialogue;
        this.currentDialogueIndex = currentDialogueIndex;
        this.hasNextDialogue = hasNextDialogue;
        this.isMerchant = isMerchant;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getDialogue() {
        return dialogue;
    }

    public int getCurrentDialogueIndex() {
        return currentDialogueIndex;
    }

    /**
     * Checks whether the NPC has next dialogue.
     * @return true if the NPC has next dialogue, false otherwise.
     */
    public boolean hasNextDialogue() {
        return hasNextDialogue;
    }

    public boolean isMerchant() {
        return isMerchant;
    }
}
