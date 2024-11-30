package Entitiy_Test;

import entity.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the Player class.
 */
public class Player_Test {

    private Player player;

    @BeforeEach
    public void setup() {
        player = Player.getInstance();
        player.setHealth(100);
        player.setPclass("Warrior");
        player.setPrace("Human");
        player.setInventory(new Inventory());
        player.setCurrentRoom(null);
    }

    @Test
    public void testSingletonInstance() {
        Player firstInstance = Player.getInstance();
        Player secondInstance = Player.getInstance();
        assertSame(firstInstance, secondInstance, "Player should follow the singleton pattern");
    }

    @Test
    public void testGetAndSetPclass() {
        player.setPclass("Mage");
        assertEquals("Mage", player.getPclass(), "Player class should be set to Mage");
    }

    @Test
    public void testGetAndSetPrace() {
        player.setPrace("Elf");
        assertEquals("Elf", player.getPrace(), "Player race should be set to Elf");
    }

    @Test
    public void testHealthManipulation() {
        player.setHealth(120); // Max health is 100, so it should be capped
        assertEquals(100, player.getHealth(), "Health should not exceed max health");

        player.setHealth(80);
        assertEquals(80, player.getHealth(), "Health should be set to 80");

        player.increaseHealthLimit(50);
        assertEquals(150, player.getMaxHealth(), "Max health should be increased to 150");

        player.setHealth(120);
        assertEquals(120, player.getHealth(), "Health should now be set to 120");
    }

    @Test
    public void testArmorManipulation() {
        assertEquals(0, player.getArmor(), "Initial armor should be 0");

        player.increaseArmor(10);
        assertEquals(10, player.getArmor(), "Armor should increase by 10");

        player.increaseArmor(5);
        assertEquals(15, player.getArmor(), "Armor should increase to 15");
    }

    @Test
    public void testAttackPowerManipulation() {
        assertEquals(10, player.getAttackPower(), "Initial attack power should be 10");

        player.increaseAttack(5);
        assertEquals(15, player.getAttackPower(), "Attack power should increase to 15");

        player.increaseAttack(10);
        assertEquals(25, player.getAttackPower(), "Attack power should increase to 25");
    }

    @Test
    public void testHealthRegeneration() {
        player.setHealth(50);
        player.increaseHealthRegeneration(10);
        assertEquals(10, player.getHealthRegeneration(), "Health regeneration should be set to 10");

        player.regenerateHealth();
        assertEquals(60, player.getHealth(), "Health should regenerate to 60");

        player.regenerateHealth();
        assertEquals(70, player.getHealth(), "Health should regenerate to 70");
    }

    @Test
    public void testEquipItem() {
        Armor armor = new Armor("Shield of Valor", "Epic", 15);
        Weapon weapon = new Weapon("Sword of Justice", "Legendary", 20);
        Buff buff = new Buff("Potion of Strength", "Rare", 10);

        assertEquals("Equipped Shield of Valor in Armor slot.", player.equipItem(armor));
        assertEquals("Equipped Sword of Justice in Weapon slot.", player.equipItem(weapon));
        assertEquals("Equipped Potion of Strength in Buff slot.", player.equipItem(buff));

        assertEquals(armor, player.getInventory().getItem("Shield of Valor"));
        assertEquals(weapon, player.getInventory().getItem("Sword of Justice"));
        assertEquals(buff, player.getInventory().getItem("Potion of Strength"));
    }

    @Test
    public void testGetAndSetCurrentRoom() {
        Room room = new ItemRoom(new Item("Sword of Valor", "Weapon", "Epic"));
        player.setCurrentRoom(room);
        assertEquals(room, player.getCurrentRoom(), "Current room should be set to the provided room");
    }

    @Test
    public void testInventoryManagement() {
        Inventory inventory = player.getInventory();

        Item item = new Item("Healing Potion", "Buff", "Common");
        inventory.addItem(item.getName(), item);

        assertNotNull(inventory.getItem("Healing Potion"), "Inventory should contain the added item");
        assertEquals(item, inventory.getItem("Healing Potion"), "Item retrieved should match the added item");

        inventory.removeItem("Healing Potion");
        assertNull(inventory.getItem("Healing Potion"), "Item should be removed from inventory");
    }
}

