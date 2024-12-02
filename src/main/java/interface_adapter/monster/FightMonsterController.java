package interface_adapter.monster;

import use_case.monster.FightMonsterInputBoundary;
import use_case.monster.FightMonsterInputData;

/**
 * The Controller for the Fight Monster Use Case
 */
public class FightMonsterController {
    private FightMonsterInputBoundary fightMonsterUseCaseInteractor;

    public FightMonsterController(FightMonsterInputBoundary fightMonsterUseCaseInteractor) {
        this.fightMonsterUseCaseInteractor = fightMonsterUseCaseInteractor;
    }

    public void execute() {
        final FightMonsterInputData fight = new FightMonsterInputData();
        fightMonsterUseCaseInteractor.execute(fight);
    }

    public void hit() {
        final FightMonsterInputData fight = new FightMonsterInputData();
        fightMonsterUseCaseInteractor.hit(fight);
    }
}
