package use_case.room_default;

import entity.Trap;

/**
 * Interface for accessing data related to traps in trap rooms.
 */
public interface TrapRoomDataAccessInterface {

    /**
     * Returns the current trap.
     * @param name the name of the trap
     * @return the current trap
     */
    Trap getCurrentTrap(String name);

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

    /**
     * Generates and retrieves a random trap from the data source.
     * @return a randomly selected {@link Trap} object, or {@code null} if no traps are available
     */
    Trap generateRandomTrap();

    /**
     * Loads trap data into the data source, typically for initialization purposes.
     */
    void loadTraps();
}
