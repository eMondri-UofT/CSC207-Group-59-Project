package entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Monster_Test {

    @Test
    void testMonsterInitialization() {
        Monster monster = new Monster(1, "bob");
        assertEquals(1, monster.getHealth());
        assertEquals("bob", monster.getName());
    }

    @Test
    void testMonsterInitialization2() {
        Monster monster = new Monster();
        monster.setHealth(10);
        assertEquals("10", monster.getHealth());
        assertEquals("Basic Monster", monster.getName());
    }
}
