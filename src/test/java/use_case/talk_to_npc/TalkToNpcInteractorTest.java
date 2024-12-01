package use_case.talk_to_npc;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import data_access.NpcDataAccessObject;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TalkToNpcInteractorTest {

    @Test
    void successTest() {
        final TalkToNpcInputData inputData = new TalkToNpcInputData(
                "ALCHEMIST",
                "Medium humanoid (Artificer), any alignment",
                Arrays.asList(
                        "Careful there, I just cleaned this place.",
                        "This? Just a little something I'm experimenting with.",
                        "Sometimes I wonder if all this work is worth it.",
                        "The right formula can change everything - or ruin it.",
                        "My best ideas come when I'm not even trying.",
                        "Don't touch that! Oh, wait... never mind.",
                        "If this works, I might finally get some respect around here."),
                        0,
                        true,
                        false
        );
        final TalkToNpcDataAccessInterface npcRepository = new NpcDataAccessObject();

        final TalkToNpcOutputBoundary successPresenter = new TalkToNpcOutputBoundary() {
            @Override
            public void moveToNextDialogue(TalkToNpcOutputData outputData) {
                assertEquals("ALCHEMIST", outputData.getName());
                assertEquals("Medium humanoid (Artificer), any alignment", outputData.getDescription());
                assertEquals(Arrays.asList(
                        "Careful there, I just cleaned this place.",
                        "This? Just a little something I'm experimenting with.",
                        "Sometimes I wonder if all this work is worth it.",
                        "The right formula can change everything - or ruin it.",
                        "My best ideas come when I'm not even trying.",
                        "Don't touch that! Oh, wait... never mind.",
                        "If this works, I might finally get some respect around here."), outputData.getDialogue());
                assertEquals(1, outputData.getCurrentDialogueIndex());
                assertEquals(true, outputData.hasNextDialogue());
                assertEquals(false, outputData.isMerchant());
            }

            @Override
            public void switchToMerchantView() {
                // This is expected
            }

            @Override
            public void exitInteraction() {
                // This is expected
            }
        };

        final TalkToNpcInputBoundary interactor = new TalkToNpcInteractor(npcRepository, successPresenter);
        interactor.moveToNextDialogue(inputData);
        interactor.switchToMerchantView();
        interactor.exitInteraction();
    }
}
