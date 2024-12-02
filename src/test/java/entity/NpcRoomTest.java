package entity;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for NpcRoom.
 */
public class NpcRoomTest {
    @Test
    void testConstructorAndGetters() {
        int roomNumber = 5;
        NpcRoom npcRoom = new NpcRoom(roomNumber);
        assertEquals(roomNumber, npcRoom.getRoomNumber());
        assertNull(npcRoom.getNpc());
    }

    @Test
    void testSetAndGetNpc() {
        NpcRoom npcRoom = new NpcRoom(1);
        Npc npc = new Npc("ALCHEMIST", "Medium humanoid (Artificer), any alignment",
                Arrays.asList(
                        "Careful there, I just cleaned this place.",
                        "This? Just a little something I'm experimenting with.",
                        "Sometimes I wonder if all this work is worth it.",
                        "The right formula can change everything - or ruin it.",
                        "My best ideas come when I'm not even trying.",
                        "Don't touch that! Oh, wait... never mind.",
                        "If this works, I might finally get some respect around here."
                ), false);
        npcRoom.setNpc(npc);
        assertEquals(npc, npcRoom.getNpc());
    }

    @Test
    void testGetRoomType() {
        NpcRoom npcRoom = new NpcRoom(10);
        assertEquals(3, npcRoom.getRoomType());
    }

    @Test
    void testGetDescription() {
        int roomNumber = 7;
        NpcRoom npcRoom = new NpcRoom(roomNumber);
        assertEquals("Room 7: Someone is in this room.", npcRoom.getDescription());
    }
}