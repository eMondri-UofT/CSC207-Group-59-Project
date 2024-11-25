package interface_adapter.fall_for_trap;

/**
 * The state for the Fall For Trap View Model.
 */
public class FallForTrapState {
    private String name;
    private int damage;
    private int difficulty;

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
