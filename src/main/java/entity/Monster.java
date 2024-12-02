package entity;

/**
 * A representation of a Monster
 */
public class Monster {
    private int health;
    private String name;

    public Monster(int health, String name) {
        this.health = health;
        this.name = name;
    }

    public Monster() {
        this.health = (int)Math.random()*100 + 10;
        this.name = "Basic Monster";
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
