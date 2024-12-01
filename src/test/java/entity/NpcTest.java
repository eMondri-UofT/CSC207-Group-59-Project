package entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NpcTest {

    private Npc npc;
    private List<String> dialogues;

    @BeforeEach
    public void setUp() {
        dialogues = Arrays.asList(
                "Careful there, I just cleaned this place.",
                "This? Just a little something I'm experimenting with.",
                "Sometimes I wonder if all this work is worth it.",
                "The right formula can change everything - or ruin it.",
                "My best ideas come when I'm not even trying.",
                "Don't touch that! Oh, wait... never mind.",
                "If this works, I might finally get some respect around here."
        );
        npc = new Npc("ALCHEMIST", "Medium humanoid (Artificer), any alignment", dialogues);
    }

    @Test
    void testNpcCreation() {
        assertEquals("ALCHEMIST", npc.getName());
        assertEquals("Medium humanoid (Artificer), any alignment", npc.getDescription());
        assertEquals(dialogues, npc.getDialogue());
        assertEquals(0, npc.getCurrentDialogueIndex());
    }

    @Test
    void testSetName() {
        npc.setName("WIZARD");
        assertEquals("WIZARD", npc.getName());
    }

    @Test
    void testSetDescription() {
        npc.setDescription("A powerful spellcaster with deep knowledge of arcane arts.");
        assertEquals("A powerful spellcaster with deep knowledge of arcane arts.", npc.getDescription());
    }

    @Test
    void testSetDialogue() {
        final List<String> newDialogues = Arrays.asList(
                "Magic is not just power, it's understanding.",
                "The weave of magic connects all things."
        );
        npc.setDialogue(newDialogues);
        assertEquals(newDialogues, npc.getDialogue());
    }

    @Test
    void testGetCurrentDialogue() {
        assertEquals("Careful there, I just cleaned this place.", npc.getCurrentDialogue());
    }

    @Test
    void testHasNextDialogue() {
        assertTrue(npc.hasNextDialogue());
        npc.moveToNextDialogue();
        npc.moveToNextDialogue();
        npc.moveToNextDialogue();
        npc.moveToNextDialogue();
        npc.moveToNextDialogue();
        npc.moveToNextDialogue();
        assertFalse(npc.hasNextDialogue());
    }

    @Test
    void testMoveToNextDialogue() {
        npc.moveToNextDialogue();
        assertEquals(1, npc.getCurrentDialogueIndex());
        assertEquals("This? Just a little something I'm experimenting with.", npc.getCurrentDialogue());
    }

    @Test
    void testMoveToNextDialogueDoesNotExceedLimit() {
        for (int i = 0; i < 10; i++) {
            npc.moveToNextDialogue();
        }
        assertEquals(6, npc.getCurrentDialogueIndex());
        assertEquals("If this works, I might finally get some respect around here.", npc.getCurrentDialogue());
    }

    @Test
    void testIsMerchant() {
        assertFalse(npc.isMerchant());
    }
}
