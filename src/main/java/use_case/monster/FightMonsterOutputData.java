package use_case.monster;

/**
 * The output data for the Fight Monster Use Case
 */
public class FightMonsterOutputData {
    private boolean useCaseFailed;

    public FightMonsterOutputData(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
