package interface_adapter.talk_to_npc;

import java.util.List;

/**
 * The state for the Talk To Npc View Model.
 */
public class TalkToNpcState {
    private String name;
    private String description;
    private List<String> dialogue;
    private int currentDialogueIndex;
    private boolean hasNextDialogue;
    private boolean isMerchant;

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
     * @return true if the NPC has next dialogue, false otherwise.
     */
    public boolean hasNextDialogue() {
        return currentDialogueIndex < dialogue.size() - 1;
    }

    public void setNextDialogue(boolean nextDialogue) {
        this.hasNextDialogue = nextDialogue;
    }

    public boolean isMerchant() {
        return isMerchant;
    }

    public void setMerchant(boolean merchant) {
        this.isMerchant = merchant;
    }
}
