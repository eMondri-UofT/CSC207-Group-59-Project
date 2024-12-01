package use_case.fall_for_trap;

/**
 * Output Data for the Fall For Trap Use Case.
 */
public class FallForTrapOutputData {
    private final String trapName;
    private final int damage;

    public FallForTrapOutputData(String trapName, int damage) {
        this.trapName = trapName;
        this.damage = damage;
    }

    public String getTrapName() {
        return trapName;
    }

    public int getDamage() {
        return damage;
    }
}
