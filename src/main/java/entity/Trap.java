package entity;

/**
 * The representation of a trap.
 */
public class Trap {
    private String name;
    private int damage;

    public Trap(String name, int damage) {
        this.setName(name);
        this.setDamage(damage);
    }

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
}
