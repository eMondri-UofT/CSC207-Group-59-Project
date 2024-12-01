package entity;

import java.util.Random;

public class Item {

    private final String name;
    private final String category;
    private final String rarity;
    private final int price;

    /**
     * Constructor for an Item.
     *
     * @param name     the name of the item
     * @param category the category of the item
     * @param rarity   the rarity of the item
     */
    public Item(String name, String category, String rarity) {
        this.name = name;
        this.category = category;
        this.rarity = rarity;
        this.price = determinePrice(rarity);
    }

    /**
     * Generates a random item.
     *
     * @return a randomly generated item (Weapon, Armor, or Buff)
     */
    public static Item generateRandomItem() {
        Random random = new Random();

        // Rarities and their quality ranges
        String[] rarities = {"Common", "Rare", "Epic", "Legendary"};
        int rarityIndex = random.nextInt(rarities.length);
        String rarity = rarities[rarityIndex];

        int type = random.nextInt(3); // 0 = Weapon, 1 = Armor, 2 = Buff

        if (type == 0) { // Weapon
            if ("Common".equals(rarity)) {
                return new Weapon("Common Sword", rarity, random.nextInt(10) + 1); // Quality range: 1-10
            } else if ("Rare".equals(rarity)) {
                return new Weapon("Rare Blade", rarity, random.nextInt(10) + 11); // Quality range: 11-20
            } else if ("Epic".equals(rarity)) {
                return new Weapon("Epic Greatsword", rarity, random.nextInt(10) + 21); // Quality range: 21-30
            } else if ("Legendary".equals(rarity)) {
                return new Weapon("Legendary Excalibur", rarity, random.nextInt(10) + 31); // Quality range: 31-40
            }
        } else if (type == 1) { // Armor
            if ("Common".equals(rarity)) {
                return new Armor("Common Shield", rarity, random.nextInt(5) + 1); // Quality range: 1-5
            } else if ("Rare".equals(rarity)) {
                return new Armor("Rare Breastplate", rarity, random.nextInt(5) + 6); // Quality range: 6-10
            } else if ("Epic".equals(rarity)) {
                return new Armor("Epic Helm", rarity, random.nextInt(5) + 11); // Quality range: 11-15
            } else if ("Legendary".equals(rarity)) {
                return new Armor("Legendary Aegis", rarity, random.nextInt(5) + 16); // Quality range: 16-20
            }
        } else if (type == 2) { // Buff
            if ("Common".equals(rarity)) {
                return new Buff("Common Health Potion", rarity, random.nextInt(5) + 3); // Healing range: 3-7
            } else if ("Rare".equals(rarity)) {
                return new Buff("Rare Potion", rarity, random.nextInt(5) + 8); // Healing range: 8-12
            } else if ("Epic".equals(rarity)) {
                return new Buff("Epic Regeneration Potion", rarity, random.nextInt(5) + 13); // Healing range: 13-17
            } else if ("Legendary".equals(rarity)) {
                return new Buff("Legendary Vitality Potion", rarity, random.nextInt(4) + 19); // Healing range: 19-22
            }
        }


        throw new IllegalStateException("Unexpected item type: " + type);
    }


    /**
     * Gets the name of the item.
     *
     * @return the item's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the category of the item.
     *
     * @return the item's category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Gets the rarity of the item.
     *
     * @return the item's rarity
     */
    public String getRarity() {
        return rarity;
    }

    /**
     * Gets the price of the item.
     * @return the item's price
     */
    public int getPrice() {
        return price;
    }

    /**
     * Determines the price based on the rarity of the item.
     *
     * @param rarity the rarity of the item
     * @return the price of the item
     */
    private int determinePrice(String rarity) {
        switch (rarity) {
            case "Common":
                return 10;
            case "Rare":
                return 25;
            case "Epic":
                return 50;
            case "Legendary":
                return 100;
            default:
                throw new IllegalArgumentException("Unknown rarity: " + rarity);
        }
    }
}
