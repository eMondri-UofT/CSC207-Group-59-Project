package entity;

/**
 * Weapon increases attack power.
 */
public class Weapon extends Item {
    private final int attackBoost;

    public Weapon(String name, String rarity, int attackBoost) {
        super(name, "Weapon", rarity); // Set category as "Weapon"
        this.attackBoost = attackBoost;
    }

    public void applyEffect(Player player) {
        player.increaseDamage(attackBoost);
    }

    public int getAttackBoost() {
        return attackBoost;
    }
}
