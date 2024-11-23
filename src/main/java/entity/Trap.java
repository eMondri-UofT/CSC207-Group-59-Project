package entity;

/**
 * The representation of a trap.
 */
public class Trap {
    private String name;
    private int damage;
    private int difficulty;

    public Trap(String name, int damage, int difficulty) {
        this.setName(name);
        this.setDamage(damage);
        this.setDifficulty(difficulty);
    }

    public Trap() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
