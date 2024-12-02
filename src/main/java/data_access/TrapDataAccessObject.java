package data_access;

import java.util.HashMap;
import java.util.Map;

import entity.Trap;
import use_case.fall_for_trap.FallForTrapDataAccessInterface;
import use_case.room_default.TrapRoomDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing trap data. This implementation does
 * NOT persist data between runs of the program.
 */
public class TrapDataAccessObject implements FallForTrapDataAccessInterface, TrapRoomDataAccessInterface {
    private static final int PENDULUM_DAMAGE = 10;
    private static final int CALL_OF_DISTRESS_DAMAGE = 5;
    private static final int SNARES_DAMAGE = 3;
    private static final int MIMIC_DAMAGE = 8;
    private static final int BEAR_TRAPS_DAMAGE = 6;
    private static final int GELATINOUS_CUBE_DAMAGE = 12;
    private static final int FALSE_ADVENTURE_DAMAGE = 4;
    private static final int ROLLING_BOULDER_DAMAGE = 10;
    private static final int TRUSTY_PIT_TRAP_DAMAGE = 7;
    private static final int DUPLICATE_MIRROR_DAMAGE = 6;
    private static final int TELEPORT_DOORWAY_DAMAGE = 5;
    private static final int GLYPH_OF_WARDING_DAMAGE = 15;
    private static final int WEB_OF_FLAMES_DAMAGE = 14;
    private static final int PRESSURE_PLATED_ITEMS_DAMAGE = 4;
    private static final int STATUES_ALIVE_DAMAGE = 9;
    private static final int COMPACTOR_DAMAGE = 13;
    private static final int GREASED_SLIDE_DAMAGE = 4;
    private static final int POLYMORPH_TRAP_DAMAGE = 6;
    private static final int POISON_NEEDLES_DAMAGE = 5;
    private static final int MAGNETS_DAMAGE = 7;
    private static final int FISHING_DAMAGE = 8;
    private static final int AMBUSH_DAMAGE = 12;
    private static final int FURNACE_DAMAGE = 15;
    private static final int MAGIC_MOUTH_DAMAGE = 3;
    private static final int PORTABLE_HOLE_DAMAGE = 20;

    private final Map<String, Trap> traps = new HashMap<>();
    private Trap currentTrap;
    private String currentTrapName;

    @Override
    public Trap getCurrentTrap() {
        return currentTrap;
    }

    @Override
    public String getCurrentTrapName() {
        return currentTrapName;
    }

    @Override
    public void setCurrentTrapName(String name) {
        this.currentTrapName = name;
    }

    @Override
    public Trap generateRandomTrap() {
        Trap trap = null;

        if (!traps.isEmpty()) {
            // Retrieves the set of keys from traps and convert it into an array
            final String[] keys = traps.keySet().toArray(new String[0]);
            // Pick a number between 0 and keys.length and cast to an int
            final String randomKey = keys[(int) (Math.random() * keys.length)];
            setCurrentTrapName(randomKey);
            trap = traps.remove(randomKey);
            currentTrap = trap;
        }
        return trap;
    }

    @Override
    public void loadTraps() {
        traps.put("Pendulums", new Trap("Pendulums", PENDULUM_DAMAGE));
        traps.put("Call Of Distress", new Trap("Call Of Distress", CALL_OF_DISTRESS_DAMAGE));
        traps.put("Snares", new Trap("Snares", SNARES_DAMAGE));
        traps.put("Mimic", new Trap("Mimic", MIMIC_DAMAGE));
        traps.put("Bear Traps", new Trap("Bear Traps", BEAR_TRAPS_DAMAGE));
        traps.put("Gelatinous Cube", new Trap("Gelatinous Cube", GELATINOUS_CUBE_DAMAGE));
        traps.put("The False Adventure", new Trap("The False Adventure", FALSE_ADVENTURE_DAMAGE));
        traps.put("The Rolling Boulder", new Trap("The Rolling Boulder", ROLLING_BOULDER_DAMAGE));
        traps.put("The Trusty Pit Trap", new Trap("The Trusty Pit Trap", TRUSTY_PIT_TRAP_DAMAGE));
        traps.put("The Duplicate Mirror", new Trap("The Duplicate Mirror", DUPLICATE_MIRROR_DAMAGE));
        traps.put("Teleport Doorway", new Trap("Teleport Doorway", TELEPORT_DOORWAY_DAMAGE));
        traps.put("Glyph Of Warding And Fire Trap Spells", new Trap("Glyph Of Warding And Fire Trap Spells",
                GLYPH_OF_WARDING_DAMAGE));
        traps.put("Web Of Flames", new Trap("Web Of Flames", WEB_OF_FLAMES_DAMAGE));
        traps.put("Pressure Plated Items", new Trap("Pressure Plated Items", PRESSURE_PLATED_ITEMS_DAMAGE));
        traps.put("The Statues Are Alive", new Trap("The Statues Are Alive", STATUES_ALIVE_DAMAGE));
        traps.put("The Compactor", new Trap("The Compactor", COMPACTOR_DAMAGE));
        traps.put("Greased Slide", new Trap("Greased Slide", GREASED_SLIDE_DAMAGE));
        traps.put("Polymorph Trap", new Trap("Polymorph Trap", POLYMORPH_TRAP_DAMAGE));
        traps.put("Poison Needles", new Trap("Poison Needles", POISON_NEEDLES_DAMAGE));
        traps.put("Fun With Magnets", new Trap("Fun With Magnets", MAGNETS_DAMAGE));
        traps.put("Let's Go Fishing", new Trap("Let's Go Fishing", FISHING_DAMAGE));
        traps.put("Umber Hulk Ambush", new Trap("Umber Hulk Ambush", AMBUSH_DAMAGE));
        traps.put("The Furnace", new Trap("The Furnace", FURNACE_DAMAGE));
        traps.put("The Magic Mouth", new Trap("The Magic Mouth", MAGIC_MOUTH_DAMAGE));
        traps.put("Portable Hole Into The Bag Of Holding", new Trap("Portable Hole Into The Bag Of Holding",
                PORTABLE_HOLE_DAMAGE));
    }
}
