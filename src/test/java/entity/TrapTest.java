package entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TrapTest {

    private Trap trap;

    @BeforeEach
    public void setUp() {
        trap = new Trap("Pendulums", 10);
    }

    @Test
    void testTrapCreation() {
        assertEquals("Pendulums", trap.getName());
        assertEquals(10, trap.getDamage());
    }

    @Test
    void testSetName() {
        trap.setName("Fire Trap");
        assertEquals("Fire Trap", trap.getName());
    }

    @Test
    void testSetDamage() {
        trap.setDamage(25);
        assertEquals(25, trap.getDamage());
    }
}
