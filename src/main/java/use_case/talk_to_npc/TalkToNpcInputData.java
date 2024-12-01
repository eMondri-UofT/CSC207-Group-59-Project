package use_case.talk_to_npc;

import java.util.List;

/**
 * The input data for the Talk To Npc Use Case.
 */
public class TalkToNpcInputData {

    private String name;
    private String description;
    private List<String> dialogue;
    private int currentDialogueIndex;
    private boolean hasNextDialogue;
    private boolean isMerchant;

    public TalkToNpcInputData(String name, String description, List<String> dialogue,
                              int currentDialogueIndex, boolean hasNextDialogue, boolean isMerchant) {
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

    public boolean isHasNextDialogue() {
        return hasNextDialogue;
    }

    public boolean isMerchant() {
        return isMerchant;
    }
}

