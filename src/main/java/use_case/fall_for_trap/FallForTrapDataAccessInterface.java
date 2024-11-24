package use_case.fall_for_trap;

import entity.Trap;

/**
 * DAO for the Fall For Trap Use Case.
 */
public interface FallForTrapDataAccessInterface {

    /**
     * Returns the current trap.
     * @param name the name of the trap
     * @return the current trap
     */
    Trap get(String name);

    /**
     * Returns the current trap name.
     * @return the current trap name
     */
    String getCurrentTrapName();

    /**
     * Sets the name of the new trap.
     * @param name the name of the new trap
     */
    void setCurrentTrapName(String name);

}