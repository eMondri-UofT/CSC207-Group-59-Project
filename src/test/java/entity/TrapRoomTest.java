package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrapRoomTest {

    @Test
    void testConstructorAndGetters() {
        int roomNumber = 3;
        TrapRoom trapRoom = new TrapRoom(roomNumber);
        assertEquals(roomNumber, trapRoom.getRoomNumber());
        assertNull(trapRoom.getTrap());
    }

    @Test
    void testSetAndGetTrap() {
        TrapRoom trapRoom = new TrapRoom(4);
        Trap trap = new Trap("Spikes", 20);
        trapRoom.setTrap(trap);
        assertEquals(trap, trapRoom.getTrap());
    }

    @Test
    void testGetRoomType() {
        TrapRoom trapRoom = new TrapRoom(5);
        assertEquals(2, trapRoom.getRoomType());
    }

    @Test
    void testGetDescription() {
        int roomNumber = 8;
        TrapRoom trapRoom = new TrapRoom(roomNumber);
        assertEquals("Room 8: The room appears empty and harmless.", trapRoom.getDescription());
    }
}