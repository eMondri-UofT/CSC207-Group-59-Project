package entity;

public class NpcRoom extends Room{
    private Npc npc;



    @Override
    public int getRoomType() {
        return 1; // Item Room
    }
    /**
     * Creates an NPCRoom
     * @param npc the Npc in the NPCRoom
     */

    public NpcRoom(int roomNumber, Npc npc){
        super(roomNumber);
        this.npc = npc;
    }

    /**
     * Returns the Npc in the NPCRoom
     * @return the Npc in NPCRoom
     */

    public Npc getNpc(){
        return npc;
    }

    /**
     * Allows us to set the Npc in the NPCRoom
     * @param npc the npc we want to set inside the NPCRoom
     */

    public void setNpc(Npc npc){
        this.npc = npc;
    }
}
