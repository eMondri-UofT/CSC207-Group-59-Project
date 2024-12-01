package use_case.talk_to_npc;

import entity.Npc;
import entity.NpcRoom;

import java.util.List;

/**
 * The Talk To Npc Interactor.
 */
public class TalkToNpcInteractor implements TalkToNpcInputBoundary{
    private TalkToNpcDataAccessInterface talkToNpcDataAccessObject;
    private TalkToNpcOutputBoundary talkToNpcPresenter;

    public TalkToNpcInteractor(TalkToNpcDataAccessInterface talkToNpcDataAccessInterface,
                               TalkToNpcOutputBoundary talkToNpcOutputBoundary) {
        this.talkToNpcDataAccessObject = talkToNpcDataAccessInterface;
        this.talkToNpcPresenter = talkToNpcOutputBoundary;
    }

    @Override
    public void moveToNextDialogue(String name, String description, List<String> dialogue,
                                   int currentDialogueIndex, boolean hasNextDialogue, boolean isMerchant) {
        if (hasNextDialogue) {
            currentDialogueIndex++;
        }

        TalkToNpcOutputData talkToNpcOutputData = new TalkToNpcOutputData(name, description, dialogue,
                currentDialogueIndex, hasNextDialogue, isMerchant);

        talkToNpcPresenter.moveToNextDialogue(talkToNpcOutputData);
    }

    @Override
    public void switchToMerchantView() {
        talkToNpcPresenter.switchToMerchantView();
    }

    @Override
    public void exitInteraction() {
        talkToNpcPresenter.exitInteraction();
    }
}
