package use_case.talk_to_npc;

/**
 * The Talk To Npc Interactor.
 */
public class TalkToNpcInteractor implements TalkToNpcInputBoundary {
    private TalkToNpcDataAccessInterface talkToNpcDataAccessObject;
    private TalkToNpcOutputBoundary talkToNpcPresenter;

    public TalkToNpcInteractor(TalkToNpcDataAccessInterface talkToNpcDataAccessInterface,
                               TalkToNpcOutputBoundary talkToNpcOutputBoundary) {
        this.talkToNpcDataAccessObject = talkToNpcDataAccessInterface;
        this.talkToNpcPresenter = talkToNpcOutputBoundary;
    }

    @Override
    public void moveToNextDialogue(TalkToNpcInputData talkToNpcInputData) {
        int nextDialogueIndex = talkToNpcInputData.getCurrentDialogueIndex();
        if (talkToNpcInputData.isHasNextDialogue()) {
            nextDialogueIndex++;
        }

        final TalkToNpcOutputData talkToNpcOutputData = new TalkToNpcOutputData(
                talkToNpcInputData.getName(),
                talkToNpcInputData.getDescription(),
                talkToNpcInputData.getDialogue(),
                nextDialogueIndex,
                talkToNpcInputData.isHasNextDialogue(),
                talkToNpcInputData.isMerchant());

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
