package use_case.character_creation;

/**
 * Output Data for the Character Creation Use Case.
 */
public class CharacterCreationOutputData {
    private final String roomDescription;
    private final String roomType;

    /**
     * Constructor for CharacterCreationOutputData.
     *
     * @param roomDescription the description of the room
     * @param roomType        the type of the room
     */
    public CharacterCreationOutputData(String roomDescription, String roomType) {
        this.roomDescription = roomDescription;
        this.roomType = roomType;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public String getRoomType() {
        return roomType;
    }
}
