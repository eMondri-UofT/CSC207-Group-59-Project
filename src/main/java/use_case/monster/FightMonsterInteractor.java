package use_case.monster;

public class FightMonsterInteractor implements FightMonsterInputBoundary {
    private FightMonsterDataAccessInterface fightMonsterDataAccessInterface;
    private FightMonsterOutputBoundary fightMonsterOutputBoundary;

    public FightMonsterInteractor(FightMonsterDataAccessInterface dataAccessInterface, FightMonsterOutputBoundary outputBoundary) {
        this.fightMonsterDataAccessInterface = dataAccessInterface;
        this.fightMonsterOutputBoundary = outputBoundary;
    }

    public void execute(FightMonsterInputData openInventoryInputData) {
        final FightMonsterOutputData openInventoryOutputData = new FightMonsterOutputData(false);
        fightMonsterOutputBoundary.prepareSuccessView(openInventoryOutputData);
    }

    public void hit(FightMonsterInputData openInventoryInputData) {
        final FightMonsterOutputData openInventoryOutputData = new FightMonsterOutputData(false);
    }
}
