package interface_adapter.talk_to_npc;

import java.util.List;

import use_case.talk_to_npc.TalkToNpcInputBoundary;
import use_case.talk_to_npc.TalkToNpcInputData;

/**
 * Controller for the Talk to NPC Use Case.
 */
public class TalkToNpcController {

    private final TalkToNpcInputBoundary talkToNpcUseCaseInteractor;

    public TalkToNpcController(TalkToNpcInputBoundary talkToNpcUseCaseInteractor) {
        this.talkToNpcUseCaseInteractor = talkToNpcUseCaseInteractor;
    }

    /**
     * Moves to the next dialogue with the NPC.
     *
     * @param name the name of the NPC.
     * @param description the description of the NPC.
     * @param dialogue the list of dialogues available for the NPC.
     * @param currentDialogueIndex the current dialogue index being displayed.
     * @param hasNextDialogue whether the NPC has more dialogue available.
     * @param isMerchant whether the NPC is a merchant.
     */
    public void moveToNextDialogue(String name, String description, List<String> dialogue,
                                   int currentDialogueIndex, boolean hasNextDialogue, boolean isMerchant) {
        final TalkToNpcInputData talkToNpcInputData = new TalkToNpcInputData(name, description, dialogue,
                currentDialogueIndex, hasNextDialogue, isMerchant);
        talkToNpcUseCaseInteractor.moveToNextDialogue(talkToNpcInputData);
    }

    /**
     * Switches the view to the merchant interface if the NPC is a merchant.
     */
    public void switchToMerchantView() {
        talkToNpcUseCaseInteractor.switchToMerchantView();
    }

    /**
     * Exits the current interaction with the NPC.
     */
    public void exitInteraction() {
        talkToNpcUseCaseInteractor.exitInteraction();
    }
}
