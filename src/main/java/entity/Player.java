package entity;

import java.util.HashMap;
import java.util.Map;

/**
 * The representation of the player, which the user controls.
 */
public class Player {
    private static Player instance;
    private String pclass;
    private String prace;
    private static Inventory inventory;
    private final Map<String, Item> equippedItems;
    private Room currentRoom;
    private int classArmor;
    private int classDamage;
    private int raceArmor;
    private int raceDamage;
    private int totalArmor;
    private int totalDamage;


    private int health;
    private int maxHealth;
    private int healthRegeneration;

    private int gold;


    public Player() {
        this.inventory = new Inventory();     //assume Base information for all race, add value if race changed.
        this.health = 100;
        this.maxHealth = 100;
        this.healthRegeneration = 0;
        this.equippedItems = new HashMap<>();
        this.equippedItems.put("Armor", null);
        this.equippedItems.put("Weapon", null);
        this.equippedItems.put("Buff", null);
        this.gold = 150;
    }

    /**
     * Getter and setter for pclass.
     */
    public String getPclass() {
        return pclass;
    }
    public void setPclass(String pclass) {
        this.pclass = pclass;
    }

    /**
     * Getter and setter for prace.
     */
    public String getPrace() {
        return prace;
    }
    public void setPrace(String race) {
        this.prace = race;
    }

    public static void setInventory(Inventory newInventory) {
        inventory = newInventory;
    }


    public static Inventory getInventory() {
        if (inventory == null) {
            inventory = new Inventory(); // Initialize if not already set
        }
        return inventory;
    }


    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = Math.min(health, maxHealth);
    }

    /**
     * Getter and setter for max health.
     */
    public int getMaxHealth() {
        return maxHealth;
    }
    public void increaseHealthLimit(int amount) {
        this.maxHealth += amount;
        System.out.println("Max health increased by " + amount + ". New max health: " + maxHealth);
    }

    /**
     * Getter and setter for health regeneration.
     */
    public int getHealthRegeneration() {
        return healthRegeneration;
    }
    public void increaseHealthRegeneration(int amount) {
        this.healthRegeneration += amount;
        System.out.println("Health regeneration increased by " + amount + ". New health regeneration: " + healthRegeneration);
    }

    /**
     * Regenerates health based on the health regeneration attribute.
     */
    public void regenerateHealth() {
        if (healthRegeneration > 0) {
            setHealth(health + healthRegeneration);
            System.out.println("Health regenerated by " + healthRegeneration + ". Current health: " + health);
        }
    }

    /**
     * Equips an item into the appropriate slot.
     *
     * @param item The item to equip.
     * @return A message indicating the result of the operation.
     */
    public String equipItem(Item item) {
        String slot;
        if (item instanceof Armor) {
            slot = "Armor";
        } else if (item instanceof Weapon) {
            slot = "Weapon";
        } else if (item instanceof Buff) {
            slot = "Buff";
        } else {
            return "Invalid item type.";
        }

        // Add the item to the inventory
        inventory.addItem(item.getName(), item);

        // Equip the item
        equippedItems.put(slot, item);
        return "Equipped " + item.getName() + " in " + slot + " slot.";
    }


    /**
     * Returns the singleton instance of Player.
     *
     * @return the Player instance.
     */
    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }

    /**
     * Getter for the current room.
     *
     * @return the current room of the player
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Setter for the current room.
     *
     * @param currentRoom the room to set as the player's current room
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }



    public Item replaceItem(Item newItem) {
        if (newItem == null) {
            return null; // Cannot equip null items
        }

        String category = newItem.getCategory();
        Item oldItem;

        switch (category) {
            case "Weapon":
                oldItem = inventory.getEquippedWeapon();
                inventory.setEquippedWeapon(newItem);
                break;
            case "Armor":
                oldItem = inventory.getEquippedArmor();
                inventory.setEquippedArmor(newItem);
                break;
            case "Buff":
                oldItem = inventory.getEquippedBuff();
                inventory.setEquippedBuff(newItem);
                break;
            default:
                return null; // Unsupported item category
        }

        if (oldItem != null) {
            inventory.addItem(oldItem.getName(), oldItem);
        }
        return oldItem;
    }

    /**
     * Getter and setter for classArmor.
     */
    public int getClassArmor() {
        return classArmor;
    }
    public void setClassArmor(int classArmor) {
        this.setTotalArmor(this.getTotalArmor() - this.getClassArmor());
        this.classArmor = classArmor;
        this.setTotalArmor(this.getTotalArmor() + classArmor);
    }

    /**
     * Getter and setter for classDamage.
     */
    public int getClassDamage() {
        return classDamage;
    }
    public void setClassDamage(int classDamage) {
        this.setTotalDamage(this.getTotalDamage() - this.getClassDamage());
        this.classDamage = classDamage;
        this.setTotalDamage(this.getTotalDamage() + classDamage);
    }

    /**
     * Getter and setter for raceArmor.
     */
    public int getRaceArmor() { return raceArmor; }
    public void setRaceArmor(int raceArmor) {
        this.setTotalArmor(this.getTotalArmor() - this.getRaceArmor());
        this.raceArmor = raceArmor;
        this.setTotalArmor(this.getTotalArmor() + raceArmor);
    }

    /**
     * Getter and setter for raceDamage.
     */
    public int getRaceDamage() { return raceDamage; }
    public void setRaceDamage(int raceDamage) {
        this.setTotalDamage(this.getTotalDamage() - this.getRaceDamage());
        this.raceDamage = raceDamage;
        this.setTotalDamage(this.getTotalDamage() + raceDamage);
    }

    /**
     * Getter and setter for totalArmor.
     */
    public int getTotalArmor() { return totalArmor; }
    public void setTotalArmor(int totalArmor) { this.totalArmor = totalArmor; }

    /**
     * Getter and setter for totalDamage.
     */
    public int getTotalDamage() { return totalDamage; }
    public void setTotalDamage(int totalDamage) { this.totalDamage = totalDamage; }

    /**
     * Gett and setter for player gold.
     */
    public int getGold() { return gold; }
    public void setGold(int gold) {
        this.gold = Math.max(0, gold);
    }

    /**
     * Increasers for Armor/Damage
     * @param amount how much armor/damage has increased by
     */
    public void increaseArmor(int amount) {
        this.setTotalArmor(this.getTotalArmor() + amount);
        System.out.println("Defense increased by " + amount + ". New defense: " + this.getTotalArmor());
    }

    public void increaseDamage(int amount) {
        this.setTotalDamage(this.getTotalDamage() + amount);
        System.out.println("Attack power increased by " + amount + ". New attack power: " + this.getTotalDamage());
    }

    /**
     * Add and subtract gold
     */
    public boolean subtractGold(int amount) {
        if (gold >= amount) {
            gold -= amount;
            return true;
        }
        return false; // Not enough gold
    }

    public void addGold(int amount) {
        this.gold += amount;
    }
}
