package use_case.monster;

/**
 * Input Boundary for actions which are related to fighting a Monster.
 */
public interface FightMonsterInputBoundary  {

    /**
     * Executes the Fight Monster use case.
     * @param FightMonsterInputData the input data
     */
    void execute(FightMonsterInputData FightMonsterInputData);

    /**
     * Executes a turn of attack
     * @param FightMonsterInputData
     */
    void hit(FightMonsterInputData FightMonsterInputData);
}
