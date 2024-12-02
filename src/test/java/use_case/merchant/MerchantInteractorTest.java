package use_case.merchant;

import data_access.NpcDataAccessObject;
import entity.Item;
import entity.Merchant;
import entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MerchantInteractorTest {

    private NpcDataAccessObject npcRepository;
    private MerchantOutputBoundary outputBoundary;
    private MerchantInteractor interactor;

    @BeforeEach
    void setUp() {
        npcRepository = new NpcDataAccessObject();
        outputBoundary = new MerchantOutputBoundary() {
            @Override
            public void prepareSuccessView(MerchantOutputData merchantOutputData) {
                Map<String, List<Item>> itemsForSale = merchantOutputData.getMerchantItems();
                assertNotNull(itemsForSale, "Items for sale should not be null.");
                assertTrue(itemsForSale.containsKey("Buff"), "Items should include Buff category.");
                assertEquals(1, itemsForSale.get("Buff").size(), "There should be one item in Buff category.");
            }

            @Override
            public void prepareBuyView(MerchantOutputData merchantOutputData, String itemName) {
                Map<String, List<Item>> itemsForSale = merchantOutputData.getMerchantItems();
                assertFalse(itemsForSale.values().stream()
                        .flatMap(Collection::stream)
                        .anyMatch(item -> item.getName().equals(itemName)), "Item should be removed from sale.");
            }
        };
        interactor = new MerchantInteractor(npcRepository, outputBoundary);
    }

    @Test
    void testExecuteSuccess() {
        // Set up a merchant with items for sale
        Item healthPotion = new Item("Health Potion", "Buff", "Common");
        Map<String, List<Item>> itemsForSale = new HashMap<>();
        itemsForSale.put("Buff", Collections.singletonList(healthPotion));

        Merchant merchant = new Merchant("Test Merchant", "A friendly merchant",
                Collections.singletonList("Welcome to my shop!"), itemsForSale);

        npcRepository.setCurrentNpc(merchant);

        // Execute the use case
        MerchantInputData inputData = new MerchantInputData(itemsForSale);
        interactor.execute(inputData);
    }

    @Test
    void testBuySuccess() {
        // Set up a merchant with items for sale
        Item healthPotion = new Item("Health Potion", "Buff", "Common");
        Map<String, List<Item>> itemsForSale = new HashMap<>();
        itemsForSale.put("Buff", new ArrayList<>(List.of(healthPotion)));

        Merchant merchant = new Merchant("Test Merchant", "A friendly merchant",
                Collections.singletonList("Welcome to my shop!"), itemsForSale);

        npcRepository.setCurrentNpc(merchant);

        // Set up the player
        Player player = Player.getInstance();
        player.setGold(50);

        // Buy the item
        MerchantInputData inputData = new MerchantInputData("Health Potion");
        interactor.buy(inputData);

        // Verify the player's inventory
        assertTrue(player.getInventory().hasItem("Health Potion"), "Player should have the purchased item.");
        assertEquals(40, player.getGold(), "Player's gold should be reduced by the item's price.");
    }

    @Test
    void testBuyFailureNotEnoughGold() {
        // Set up a merchant with items for sale
        Item healthPotion = new Item("Health Potion", "Buff", "Common");
        Map<String, List<Item>> itemsForSale = new HashMap<>();
        itemsForSale.put("Buff", Collections.singletonList(healthPotion));

        Merchant merchant = new Merchant("Test Merchant", "A friendly merchant",
                Collections.singletonList("Welcome to my shop!"), itemsForSale);

        npcRepository.setCurrentNpc(merchant);

        // Set up the player with insufficient gold
        Player player = Player.getInstance();
        player.setGold(5);

        // Attempt to buy the item
        MerchantInputData inputData = new MerchantInputData("Health Potion");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> interactor.buy(inputData));
        assertEquals("Purchase failed: Player does not have enough gold to buy Health Potion", exception.getMessage());
    }

    @Test
    void testBuyFailureItemNotAvailable() {
        // Set up a merchant with items for sale
        Map<String, List<Item>> itemsForSale = new HashMap<>();
        Merchant merchant = new Merchant("Test Merchant", "A friendly merchant",
                Collections.singletonList("Welcome to my shop!"), itemsForSale);

        npcRepository.setCurrentNpc(merchant);

        // Attempt to buy an item that doesn't exist
        MerchantInputData inputData = new MerchantInputData("Nonexistent Item");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> interactor.buy(inputData));
        assertEquals("The item is not available for sale: Nonexistent Item", exception.getMessage());
    }
}
