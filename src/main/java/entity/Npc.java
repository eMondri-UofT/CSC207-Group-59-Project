package entity;

import java.util.List;

/**
 * The representation of an NPC.
 */
public class Npc {
    private String name;
    private String description;
    private List<String> dialogue;
    private int currentDialogueIndex;

    public Npc(String name, String description, List<String> dialogue) {
        this.name = name;
        this.description = description;
        this.dialogue = dialogue;
        this.setCurrentDialogueIndex(0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getDialogue() {
        return dialogue;
    }

    public void setDialogue(List<String> dialogue) {
        this.dialogue = dialogue;
    }

    public int getCurrentDialogueIndex() {
        return currentDialogueIndex;
    }

    public void setCurrentDialogueIndex(int currentDialogueIndex) {
        this.currentDialogueIndex = currentDialogueIndex;
    }

    public String getCurrentDialogue() {
        return dialogue.get(currentDialogueIndex);
    }

    /**
     * Checks if the NPC has next dialogue.
     * @return true if there is next dialogue, false otherwise
     */
    public boolean hasNextDialogue() {
        return currentDialogueIndex < dialogue.size() - 1;
    }

    /**
     * Moves to the next dialogue in the list if available.
     */
    public void moveToNextDialogue() {
        if (hasNextDialogue()) {
            currentDialogueIndex++;
        }
    }

    /**
     * Checks if the NPC is a merchant.
     * @return false by default, true if npc is an instance of merchant
     */
    public boolean isMerchant() {
        return false;
    }
}
