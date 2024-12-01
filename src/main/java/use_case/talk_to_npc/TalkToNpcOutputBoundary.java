package use_case.talk_to_npc;

/**
 * The output boundary for the Talk To Npc Use Case.
 */
public interface TalkToNpcOutputBoundary {

    /**
     * Updates the view with the next dialogue of the NPC.
     * @param outputData the output data containing NPC details and dialogue information.
     */
    void moveToNextDialogue(TalkToNpcOutputData outputData);

    /**
     * Switches the view to the merchant interface when interacting with a merchant NPC.
     */
    void switchToMerchantView();

    /**
     * Exits the current interaction with the NPC.
     * Signals the view to transition to the appropriate state after the interaction ends.
     */
    void exitInteraction();
}
