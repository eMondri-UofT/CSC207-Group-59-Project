package use_case.merchant;
import interface_adapter.merchant.MerchantState;

import entity.Item;
import entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MerchantStateTest {

    private MerchantState merchantState;
    private Player player;

    @BeforeEach
    void setUp() {
        // Initialize the merchant state
        merchantState = new MerchantState();

        // Mock merchant's inventory
        Map<String, List<Item>> items = new HashMap<>();
        items.put("Potions", Arrays.asList(
                new Item("Health Potion", "Potion", "Common"),
                new Item("Mana Potion", "Potion", "Rare")
        ));
        items.put("Weapons", Arrays.asList(
                new Item("Sword", "Weapon", "Epic")
        ));
        merchantState.setItems(items);

        // Initialize the player and set gold
        player = Player.getInstance();
        player.setGold(100); // Assume player starts with 100 gold
    }

    @Test
    void testSuccessfulPurchase() {
        merchantState.buy("Health Potion");

        // Verify gold is deducted
        assertEquals(90, player.getGold()); // Health Potion costs 10 (common rarity)

        // Verify the item is removed from merchant's inventory
        assertFalse(merchantState.getItems().get("Potions").stream()
                .anyMatch(item -> item.getName().equals("Health Potion")));

        // Verify the item is added to the player's inventory
        assertNotNull(player.getInventory().getItem("Health Potion"));
    }

    @Test
    void testInsufficientGold() {
        player.setGold(5); // Not enough gold for any item

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            merchantState.buy("Mana Potion");
        });

        assertEquals("Not enough gold to buy Mana Potion", exception.getMessage());

        // Verify player's gold is unchanged
        assertEquals(5, player.getGold());
    }

    @Test
    void testItemNotFound() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            merchantState.buy("Shield"); // Item does not exist
        });

        assertEquals("Item not found: Shield", exception.getMessage());
    }

    @Test
    void testInventoryUpdate() {
        merchantState.buy("Sword");

        // Verify merchant's inventory
        assertFalse(merchantState.getItems().get("Weapons").stream()
                .anyMatch(item -> item.getName().equals("Sword")));

        // Verify player's inventory
        assertNotNull(player.getInventory().getItem("Sword"));
    }
}
