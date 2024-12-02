package use_case.monster;

public interface FightMonsterOutputBoundary {

    /**
     * Prepares the success view for the Fight Monster Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(FightMonsterOutputData outputData);

    /**
     * Prepares the failure view for the Fight Monster Use Case.
     * @param errorMessage the explanation of the failure
     */

    void prepareFailView(String errorMessage);

    void prepareHitView(FightMonsterOutputData outputData);
}
