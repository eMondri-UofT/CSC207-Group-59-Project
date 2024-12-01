package use_case.talk_to_npc;

import java.util.List;

/**
 * Input Boundary for actions which are related to talking to NPC.
 */
public interface TalkToNpcInputBoundary {

    /**
     * Moves to the next dialogue with the NPC.
     * @param talkToNpcInputData input data for talk to npc.
     */
    void moveToNextDialogue(TalkToNpcInputData talkToNpcInputData);

    /**
     * Switches the view to the merchant interface if the NPC is a merchant.
     */
    void switchToMerchantView();

    /**
     * Exits the current interaction with the NPC.
     * Signals the interactor to end the ongoing interaction with the NPC and transition the game state as appropriate.
     */
    void exitInteraction();
}
