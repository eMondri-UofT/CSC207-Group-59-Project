package entity;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void testItemInitialization() {
        Item item = new Item("Excalibur", "Weapon", "Legendary");

        assertEquals("Excalibur", item.getName());
        assertEquals("Weapon", item.getCategory());
        assertEquals("Legendary", item.getRarity());
    }

    @Test
    void testWeaponInitialization() {
        Weapon weapon = new Weapon("Sword of Valor", "Epic", 45);

        assertEquals("Sword of Valor", weapon.getName());
        assertEquals("Weapon", weapon.getCategory());
        assertEquals("Epic", weapon.getRarity());
        assertEquals(45, weapon.getAttackBoost());
    }

    @Test
    void testArmorInitialization() {
        Armor armor = new Armor("Shield of Fortitude", "Rare", 20);

        assertEquals("Shield of Fortitude", armor.getName());
        assertEquals("Armor", armor.getCategory());
        assertEquals("Rare", armor.getRarity());
        assertEquals(20, armor.getArmorBoost());
    }

    @Test
    void testBuffInitialization() {
        Buff buff = new Buff("Potion of Healing", "Common", 15);

        assertEquals("Potion of Healing", buff.getName());
        assertEquals("Buff", buff.getCategory());
        assertEquals("Common", buff.getRarity());
        assertEquals(15, buff.getHealthRestoration());
    }

    @Test
    void testGenerateRandomItem() {
        Item randomItem = Item.generateRandomItem();

        assertNotNull(randomItem.getName());
        assertNotNull(randomItem.getCategory());
        assertNotNull(randomItem.getRarity());

        assertTrue(randomItem instanceof Weapon || randomItem instanceof Armor || randomItem instanceof Buff);

        if (randomItem instanceof Weapon) {
            assertEquals("Weapon", randomItem.getCategory());
        } else if (randomItem instanceof Armor) {
            assertEquals("Armor", randomItem.getCategory());
        } else if (randomItem instanceof Buff) {
            assertEquals("Buff", randomItem.getCategory());
        }
    }

    @Test
    void testDeterminePrice() {
        Item commonItem = new Item("Basic Sword", "Weapon", "Common");
        Item rareItem = new Item("Mystic Bow", "Weapon", "Rare");
        Item epicItem = new Item("Dragon Shield", "Armor", "Epic");
        Item legendaryItem = new Item("Excalibur", "Weapon", "Legendary");

        assertEquals(10, commonItem.getPrice());
        assertEquals(25, rareItem.getPrice());
        assertEquals(50, epicItem.getPrice());
        assertEquals(100, legendaryItem.getPrice());
    }
}

