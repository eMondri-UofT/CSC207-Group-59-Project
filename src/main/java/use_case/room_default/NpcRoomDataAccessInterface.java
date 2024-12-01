package use_case.room_default;

import entity.Npc;

/**
 * Interface for accessing and managing NPC data in NPC rooms.
 */
public interface NpcRoomDataAccessInterface {

    /**
     * Returns the current NPC.
     * @return the current NPC
     */
    Npc getCurrentNpc();

    /**
     * Sets the current NPC.
     * @param npc the NPC to set as the current NPC.
     */
    void setCurrentNpc(Npc npc);

    /**
     * Generates a random NPC.
     * @return a randomly generated NPC.
     */
    Npc generateRandomNpc();

    /**
     * Loads all NPCs into the system for use in NPC rooms.
     */
    void loadNpcs();
}
