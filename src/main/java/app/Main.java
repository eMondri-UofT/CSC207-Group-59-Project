package app;

import javax.swing.JFrame;

/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                                            .addTalkToNpcView()
                                            .addOpenInventoryView()
                                            .addRoomView()
                                            .addCharacterCreationView()
                                            .addFallForTrapView()
                                            .addPickUpItemView()
                                            .addEquipItemView()
                                            .addFightMonsterView()
                                            .addRoomUseCase()
                                            .addOpenInventoryUseCase()
                                            .addCharacterCreationUseCase()
                                            .addTalkToNpcView()
                                            .addFallForTrapView()
                                            .addMerchantView()
                                            .addMonsterUseCase()
                                            .build();

        application.pack();
        application.setVisible(true);
    }
}
