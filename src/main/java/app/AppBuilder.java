package app;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.*;
import entity.*;
import interface_adapter.ViewManagerModel;

import interface_adapter.equip_item.EquipItemController;
import interface_adapter.equip_item.EquipItemPresenter;
import interface_adapter.merchant.MerchantController;
import interface_adapter.merchant.MerchantPresenter;
import interface_adapter.monster.FightMonsterController;
import interface_adapter.monster.FightMonsterPresenter;
import interface_adapter.monster.FightMonsterViewModel;
import interface_adapter.pickup_item.PickUpItemController;
import interface_adapter.pickup_item.PickUpItemPresenter;
import interface_adapter.room_default.RoomDefaultViewModel;
import interface_adapter.pickup_item.PickUpItemViewModel;
import interface_adapter.equip_item.EquipItemViewModel;
import interface_adapter.merchant.MerchantViewModel;
import interface_adapter.open_inventory.OpenInventoryController;
import interface_adapter.open_inventory.OpenInventoryPresenter;
import interface_adapter.open_inventory.OpenInventoryViewModel;
import interface_adapter.room_default.RoomDefaultController;
import interface_adapter.room_default.RoomDefaultPresenter;
import use_case.equip_item.EquipItemInputBoundary;
import use_case.equip_item.EquipItemInteractor;
import use_case.equip_item.EquipItemOutputBoundary;
import use_case.monster.FightMonsterInputBoundary;
import use_case.monster.FightMonsterInteractor;
import use_case.monster.FightMonsterOutputBoundary;
import interface_adapter.character_creation.CharacterCreationController;
import interface_adapter.character_creation.CharacterCreationPresenter;
import interface_adapter.character_creation.CharacterCreationViewModel;
import interface_adapter.talk_to_npc.TalkToNpcController;
import interface_adapter.talk_to_npc.TalkToNpcPresenter;
import interface_adapter.talk_to_npc.TalkToNpcViewModel;
import interface_adapter.fall_for_trap.FallForTrapController;
import interface_adapter.fall_for_trap.FallForTrapPresenter;
import interface_adapter.fall_for_trap.FallForTrapViewModel;
import use_case.merchant.*;
import use_case.monster.FightMonsterInputBoundary;
import use_case.monster.FightMonsterInteractor;
import use_case.monster.FightMonsterOutputBoundary;

import use_case.open_inventory.OpenInventoryInputBoundary;
import use_case.open_inventory.OpenInventoryInteractor;
import use_case.open_inventory.OpenInventoryOutputBoundary;
import use_case.pickup_item.PickUpItemDataAccessInterface;
import use_case.pickup_item.PickUpItemInputBoundary;
import use_case.pickup_item.PickUpItemInteractor;
import use_case.pickup_item.PickUpItemOutputBoundary;
import use_case.room_default.*;
import use_case.character_creation.CharacterCreationInputBoundary;
import use_case.character_creation.CharacterCreationInteractor;
import use_case.character_creation.CharacterCreationOutputBoundary;
import use_case.talk_to_npc.TalkToNpcInputBoundary;
import use_case.talk_to_npc.TalkToNpcInteractor;
import use_case.talk_to_npc.TalkToNpcOutputBoundary;
import use_case.fall_for_trap.FallForTrapInputBoundary;
import use_case.fall_for_trap.FallForTrapInteractor;
import use_case.fall_for_trap.FallForTrapOutputBoundary;
import use_case.merchant.MerchantInputBoundary;
import use_case.merchant.MerchantInteractor;
import view.*;


/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final InventoryFactory inventoryFactory = new InventoryFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private final InventoryDataAccessObject inventoryDataAccessObject = new InventoryDataAccessObject();
    private final RoomDataAccessObject roomDataAccessObject = new RoomDataAccessObject();
    private final PlayerDataAccessObject playerDataAccessObject = new PlayerDataAccessObject();
    private final NpcDataAccessObject npcDataAccessObject = new NpcDataAccessObject();
    private final TrapDataAccessObject trapDataAccessObject = new TrapDataAccessObject();
    private final FightMonsterDataAccessObject fightMonsterDataAccessObject = new FightMonsterDataAccessObject();
    private final EquipItemDataAccessObject equipItemDataAccessObject = new EquipItemDataAccessObject();
    private final PickUpItemDataAccessObject pickUpItemDataAccessObject = new PickUpItemDataAccessObject();
    private final NpcRoomDataAccessInterface npcRoomDataAccessInterface = new NpcDataAccessObject();
    private final TrapRoomDataAccessInterface trapRoomDataAccessInterface = new TrapDataAccessObject();
    private TalkToNpcViewModel talkToNpcViewModel;
    private FallForTrapViewModel fallForTrapViewModel;
    private RoomDefaultViewModel roomDefaultViewModel;
    private OpenInventoryViewModel openInventoryViewModel;
    private MerchantViewModel merchantViewModel;
    private PickUpItemViewModel pickUpItemViewModel;
    private EquipItemViewModel equipItemViewModel;
    private OpenInventoryView openInventoryView;
    private RoomView roomView;
    private PickUpItemView pickUpItemView;
    private EquipItemView equipItemView;
    private CharacterCreationViewModel characterCreationViewModel;
    private CharacterCreationView characterCreationView;
    private TalkToNpcView talkToNpcView;
    private FallForTrapView fallForTrapView;
    private MerchantView merchantView;
    private FightMonsterViewModel fightMonsterViewModel;
    private MonsterView monsterView;
  
    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the OpenInventory View to the application.
     * @return this builder
     */
    public AppBuilder addOpenInventoryView() {
        openInventoryViewModel = new OpenInventoryViewModel();
        openInventoryView = new OpenInventoryView(openInventoryViewModel);
        cardPanel.add(openInventoryView, openInventoryView.getViewName());
        return this;
    }

    /**
     * Adds the Room View to the application.
     * @return this builder
     */
    public AppBuilder addRoomView() {
        roomDefaultViewModel = new RoomDefaultViewModel();
        roomView = new RoomView(roomDefaultViewModel);
        cardPanel.add(roomView, roomDefaultViewModel.getViewName());
        return this;
    }

    public AppBuilder addPickUpItemView() {
        pickUpItemViewModel = new PickUpItemViewModel();
        pickUpItemView = new PickUpItemView(pickUpItemViewModel, viewManagerModel);
        cardPanel.add(pickUpItemView, pickUpItemView.getViewName());
        return this;
    }

    public AppBuilder addEquipItemView() {
        equipItemViewModel = new EquipItemViewModel();
        equipItemView = new EquipItemView(equipItemViewModel, viewManagerModel);
        cardPanel.add(equipItemView, equipItemView.getViewName());
        return this;
    }

    /**
     * Adds the Character Creation View to the application.
     * @return this builder
     */
    public AppBuilder addCharacterCreationView() {
        characterCreationViewModel = new CharacterCreationViewModel();
        characterCreationView = new CharacterCreationView(characterCreationViewModel);
        cardPanel.add(characterCreationView, characterCreationViewModel.getViewName());
        return this;
    }
    
      /**
     * Adds the Fall For Trap View to the application.
     * @return this builder
     */
    public AppBuilder addFallForTrapView() {
        fallForTrapViewModel = new FallForTrapViewModel();
        fallForTrapView = new FallForTrapView(fallForTrapViewModel);
        cardPanel.add(fallForTrapView, fallForTrapViewModel.getViewName());
        return this;
    }

    /**
     * Adds the Talk To Npc View to the application.
     * @return this builder
     */
    public AppBuilder addTalkToNpcView() {
        talkToNpcViewModel = new TalkToNpcViewModel();
        talkToNpcView = new TalkToNpcView(talkToNpcViewModel);
        cardPanel.add(talkToNpcView, talkToNpcViewModel.getViewName());
        return this;
    }

    /**
     * Adds the Merchant View to the application.
     * @return this builder
     */
    public AppBuilder addMerchantView() {
        merchantViewModel = new MerchantViewModel(npcDataAccessObject);
        merchantView = new MerchantView(merchantViewModel);
        cardPanel.add(merchantView, merchantViewModel.getViewName());
        return this;
    }
  
      public AppBuilder addFightMonsterView() {
        fightMonsterViewModel = new FightMonsterViewModel();
        monsterView = new MonsterView(fightMonsterViewModel);
        cardPanel.add(monsterView, monsterView.getViewName());
        return this;
    }

    /**
     * Adds the Room Use Case to the application.
     * @return this builder
     */
    public AppBuilder addRoomUseCase() {
        // Create the presenter with appropriate view models
        final RoomOutputBoundary roomOutputBoundary = new RoomDefaultPresenter(
                viewManagerModel, roomDefaultViewModel, talkToNpcViewModel,
                fallForTrapViewModel, openInventoryViewModel);

        // Create the interactor with the presenter and data access objects
        final RoomInputBoundary roomInteractor =
                new RoomInteractor(roomOutputBoundary, roomDataAccessObject,
                        npcRoomDataAccessInterface, trapRoomDataAccessInterface);

        // Create the controller with the interactor
        final RoomDefaultController roomDefaultController = new RoomDefaultController(roomInteractor);

        // Set the controller in the view
        roomView.setRoomController(roomDefaultController);

        return this;
    }



    /**
     * Adds the OpenInventory Use Case to the application.
     * @return this builder
     */
    public AppBuilder addOpenInventoryUseCase() {
        final OpenInventoryOutputBoundary openInventoryOutputBoundary = new OpenInventoryPresenter(
                viewManagerModel, openInventoryViewModel, roomDefaultViewModel);

        final OpenInventoryInputBoundary openInventoryInteractor =
                new OpenInventoryInteractor(inventoryDataAccessObject, openInventoryOutputBoundary);

        final OpenInventoryController openInventoryController = new OpenInventoryController(openInventoryInteractor);
        openInventoryView.setOpenInventoryController(openInventoryController);
        return this;
    }

    public AppBuilder addMonsterUseCase() {
        final FightMonsterOutputBoundary fightMonsterOutputBoundary = new FightMonsterPresenter(
                fightMonsterViewModel, viewManagerModel, roomDefaultViewModel);

        final FightMonsterInputBoundary fightMonsterInteractor =
                new FightMonsterInteractor(fightMonsterDataAccessObject, fightMonsterOutputBoundary);

        final FightMonsterController fightMonsterController = new FightMonsterController(fightMonsterInteractor);
        monsterView.setFightMonsterController(fightMonsterController);
        return this;
    }

    /**
     * Adds the CharacterCreation Use Case to the application.
     * @return this builder
     */
    public AppBuilder addCharacterCreationUseCase() {
        final CharacterCreationOutputBoundary characterCreationOutputBoundary = new CharacterCreationPresenter(
                viewManagerModel, characterCreationViewModel, roomDefaultViewModel);

        final CharacterCreationInputBoundary characterCreationInteractor =
                new CharacterCreationInteractor(playerDataAccessObject, characterCreationOutputBoundary, roomDataAccessObject);

        final CharacterCreationController characterCreationController =
                new CharacterCreationController(characterCreationInteractor);
        characterCreationView.setCharacterCreationController(characterCreationController);
        return this;
    }

    public AppBuilder addMerchantUseCase() {
        final MerchantOutputBoundary merchantOutputBoundary = new MerchantPresenter(merchantViewModel,
                roomDefaultViewModel, viewManagerModel);

        final MerchantInputBoundary merchantInteractor = new MerchantInteractor(npcDataAccessObject,
                merchantOutputBoundary);
        final MerchantController merchantController = new MerchantController(merchantInteractor);
        merchantView.setMerchantController(merchantController);
        return this;
    }

    /**
     * Adds the TalkToNpc Use Case to the application.
     * @return this builder
     */
    public AppBuilder addTalkToNpcUseCase() {
        final TalkToNpcOutputBoundary talkToNpcOutputBoundary = new TalkToNpcPresenter(
                viewManagerModel, talkToNpcViewModel, merchantViewModel, roomDefaultViewModel);

        final TalkToNpcInputBoundary talkToNpcInteractor =
                new TalkToNpcInteractor(npcDataAccessObject, talkToNpcOutputBoundary);

        final TalkToNpcController talkToNpcController =
                new TalkToNpcController(talkToNpcInteractor);
        talkToNpcView.setTalkToNpcController(talkToNpcController);
        return this;
    }

    /**
     * Adds the TalkToNpc Use Case to the application.
     * @return this builder
     */
    public AppBuilder addFallForTrapUseCase() {
        final FallForTrapOutputBoundary fallForTrapOutputBoundary = new FallForTrapPresenter(
                viewManagerModel, roomDefaultViewModel);

        final FallForTrapInputBoundary fallForTrapInteractor =
                new FallForTrapInteractor(trapDataAccessObject, fallForTrapOutputBoundary);

        final FallForTrapController fallForTrapController =
                new FallForTrapController(fallForTrapInteractor);
        fallForTrapView.setFallForTrapController(fallForTrapController);
        return this;
    }


    /**
     * Adds the Equip Item Use Case to the application.
     * @return this builder
     */
    /**
     * Adds the EquipItem Use Case to the application.
     * @return this builder
     */
    public AppBuilder addEquipItemUseCase() {
        final EquipItemOutputBoundary equipItemOutputBoundary = new EquipItemPresenter(
                viewManagerModel, equipItemViewModel, roomDefaultViewModel);
        final EquipItemInputBoundary equipItemInteractor = new EquipItemInteractor(
                equipItemDataAccessObject, equipItemOutputBoundary);
        final EquipItemController equipItemController = new EquipItemController(equipItemInteractor);
        equipItemView.setEquipItemController(equipItemController);

        return this;
    }

    public AppBuilder addPickUpItemUseCase() {
        final PickUpItemOutputBoundary pickUpItemOutputBoundary = new PickUpItemPresenter(
                viewManagerModel, pickUpItemViewModel, roomDefaultViewModel);
        final PickUpItemInputBoundary pickUpItemInteractor = new PickUpItemInteractor(pickUpItemDataAccessObject, pickUpItemOutputBoundary);

        final PickUpItemController pickUpItemController = new PickUpItemController(pickUpItemInteractor);

        pickUpItemView.setPickUpController(pickUpItemController);

        return this;
    }






    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("CYOA Game");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        cardPanel.setPreferredSize(new Dimension(400, 200));
        application.add(cardPanel);

        viewManagerModel.setState(monsterView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
